package dev.rrlabs.webflux.common.api

data class ErrorDetail(
    val code: String? = null,
    val description: String? = null,
    val field: String? = null
)
