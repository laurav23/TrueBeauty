package com.example.truebeauty.enviar

object AdminUser {
    private var userId: Int = -1

    fun getUserId(): Int {
        return userId
    }

    fun setUserId(id: Int) {
        userId=id
        }
}