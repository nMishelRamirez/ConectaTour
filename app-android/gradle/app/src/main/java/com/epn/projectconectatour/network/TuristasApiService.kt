package com.epn.projectconectatour.network

import com.epn.projectconectatour.network.models.*
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface TuristasApiService {

    @POST("api/turistas/register")
    suspend fun register(@Body request: RegisterRequest): Response<ApiResponse<Turista>>

    @POST("api/turistas/login")
    suspend fun login(@Body request: LoginRequest): Response<ApiResponse<Turista>>
}