package com.black1552.compose.http

data class PageInfoEntity<T>(
    val current_page: Int,
    val `data`: List<T>?,
    val last_page: Int,
    val per_page: String,
    val total: Int
)
