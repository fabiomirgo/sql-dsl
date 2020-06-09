package com.sixty4bits.sqldsl.commands.select

import com.sixty4bits.sqldsl.condition.Condition
import com.sixty4bits.sqldsl.table.Table
import com.sixty4bits.sqldsl.table.TableWrapper
import com.sixty4bits.sqldsl.table.getColumnName
import kotlin.reflect.KClass
import kotlin.reflect.KProperty1

class SelectCommand {

    @PublishedApi
    internal val columns: MutableList<String> = mutableListOf()

    @PublishedApi
    internal lateinit var tableWrapper: TableWrapper<*>
    
    internal var where: Condition? = null

    inline fun <reified T : Table> columns(vararg columns: KProperty1<T, *>) {
        this.columns.add(columns.joinToString { it.getColumnName() })
    }

    inline fun <reified T : Table> from(table: KClass<T>): TableWrapper<T> =
        TableWrapper(table).also { this.tableWrapper = it }

    fun where(condition: Condition) {
        this.where = condition
    }
}
