package com.sixty4bits.sqldsl.commands.select

import com.sixty4bits.sqldsl.condition.Condition

internal class OnClause(private val condition: Condition) {

    override fun toString(): String = "ON ($condition)"
}
