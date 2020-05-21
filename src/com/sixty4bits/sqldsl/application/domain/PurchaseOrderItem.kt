package com.sixty4bits.sqldsl.application.domain

import coollibrary.sqldsl.Table
import java.math.BigDecimal

data class PurchaseOrderItem(
    val id: Long,
    val orderId: Long,
    val productId: String,
    val quantity: Long,
    val value: BigDecimal
) : Table

