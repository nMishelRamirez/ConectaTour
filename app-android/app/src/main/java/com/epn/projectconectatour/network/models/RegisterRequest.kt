package com.epn.projectconectatour.network.models

data class RegisterRequest(
    val nombre: String,
    val celular: String,
    val correo: String,
    val contraseña: String
)