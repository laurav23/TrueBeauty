package com.example.truebeauty.ui.editarper

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.truebeauty.ApiConexion.ApiConexion
import com.example.truebeauty.Traer.TraerUser
import com.example.truebeauty.enviar.AdminUser
import com.example.truebeauty.enviar.User
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class PerfilViewModel : ViewModel() {


    init {
        val userId = AdminUser.getUserId()
        Log.e("IDUSER", "${userId}")
        getUserProfile(userId.toString())
    }
    private val _text = MutableLiveData<String>().apply {
        value = "This is dashboard Fragment"
    }
    val text: LiveData<String> = _text

    private val userById = MutableLiveData<User>()
    val user: LiveData<User> get() = userById

    fun fetchUserProfile() {
        val userId = AdminUser.getUserId()
        getUserProfile(userId.toString())
    }

    private fun getUserProfile(userId: String) {
        val apiService = ApiConexion.getApiService()


        val userProfileCall: Call<User> = apiService.getUserProfile(userId)
        userProfileCall.enqueue(object : Callback<User> {
            override fun onResponse(call: Call<User>, response: Response<User>) {
                if (response.isSuccessful) {
                    val user = response.body()
                    user?.let {
                        userById.value = it
                        Log.d("User Profile Response", "User data: $user")
                    }
                }

            }

            override fun onFailure(call: Call<User>, t: Throwable) {
                Log.e("Error user", t.toString())
            }
        })
    }

    private val _deleteUserResult = MutableLiveData<Boolean>()
    val deleteUserResult: LiveData<Boolean> get() = _deleteUserResult

    fun deleteUser(userId: String) {
        val apiService = ApiConexion.getApiService()

        val deleteUserCall: Call<Void> = apiService.deleteUser(userId)
        deleteUserCall.enqueue(object : Callback<Void> {
            override fun onResponse(call: Call<Void>, response: Response<Void>) {
                if (response.isSuccessful) {
                    // Eliminación exitosa
                    Log.d("User deletion", "User deleted successfully")
                    _deleteUserResult.value = true
                } else {
                    // Manejar errores en la respuesta, si es necesario
                    Log.e(
                        "User deletion",
                        "Failed to delete user. Response code: ${response.code()}"
                    )
                    _deleteUserResult.value = false
                }
            }

            override fun onFailure(call: Call<Void>, t: Throwable) {
                // Manejar errores en la solicitud, si es necesario
                Log.e("User deletion", "Error deleting user", t)
                _deleteUserResult.value = false
            }
        })
    }

    private val _updateProfileResult = MutableLiveData<Boolean>()
    val updateProfileResult: LiveData<Boolean> get() = _updateProfileResult

    fun updateProfile(userRequest: TraerUser, userId: String) {
        val apiService = ApiConexion.getApiService()

        val userProfileCall: Call<User> = apiService.updateProfile(userRequest, userId)
        userProfileCall.enqueue(object : Callback<User> {
            override fun onResponse(call: Call<User>, response: Response<User>) {
                if (response.isSuccessful) {
                    _updateProfileResult.value = true
                } else {
                    _updateProfileResult.value = false
                }
            }

            override fun onFailure(call: Call<User>, t: Throwable) {
               Log.e("Error de actualización de perfil", "Error actualizando el perfil del usuario", t)
                _updateProfileResult.value = false
            }
        })
    }

}