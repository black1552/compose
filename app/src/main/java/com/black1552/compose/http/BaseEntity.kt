package com.black1552.compose.http

data class BaseEntity<T>(
    val code: Int,
    val data: T,
    val msg: String
)