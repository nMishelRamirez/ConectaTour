package com.epn.projectconectatour.network

import com.epn.projectconectatour.network.models.*
import retrofit2.Response
import retrofit2.http.*

interface TuristasApiService {
    @POST("api/turistas/register")
    suspend fun register(@Body request: RegisterRequest): Response<ApiResponse<Turista>>

    @POST("api/turistas/login")
    suspend fun login(@Body request: LoginRequest): Response<ApiResponse<Turista>>

    // SE AGREGA "perfil/" A LA RUTA
    @GET("api/turistas/perfil/{id}")
    suspend fun getTurista(@Path("id") id: Int): Response<ApiResponse<Turista>>

    // SE AGREGA "perfil/" A LA RUTA
    @PUT("api/turistas/perfil/{id}")
    suspend fun updateTurista(@Path("id") id: Int, @Body turista: Turista): Response<ApiResponse<Turista>>
}//404