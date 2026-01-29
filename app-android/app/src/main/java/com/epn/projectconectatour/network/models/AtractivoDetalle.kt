package com.epn.projectconectatour.network.models

import com.google.gson.annotations.SerializedName

data class AtractivoDetalle(
    @SerializedName("informacionGeneral") val informacionGeneral: InformacionGeneral?,
    @SerializedName("informacionAdicional") val informacionAdicional: InformacionAdicional?,
    @SerializedName("imagenPrincipal") val imagenPrincipal: String?,
    @SerializedName("categoria") val categoria: String?
)
