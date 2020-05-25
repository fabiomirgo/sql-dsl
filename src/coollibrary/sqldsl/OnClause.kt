package coollibrary.sqldsl

class OnClause(private val condition: Condition) {

    override fun toString(): String = "ON ($condition)"
}
