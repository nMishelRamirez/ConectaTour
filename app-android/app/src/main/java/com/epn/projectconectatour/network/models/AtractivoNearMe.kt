package com.epn.projectconectatour.network.models

data class AtractivoNearMe(
    val id: Int,
    val nombre: String,
    val imagenPrincipal: String,
    val categoria: String,
    val latitud: Double,
    val longitud: Double
)