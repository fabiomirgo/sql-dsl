package com.sixty4bits.sqldsl.application.domain

import coollibrary.sqldsl.Table

data class Person(val id: Long, val name: String, val document: String) : Table

