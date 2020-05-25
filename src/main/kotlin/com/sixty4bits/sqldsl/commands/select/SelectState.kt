package com.sixty4bits.sqldsl.commands.select

import com.sixty4bits.sqldsl.table.TableWrapper
import com.sixty4bits.sqldsl.condition.Condition

class SelectState {

    internal val columns: MutableList<String> = mutableListOf()
    internal lateinit var tableWrapper: TableWrapper<*>
    internal var where: Condition? = null
}
