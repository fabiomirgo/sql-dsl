package com.sixty4bits.sqldsl.extensions

@PublishedApi
internal fun String.toSnakeCase() =
    this.mapIndexed { index, c -> if (c.isUpperCase() && index > 0) "_$c" else c.toString() }
        .joinToString("")
        .toLowerCase()
