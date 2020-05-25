package com.sixty4bits.sqldsl.commands

import com.sixty4bits.sqldsl.commands.select.SelectBuilder
import com.sixty4bits.sqldsl.commands.select.SelectCommand
import com.sixty4bits.sqldsl.commands.select.SelectState

fun select(block: SelectCommand.() -> Unit): SelectBuilder =
    SelectBuilder(SelectState().also { SelectCommand(it).apply(block) })
