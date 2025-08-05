package com.example.a08_modulo_a2_05

data class AuthRequest(
    val email: String,
    val senha: String
)

data class AuthResponse(
    val token: String
)