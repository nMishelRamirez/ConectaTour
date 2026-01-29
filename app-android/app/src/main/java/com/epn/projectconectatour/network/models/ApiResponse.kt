package com.epn.projectconectatour.network.models

data class ApiResponse<T>(
    val estado: String,
    val codigo: String,
    val mensaje: String,
    val datos: T?
)