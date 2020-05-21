package coollibrary.sqldsl

import coollibrary.sqldsl.functions.toSnakeCase
import kotlin.reflect.KClass

open class TableWrapper<T : Table>(
    private val table: KClass<T>,
    private val joinType: JoinType? = null
) {

    private var next: TableWrapper<*>? = null

    internal inline infix fun <reified T : Table> innerJoin(table: KClass<T>) =
        JoinWrapper(table, JoinType.INNER)
            .also { next = it }

    internal inline infix fun <reified T : Table> leftJoin(table: KClass<T>) =
        JoinWrapper(table, JoinType.LEFT)
            .also { next = it }

    private fun onClause(): String = if (this is JoinWrapper) "$onClause" else ""

    override fun toString(): String =
        "${joinType?.value ?: ""} ${table.simpleName?.toSnakeCase()} ${onClause()}\n${next ?: ""}".trim()
}

internal class JoinWrapper<T : Table>(table: KClass<T>, type: JoinType) : TableWrapper<T>(table, type) {

    lateinit var onClause: OnClause

    internal infix fun on(condition: Condition): TableWrapper<T> {
        OnClause(condition).also { onClause = it }
        return this
    }
}
