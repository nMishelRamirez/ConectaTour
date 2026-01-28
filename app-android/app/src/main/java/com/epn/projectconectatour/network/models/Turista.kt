package com.epn.projectconectatour.network.models

import com.google.gson.annotations.SerializedName

data class Turista(
    @SerializedName("id") val id: Int,
    @SerializedName("nombre") val nombre: String,
    @SerializedName("celular") val celular: String,
    @SerializedName("correo") val correo: String,
    @SerializedName("contraseña") val contraseña: String
)