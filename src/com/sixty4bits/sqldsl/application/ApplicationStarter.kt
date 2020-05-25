package com.sixty4bits.sqldsl.application

import com.sixty4bits.sqldsl.application.domain.Person
import com.sixty4bits.sqldsl.application.domain.Product
import com.sixty4bits.sqldsl.application.domain.PurchaseOrder
import com.sixty4bits.sqldsl.application.domain.PurchaseOrderItem
import coollibrary.sqldsl.eq
import coollibrary.sqldsl.select

fun buildQuery() =
    select {
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

fun main() {
    print(buildQuery())
}
