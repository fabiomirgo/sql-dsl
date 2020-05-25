package com.sixty4bits.sqldsl.commands.select

internal enum class JoinType(val value: String) {
    INNER("INNER JOIN"),
    LEFT("LEFT JOIN")
}
