package coollibrary.sqldsl

import coollibrary.sqldsl.functions.toSnakeCase
import kotlin.reflect.KProperty1

interface Table

inline fun <reified T : Table> KProperty1<T, *>.getColumnName() =
    "${T::class.java.simpleName}.${this.name}".toSnakeCase()

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
