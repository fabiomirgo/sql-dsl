package com.sixty4bits.sqldsl.application.domain

import coollibrary.sqldsl.Table
import java.math.BigDecimal
import java.time.LocalDateTime

data class PurchaseOrder(val id: Long, val personId: Long, val value: BigDecimal, val date: LocalDateTime) : Table

