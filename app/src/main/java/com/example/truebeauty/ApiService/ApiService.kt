package com.example.truebeauty.ApiService

import retrofit2.Call
import com.example.truebeauty.Traer.TraerRegistro
import com.example.truebeauty.enviar.EnviarRegistro

import retrofit2.http.Body
import retrofit2.http.POST

interface ApiService {
    @POST("/api/register")
    fun registeruser(@Body Registromodel: TraerRegistro): Call<EnviarRegistro>
}