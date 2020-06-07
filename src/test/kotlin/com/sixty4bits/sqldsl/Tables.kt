package com.sixty4bits.sqldsl

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
