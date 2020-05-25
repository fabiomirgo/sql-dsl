package com.sixty4bits.sqldsl.table

import com.sixty4bits.sqldsl.commands.select.JoinType
import com.sixty4bits.sqldsl.commands.select.OnClause
import com.sixty4bits.sqldsl.condition.Condition
import com.sixty4bits.sqldsl.extensions.toSnakeCase
import kotlin.reflect.KClass

internal open class TableWrapper<T : Table>(
    private val table: KClass<T>,
    private val joinType: JoinType? = null
) {

    private var next: TableWrapper<*>? = null

    internal inline infix fun <reified T : Table> innerJoin(table: KClass<T>) =
        JoinWrapper(
            table,
            JoinType.INNER
        ).also { next = it }

    internal inline infix fun <reified T : Table> leftJoin(table: KClass<T>) =
        JoinWrapper(
            table,
            JoinType.LEFT
        ).also { next = it }

    private fun onClause(): String = if (this is JoinWrapper) "$onClause" else ""

    private fun nextJoin(): String = if (next != null) "\n$next" else ""

    override fun toString(): String =
        "${joinType?.value ?: ""} ${table.simpleName?.toSnakeCase()} ${onClause()}".trim() + nextJoin()
}

internal class JoinWrapper<T : Table>(table: KClass<T>, type: JoinType) : TableWrapper<T>(table, type) {

    lateinit var onClause: OnClause

    internal infix fun on(condition: Condition): TableWrapper<T> {
        OnClause(condition).also { onClause = it }
        return this
    }
}
