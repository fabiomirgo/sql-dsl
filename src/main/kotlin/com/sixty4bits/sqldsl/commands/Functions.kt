package com.sixty4bits.sqldsl.commands

import com.sixty4bits.sqldsl.commands.select.SelectBuilder
import com.sixty4bits.sqldsl.commands.select.SelectCommand

fun select(block: SelectCommand.() -> Unit): SelectBuilder =
    SelectBuilder(SelectCommand().apply(block))
