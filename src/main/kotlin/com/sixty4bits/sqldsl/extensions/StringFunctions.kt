package com.sixty4bits.sqldsl.extensions

internal fun String.toSnakeCase() =
    this.mapIndexed { index, c -> if (c.isUpperCase() && index > 0) "_$c" else c }
        .joinToString("")
        .toLowerCase()
