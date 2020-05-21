package coollibrary.sqldsl

class Condition(private val type: ConditionType, private val valueA: String, private val valueB: String) {

    override fun toString() =
        "$valueA ${type.value} $valueB"
}
