package com.example.truebeauty.enviar

data class ProductSend(
    val id: Int,
    val name: String,
    val precio: String,
    val image: String,
    val user_id: Int, //
    val created_at: String,
    val updated_at: String
)