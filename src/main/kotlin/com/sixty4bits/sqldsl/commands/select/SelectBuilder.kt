package com.sixty4bits.sqldsl.commands.select

class SelectBuilder(private val command: SelectCommand) {

    private fun whereClause(): String = command.where?.let { "\nWHERE $it" } ?: ""

    fun build(): String =
        "SELECT ${command.columns.joinToString().ifBlank { "*" }}\nFROM ${command.tableWrapper}${whereClause()}".trim()
}
