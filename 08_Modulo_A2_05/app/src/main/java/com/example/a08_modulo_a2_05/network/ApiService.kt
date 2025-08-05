package com.example.a08_modulo_a2_05.network

import com.example.a08_modulo_a2_05.AuthRequest
import com.example.a08_modulo_a2_05.AuthResponse
import retrofit2.http.Body
import retrofit2.http.POST

interface ApiService {
    @POST("worldskills/A2/generate_token")
    suspend fun gerarToken(@Body login: AuthRequest): AuthResponse
}