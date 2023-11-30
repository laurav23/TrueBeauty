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

// ViewModel para la edición del perfil del usuario
class PerfilViewModel : ViewModel() {

    // Inicialización del ViewModel: obtención del perfil del usuario al iniciar
    init {
        val userId = AdminUser.getUserId()
        Log.e("IDUSER", "${userId}")
        getUserProfile(userId.toString())
    }

    // LiveData para el texto del fragmento (puede no ser necesario en este contexto)
    private val _text = MutableLiveData<String>().apply {
        value = "This is dashboard Fragment"
    }
    val text: LiveData<String> = _text

    // LiveData para almacenar y observar los detalles del usuario
    private val userById = MutableLiveData<User>()
    val user: LiveData<User> get() = userById

    // Función para obtener el perfil del usuario
    fun fetchUserProfile() {
        val userId = AdminUser.getUserId()
        getUserProfile(userId.toString())
    }

    // Función para obtener el perfil del usuario usando Retrofit
    private fun getUserProfile(userId: String) {
        val apiService = ApiConexion.getApiService()

        val userProfileCall: Call<User> = apiService.getUserProfile(userId)
        userProfileCall.enqueue(object : Callback<User> {
            override fun onResponse(call: Call<User>, response: Response<User>) {
                if (response.isSuccessful) {
                    val user = response.body()
                    user?.let {
                        userById.value = it // Actualiza el LiveData con los datos del usuario
                        Log.d("User Profile Response", "User data: $user")
                    }
                }
            }

            override fun onFailure(call: Call<User>, t: Throwable) {
                Log.e("Error user", t.toString())
            }
        })
    }

    // LiveData y función para observar y eliminar al usuario
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
                    // Manejar errores en la respuesta
                    Log.e("User deletion", "Failed to delete user. Response code: ${response.code()}")
                    _deleteUserResult.value = false
                }
            }

            override fun onFailure(call: Call<Void>, t: Throwable) {
                // Manejar errores en la solicitud
                Log.e("User deletion", "Error deleting user", t)
                _deleteUserResult.value = false
            }
        })
    }

    // LiveData y función para observar y actualizar el perfil del usuario
    private val _updateProfileResult = MutableLiveData<Boolean>()
    val updateProfileResult: LiveData<Boolean> get() = _updateProfileResult

    fun updateProfile(userRequest: TraerUser, userId: String) {
        val apiService = ApiConexion.getApiService()

        val userProfileCall: Call<User> = apiService.updateProfile(userRequest, userId)
        userProfileCall.enqueue(object : Callback<User> {
            override fun onResponse(call: Call<User>, response: Response<User>) {
                if (response.isSuccessful) {
                    _updateProfileResult.value = true // Actualización exitosa
                } else {
                    _updateProfileResult.value = false // Actualización fallida
                }
            }

            override fun onFailure(call: Call<User>, t: Throwable) {
                Log.e("Error de actualización de perfil", "Error actualizando el perfil del usuario", t)
                _updateProfileResult.value = false
            }
        })
    }
}
