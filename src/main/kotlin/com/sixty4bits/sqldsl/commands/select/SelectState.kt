package com.sixty4bits.sqldsl.commands.select

import com.sixty4bits.sqldsl.condition.Condition
import com.sixty4bits.sqldsl.table.TableWrapper

class SelectState {

    @PublishedApi
    internal val columns: MutableList<String> = mutableListOf()

    @PublishedApi
    internal lateinit var tableWrapper: TableWrapper<*>

    @PublishedApi
    internal var where: Condition? = null
}
