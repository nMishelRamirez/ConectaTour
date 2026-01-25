package com.epn.projectconectatour.network.models

import com.google.gson.annotations.SerializedName

data class InformacionAdicional(
    @SerializedName("horario") val horario: String?,
    @SerializedName("precioEntrada") val precioEntrada: String?,
    @SerializedName("actividades") val actividades: String?,
    @SerializedName("imagenesAdicionales") val imagenesAdicionales: List<String>?
)
