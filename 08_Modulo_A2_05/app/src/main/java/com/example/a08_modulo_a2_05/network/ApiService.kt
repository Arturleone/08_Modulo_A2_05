package com.example.a08_modulo_a2_05.network

import com.example.a08_modulo_a2_05.AuthRequest
import com.example.a08_modulo_a2_05.AuthResponse
import com.example.a08_modulo_a2_05.SchoolListResponse
import com.example.a08_modulo_a2_05.motdGerado
import com.example.a08_modulo_a2_05.puxarToken
import com.example.a08_modulo_a2_05.validarToken
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface ApiService {
    @POST("worldskills/A2/generate_token")
    suspend fun gerarToken(@Body login: AuthRequest): AuthResponse

    @POST("worldskills/jwt/validate_token")
    suspend fun validarToken(@Body token: puxarToken): validarToken

    @GET("worldskills/A2/motd")
    suspend fun gerarMOTD(): motdGerado

    @GET("worldskills/A2/school_list")
    suspend fun getEscolas(): SchoolListResponse
}