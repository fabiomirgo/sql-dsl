package com.sixty4bits.sqldsl.condition

class Condition @PublishedApi internal constructor(
    private val type: ConditionType,
    private val value: String,
    private val other: String
) {

    override fun toString() =
        "$value ${type.value} $other"
}
