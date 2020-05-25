package com.sixty4bits.sqldsl.commands.select

import com.sixty4bits.sqldsl.condition.Condition
import com.sixty4bits.sqldsl.table.Table
import com.sixty4bits.sqldsl.table.TableWrapper
import com.sixty4bits.sqldsl.table.getColumnName
import kotlin.reflect.KClass
import kotlin.reflect.KProperty1

class SelectCommand(@PublishedApi internal val state: SelectState) {

    inline fun <reified T : Table> columns(vararg columns: KProperty1<T, *>) {
        state.columns.add(columns.joinToString { it.getColumnName() })
    }

    inline fun <reified T : Table> from(table: KClass<T>): TableWrapper<T> =
        TableWrapper(table).also { state.tableWrapper = it }

    fun where(condition: Condition) {
        state.where = condition
    }
}
