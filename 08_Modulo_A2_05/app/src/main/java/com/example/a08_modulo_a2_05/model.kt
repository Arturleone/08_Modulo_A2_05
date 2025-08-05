package com.example.a08_modulo_a2_05

data class AuthRequest(
    val email: String,
    val password: String
)

data class AuthResponse(
    val token: String
)

data class puxarToken(
    val token: String
)

data class validarToken(
    val valid: Boolean
)

data class motdGerado(
    val MOTD: String
)

data class SchoolListResponse(
    val total: Int,
    val escolas: List<Escola>
)

data class Escola(
    val id: Int,
    val nome: String,
    val avaliacao: Double,
    val image: String,
    val latitude: Double,
    val longitude: Double
)