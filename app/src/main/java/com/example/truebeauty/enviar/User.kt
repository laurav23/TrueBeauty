package com.example.truebeauty.enviar

data class User (
    val id:  Int,
    val name: String,
    val email: String,
    val email_verified_at: String,
    val two_factor_confirmed_at: String,
    val current_team_id: String,
    val profile_photo_path: String,
    val profile_photo_url :String,
)