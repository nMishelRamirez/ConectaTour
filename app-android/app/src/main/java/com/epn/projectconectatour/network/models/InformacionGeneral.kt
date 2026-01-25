package com.epn.projectconectatour.network.models

import com.google.gson.annotations.SerializedName

data class InformacionGeneral(
    @SerializedName("nombre") val nombre: String?,
    @SerializedName("descripcion") val descripcion: String?,
    @SerializedName("direccion") val direccion: String?
)
