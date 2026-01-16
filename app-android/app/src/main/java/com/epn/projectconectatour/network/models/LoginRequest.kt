package com.epn.projectconectatour.network.models

data class LoginRequest(
    val correo: String,
    val contraseña: String
)