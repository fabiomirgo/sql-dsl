package com.sixty4bits.sqldsl.table

import com.sixty4bits.sqldsl.extensions.toSnakeCase
import kotlin.reflect.KProperty1

interface Table

@PublishedApi
internal inline fun <reified T : Table> KProperty1<T, *>.getColumnName() =
    "${T::class.java.simpleName}.${this.name}".toSnakeCase()
