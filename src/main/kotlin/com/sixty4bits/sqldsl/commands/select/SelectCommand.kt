package com.sixty4bits.sqldsl.commands.select

import com.sixty4bits.sqldsl.table.Table
import com.sixty4bits.sqldsl.table.TableWrapper
import com.sixty4bits.sqldsl.condition.Condition
import com.sixty4bits.sqldsl.table.getColumnName
import kotlin.reflect.KClass
import kotlin.reflect.KProperty1

class SelectCommand(private val state: SelectState) {

    internal inline fun <reified T : Table> columns(vararg columns: KProperty1<T, *>) {
        state.columns.add(columns.joinToString { it.getColumnName() })
    }

    internal inline fun <reified T : Table> from(table: KClass<T>): TableWrapper<T> =
        TableWrapper(table).also { state.tableWrapper = it }

    internal fun where(condition: Condition) {
        state.where = condition
    }
}
