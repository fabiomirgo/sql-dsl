package coollibrary.sqldsl

import kotlin.reflect.KClass
import kotlin.reflect.KProperty1

class SelectCommand {

    internal val columns: MutableList<String> = mutableListOf()
    internal lateinit var tableWrapper: TableWrapper<*>
    internal var where: Condition? = null

    internal inline fun <reified T : Table> columns(vararg columns: KProperty1<T, *>) {
        this.columns.add(columns.joinToString { it.getColumnName() })
    }

    internal inline fun <reified T : Table> from(table: KClass<T>): TableWrapper<T> =
        TableWrapper(table).also { this.tableWrapper = it }

    internal fun where(condition: Condition) {
        this.where = condition
    }
}

class SelectBuilder(private val command: SelectCommand) {

    private fun whereClause(): String = command.where?.let { "\nWHERE $it" } ?: ""

    fun build(): String =
        "SELECT ${command.columns.joinToString().ifBlank { "*" }}\nFROM ${command.tableWrapper} ${whereClause()}"
}

fun select(block: SelectCommand.() -> Unit): SelectBuilder {
    val command = SelectCommand().apply(block)
    return SelectBuilder(command)
}

