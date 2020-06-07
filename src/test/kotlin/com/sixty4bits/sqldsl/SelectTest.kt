package com.sixty4bits.sqldsl

import com.sixty4bits.sqldsl.commands.select
import com.sixty4bits.sqldsl.condition.eq
import io.kotlintest.shouldBe
import io.kotlintest.shouldThrow
import io.kotlintest.specs.ShouldSpec

class SelectTest : ShouldSpec({

    should("throw UninitializedPropertyAccessException exception when FROM clause is missing") {
        shouldThrow<UninitializedPropertyAccessException> {
            select { }.build()
        }
    }

    should("return the correct SELECT statement when there is only one table and no columns specified") {
        //arrange
        val expectedSql = "SELECT *\n" +
                "FROM person"

        //execute
        val generatedSql = select { from(Person::class) }.build()

        //assert
        generatedSql shouldBe expectedSql
    }

    should("throw UninitializedPropertyAccessException exception when ON clause is missing") {
        shouldThrow<UninitializedPropertyAccessException> {
            select {
                (from(Person::class)
                        innerJoin PurchaseOrder::class)
            }.build()
        }
    }

    should("return the correct SELECT statement when there is join and no columns specified") {
        //arrange
        val expectedSql = "SELECT *\nFROM purchase_order\nINNER JOIN person ON (person.id = purchase_order.person_id)"

        //execute
        val generatedSql = select {
            (from(PurchaseOrder::class)
                    innerJoin Person::class on (Person::id eq PurchaseOrder::personId))
        }.build()

        //assert
        generatedSql shouldBe expectedSql
    }

    should("return the correct SELECT statement when there is only one table and columns specified") {
        //arrange
        val expectedSql = "SELECT " +
                "person.id, " +
                "person.name\n" +
                "FROM person"

        //execute
        val generatedSql = select {
            columns(Person::id, Person::name)
            from(Person::class)
        }.build()

        //assert
        generatedSql shouldBe expectedSql
    }

    should("return the correct SELECT statement when there is join and columns specified") {
        //arrange
        val expectedSql = "SELECT " +
                "purchase_order.id, " +
                "person.name\n" +
                "FROM purchase_order\n" +
                "INNER JOIN person ON (person.id = purchase_order.person_id)"

        //execute
        val generatedSql = select {
            columns(PurchaseOrder::id)
            columns(Person::name)
            (from(PurchaseOrder::class)
                    innerJoin Person::class on (Person::id eq PurchaseOrder::personId))
        }.build()

        //assert
        generatedSql shouldBe expectedSql
    }

    should("return the correct SELECT statement when there is join, columns, and where clause specified") {
        //arrange
        val expectedSql =
            "SELECT " +
                    "purchase_order.id, " +
                    "purchase_order.date, " +
                    "person.name, " +
                    "person.document, " +
                    "purchase_order_item.product_id, " +
                    "purchase_order_item.quantity, " +
                    "purchase_order_item.value, " +
                    "product.name\n" +
                    "FROM purchase_order\n" +
                    "INNER JOIN person ON (person.id = purchase_order.person_id)\n" +
                    "INNER JOIN purchase_order_item ON (purchase_order_item.order_id = purchase_order.id)\n" +
                    "LEFT JOIN product ON (product.id = purchase_order_item.product_id)\n" +
                    "WHERE purchase_order.date = \"01/06/2020\""

        //execute
        val generatedSql = select {
            columns(PurchaseOrder::id, PurchaseOrder::date)
            columns(Person::name, Person::document)
            columns(PurchaseOrderItem::productId, PurchaseOrderItem::quantity, PurchaseOrderItem::value)
            columns(Product::name)
            (from(PurchaseOrder::class)
                    innerJoin Person::class on (Person::id eq PurchaseOrder::personId)
                    innerJoin PurchaseOrderItem::class on (PurchaseOrderItem::orderId eq PurchaseOrder::id)
                    leftJoin Product::class on (Product::id eq PurchaseOrderItem::productId))
            where(PurchaseOrder::date eq "01/06/2020")
        }.build()

        //assert
        generatedSql shouldBe expectedSql
    }
})
