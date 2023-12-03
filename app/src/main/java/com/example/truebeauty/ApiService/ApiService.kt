package com.example.truebeauty.ApiService

import com.example.truebeauty.Traer.LoginBring
import com.example.truebeauty.Traer.TraerProductos
import retrofit2.Call
import com.example.truebeauty.Traer.TraerRegistro
import com.example.truebeauty.Traer.TraerUser
import com.example.truebeauty.enviar.EnviarRegistro
import com.example.truebeauty.enviar.LoginSend
import com.example.truebeauty.enviar.ProductSend
import com.example.truebeauty.enviar.Tip
import com.example.truebeauty.enviar.User
import com.google.android.gms.analytics.ecommerce.Product
import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.Response

import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.Multipart
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Part
import retrofit2.http.Path

// Interfaz que define los métodos para interactuar con la API
interface ApiService {


    @POST("/api/register")
    fun registeruser(@Body Registromodel: TraerRegistro): Call<EnviarRegistro> // Registra un usuario

    @POST("/api/login")
    fun loginUser(@Body loginRequest: LoginBring): Call<LoginSend> // Inicia sesión de usuario

    @GET("/api/users/{userId}")
    fun getUserProfile(@Path("userId") userId: String): Call<User> // Obtiene el perfil de un usuario por su ID

    @PUT("api/users/{userId}")
    fun updateProfile(@Body userRequest: TraerUser, @Path("userId") userId: String): Call<User> // Actualiza el perfil de usuario

    @DELETE("/api/users/{userId}")
    fun deleteUser(@Path("userId") userId: String): Call<Void> // Elimina un usuario por su ID

    @GET("/api/products")
    fun getProduct(): Call<List<ProductSend>> // Obtiene la lista de productos

    @GET("/api/tips")
    fun getTips(): Call<List<Tip>> // Obtiene la lista de consejos
}
