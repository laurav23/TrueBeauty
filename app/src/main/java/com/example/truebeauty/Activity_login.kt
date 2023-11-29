package com.example.truebeauty

import android.content.Intent
import android.content.pm.LauncherApps
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.inputmethod.InputMethodManager
import androidx.core.util.PatternsCompat
import com.example.truebeauty.ApiConexion.ApiConexion
import com.example.truebeauty.ImportClasses.popupalert
import com.example.truebeauty.Traer.LoginBring
import com.example.truebeauty.databinding.ActivityLoginBinding
import com.example.truebeauty.enviar.AdminUser
import com.example.truebeauty.enviar.LoginSend
import retrofit2.Call
import retrofit2.Response
import retrofit2.Callback

class Activity_login : AppCompatActivity() {
    private lateinit var binding: ActivityLoginBinding
    private val toast = popupalert()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val textView = binding.registrate
        textView.setOnClickListener {
            val intent = Intent(this, Activity_registro::class.java)
            startActivity(intent)
        }

        clickListener()
    }

    private fun clickListener() {
        binding.loginButton.setOnClickListener {
            validate()
            hideKeyboard()
            getInputs()
        }
    }

    private fun getInputs() {
        val email = binding.email.text.toString()
        val password = binding.password.text.toString()

        if (email.isNotEmpty() && password.isNotEmpty()) {
            loginUser(email, password)
        } else {
            toast.toastWarning(this, "Campos incompletos", "Completa los campos")
        }
    }

    private fun loginUser(email: String, password: String) {
        if (isEmailValid(email)) {
            val loginBring = LoginBring(email, password)
            val apiService = ApiConexion.getApiService()
            val apiCall = apiService.loginUser(loginBring)

            apiCall.enqueue(object : Callback<LoginSend> {
                override fun onResponse(call: Call<LoginSend>, response: Response<LoginSend>) {
                    if (response.isSuccessful) {
                        val loginResponse = response.body()
                        loginResponse?.let {
                            val userId = it.user.id
                            AdminUser.setUserId(userId)
                            move()
                        }
                    } else {
                        toast.toastError(this@Activity_login, "Error", "Correo o contraseña incorrectos")
                    }
                }

                override fun onFailure(call: Call<LoginSend>, t: Throwable) {
                    toast.toastError(this@Activity_login, "Error", "Ha ocurrido un error inesperado: ${t.localizedMessage}")
                }
            })
        } else {
            toast.toastError(this@Activity_login, "Error", "Correo inválido")
        }
    }

    private fun isEmailValid(email: String): Boolean {
        return PatternsCompat.EMAIL_ADDRESS.matcher(email).matches()
    }

    private fun validate() {
        val result = arrayOf(validateEmail(), validatePassword())
        if (false in result) {
            return
        }
    }

    private fun validateEmail(): Boolean {
        val email = binding.email.text.toString()
        return if (email.isEmpty()) {
            binding.email.error = "El campo del correo no puede estar vacío"
            false
        } else if (!PatternsCompat.EMAIL_ADDRESS.matcher(email).matches()) {
            binding.email.error = "Por favor ingresa un correo válido"
            false
        } else {
            binding.email.error = null
            true
        }
    }

    private fun validatePassword(): Boolean {
        val password = binding.password.text.toString()
        return if (password.isEmpty()) {
            binding.password.error = "El campo contraseña no debe estar vacío"
            false
        } else {
            binding.password.error = null
            true
        }
    }

    private fun hideKeyboard() {
        val imm = getSystemService(INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(binding.viewRoot.windowToken, 0)
    }

    private fun move() {
        startActivity(Intent(this, Home::class.java))
        finish()
    }


}