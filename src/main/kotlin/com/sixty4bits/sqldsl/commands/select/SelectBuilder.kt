package com.sixty4bits.sqldsl.commands.select

class SelectBuilder(private val state: SelectState) {

    private fun whereClause(): String = state.where?.let { "\nWHERE $it" } ?: ""

    fun build(): String =
        "SELECT ${state.columns.joinToString().ifBlank { "*" }}\nFROM ${state.tableWrapper}${whereClause()}".trim()
}
