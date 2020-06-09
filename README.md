# [sql-dsl]()
This project is an internal DSL for SQL written in Kotlin.
It makes possible to generate SELECT commands using only the data classes and their properties, taking advantage of several features that the language provides, without the need to write one SQL line directly.

## Motivation
The motivation behind this project is educational.
The main idea here is to show how it's possible to create a DSL in Kotlin, using the features that the language provides: lambda with receivers, extension functions, infix functions, inline functions, reified types, smart cast, internal modifier, etc.

## Usage example
```
package com.sixty4bits.sqldsl

import com.sixty4bits.sqldsl.commands.select
import com.sixty4bits.sqldsl.condition.eq
import com.sixty4bits.sqldsl.table.Table
import java.math.BigDecimal
import java.time.LocalDateTime

data class Person(val id: Long, val name: String, val document: String) : Table

data class Product(val id: String, val name: String) : Table

data class PurchaseOrder(val id: Long, val personId: Long, val value: BigDecimal, val date: LocalDateTime) : Table

data class PurchaseOrderItem(
    val id: Long,
    val orderId: Long,
    val productId: String,
    val quantity: Long,
    val value: BigDecimal
) : Table

fun generateSql(): String =
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
);
```
## Generated SELECT command
```
SELECT 
    purchase_order.id, 
    purchase_order.date, 
    person.name, 
    person.document, 
    purchase_order_item.product_id, 
    purchase_order_item.quantity, 
    purchase_order_item.value, 
    product.name
FROM purchase_order 
INNER JOIN person ON (person.id = purchase_order.person_id)
INNER JOIN purchase_order_item ON (purchase_order_item.order_id = purchase_order.id)
LEFT JOIN product ON (product.id = purchase_order_item.product_id) 
WHERE purchase_order.date = "01/06/2020"
```