package com.example.truebeauty.enviar

data class LoginSend (
    val message: String,
    val access_token: String,
    val token_type: String,
    val user: User
)