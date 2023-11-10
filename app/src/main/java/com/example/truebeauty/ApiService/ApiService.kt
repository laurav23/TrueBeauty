package com.example.truebeauty.ApiService

import retrofit2.Call
import com.example.truebeauty.Traer.TraerRegistro
import com.example.truebeauty.enviar.EnviarRegistro
import com.example.truebeauty.enviar.Tip

import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface ApiService {
    @POST("/api/register")
    fun registeruser(@Body Registromodel: TraerRegistro): Call<EnviarRegistro>

    @GET("tips")
    fun getTips(): Call<List<Tip>>
}