package com.example.a08_modulo_a2_05.network

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object API {
    val retrofit by lazy {
        Retrofit.Builder()
            .baseUrl("https://workspace.dinizeotecnologia.com.br/worldskills/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    val service by lazy {
        retrofit.create(ApiService::class.java)
    }
}