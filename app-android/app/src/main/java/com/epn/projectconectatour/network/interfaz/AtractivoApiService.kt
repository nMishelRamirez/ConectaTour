package com.epn.projectconectatour.network

import com.epn.projectconectatour.network.models.*
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface AtractivoApiService {
    @GET("api/Atractivos/Home")
    fun getAtractivosHome(): Call<List<AtractivoHome>>

    @GET("api/Atractivos/NearMe")
    fun getAtractivosNearMe(): Call<List<AtractivoNearMe>>

    @GET("api/Atractivos/Detalle/{id}")
    fun getAtractivoDetalle(@Path("id") id: Int): Call<AtractivoDetalle>
}
