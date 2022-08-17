package dev.rrlabs.webflux.common.api

data class ApiError(
    val errors: List<ErrorDetail?>? = null
)
