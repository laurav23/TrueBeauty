package com.example.truebeauty.ApiConexion

import com.example.truebeauty.ApiService.ApiService
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object ApiConexion {
    // URL base para la conexión a la API
    val baseUrl = "http://192.168.0.6:8000"

    // Función privada que crea y devuelve una instancia de Retrofit
    private fun getRetrofit(): Retrofit {

        // Configuración del interceptor para registrar las solicitudes y respuestas HTTP
        val logger = HttpLoggingInterceptor()
        logger.setLevel(HttpLoggingInterceptor.Level.BODY)

        // Creación del cliente OkHttpClient con el interceptor configurado
        val client = OkHttpClient().newBuilder()
            .addInterceptor(logger)
            .build()

        // Creación de una instancia Retrofit con la URL base y el cliente OkHttpClient
        return Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create()) // Convierte JSON a objetos usando Gson
            .client(client) // Configura el cliente HTTP
            .baseUrl(baseUrl) // Configura la URL base
            .build()
    }

    // Función pública que devuelve una instancia de ApiService creada con Retrofit
    fun getApiService(): ApiService {
        return getRetrofit().create(ApiService::class.java) // Crea una implementación de la interfaz ApiService
    }
}
