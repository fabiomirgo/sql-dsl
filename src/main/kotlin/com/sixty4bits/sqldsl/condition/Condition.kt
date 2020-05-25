package com.sixty4bits.sqldsl.condition

internal class Condition(private val type: ConditionType, private val value: String, private val other: String) {

    override fun toString() =
        "$value ${type.value} $other"
}
