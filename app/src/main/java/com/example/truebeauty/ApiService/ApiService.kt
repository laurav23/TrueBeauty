package com.example.truebeauty.ApiService

import com.example.truebeauty.Traer.LoginBring
import com.example.truebeauty.Traer.TraerProductos
import retrofit2.Call
import com.example.truebeauty.Traer.TraerRegistro
import com.example.truebeauty.Traer.TraerUser
import com.example.truebeauty.enviar.EnviarRegistro
import com.example.truebeauty.enviar.LoginSend
import com.example.truebeauty.enviar.ProductSend
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

interface ApiService {
    @POST("/api/register" )
    fun registeruser(@Body Registromodel: TraerRegistro): Call<EnviarRegistro>

    @POST("/api/login")
    fun loginUser(@Body loginRequest: LoginBring):Call<LoginSend>
    @GET("/api/users/{userId}")
    fun getUserProfile(@Path("userId") userId: String):Call<User>

    @PUT("api/users/{userId}")
    fun updateProfile(@Body userRequest: TraerUser, @Path("userId") userId: String): Call<User>
    @DELETE("/api/users/{userId}")
    fun deleteUser(@Path("userId") userId: String): Call<Void>

   @GET("/api/products")
    fun getProduct(): Call<List<ProductSend>>

    @GET("/api/products")
    suspend fun obtenerProducts(): Response<List<ProductSend>>

    @GET("/api/products/{id}")
    suspend fun obtenerProductPorId(
        @Path("id") id: Int
    ): Response<ProductSend>

    @POST("/api/products")
    suspend fun agregarProducts(
        @Body products: ProductSend
    ): Response<String>

    @PUT("/api/products/{id}")
    suspend fun actualizarProducts(
        @Path("id") id: Int,
        @Body products: ProductSend
    ): Response<String>

    @DELETE("/api/products/{id}")
    suspend fun borrarProducts(
        @Path("id") idUsuario: Int
    ): Response<String>






}