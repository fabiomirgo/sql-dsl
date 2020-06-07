package com.sixty4bits.sqldsl.condition

import com.sixty4bits.sqldsl.table.Table
import com.sixty4bits.sqldsl.table.getColumnName
import kotlin.reflect.KProperty1

inline infix fun <reified T : Table, reified O : Table> KProperty1<T, *>.eq(other: KProperty1<O, *>): Condition =
    Condition(
        ConditionType.EQ,
        this.getColumnName(),
        other.getColumnName()
    )

inline infix fun <reified T : Table> KProperty1<T, *>.eq(value: String): Condition =
    Condition(
        ConditionType.EQ,
        this.getColumnName(),
        "\"$value\""
    )
