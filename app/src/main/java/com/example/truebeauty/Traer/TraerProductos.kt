package com.example.truebeauty.Traer

import okhttp3.MultipartBody

data class TraerProductos (
    val name: String,
    val precio: String,
    val image: MultipartBody.Part?,
    val user_id: Int

)