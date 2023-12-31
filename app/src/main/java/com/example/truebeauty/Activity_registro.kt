package com.example.truebeauty


import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

import androidx.appcompat.app.AlertDialog
import androidx.core.util.PatternsCompat
import com.example.truebeauty.ApiConexion.ApiConexion
import com.example.truebeauty.ImportClasses.popupalert
import com.example.truebeauty.Traer.TraerRegistro
import com.example.truebeauty.databinding.ActivityRegistroBinding
import com.example.truebeauty.enviar.EnviarRegistro
import retrofit2.Call
import java.util.regex.Pattern
import retrofit2.Callback
import retrofit2.Response

class Activity_registro : AppCompatActivity() {
    private lateinit var binding: ActivityRegistroBinding

    // Inicialización de una instancia de la clase popupalert
    private val toast = popupalert()

    // Patrón de contraseña
    private val PASSWORD_PATTERN = Pattern.compile(
        "^(?=.*[@#$%^&+=!|°()?¡¿*.:,])(?=\\S+$).{8,}\$"
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegistroBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Botón para registrar usuario
        binding.ReButton.setOnClickListener {
            if (validate()) {
                val name = binding.username.text.toString()
                val email = binding.Correo.text.toString()
                val password = binding.password.text.toString()
                val passwordConfirm = binding.configpassword.text.toString()
                registerUser(name, email, password, passwordConfirm)
            }
        }

        // Botón para redirigir al login
        binding.login.setOnClickListener {
            toast.toastSuccess(this, "TrueBeauty", "Iniciar sesión")
            startActivity(Intent(this, Activity_login::class.java))
        }
    }

    // Método para manejar la lógica de registro de usuario
    private fun registerUser(name: String, email: String, password: String, passwordConfirm: String) {
        val registerRequest = TraerRegistro(name, email, password, passwordConfirm)
        val apiCall = ApiConexion.getApiService().registeruser(registerRequest)
        apiCall.enqueue(object : Callback<EnviarRegistro> {
            override fun onResponse(call: Call<EnviarRegistro>, response: Response<EnviarRegistro>) {
                if (response.isSuccessful) {
                    move()
                } else {
                    toast.toastError(this@Activity_registro, "Error", "Sucedio un error inesperado o corrige tus credenciales")
                }
            }

            override fun onFailure(call: Call<EnviarRegistro>, t: Throwable) {
                // Manejar el caso de fallo
            }
        })
    }

    // Método para redirigir al usuario después del registro
    private fun move() {
        if (validate()) {
            startActivity(Intent(this@Activity_registro, Activity_login::class.java))
            toast.toastSuccess(this@Activity_registro, "TrueBeauty", "Registrado con éxito!")
        } else {
            showIncompleteFieldsAlert()
        }
    }

    // Método para mostrar alerta de campos incompletos
    private fun showIncompleteFieldsAlert() {
        val builder = AlertDialog.Builder(this@Activity_registro)
        builder.setTitle("Campos Incompletos")
        builder.setMessage("Por favor, completa todos los campos antes de continuar.")
        builder.setPositiveButton("OK") { dialog, _ ->
            dialog.dismiss()
        }

        val dialog = builder.create()
        dialog.show()
    }

    // Método para validar la información de registro ingresada por el usuario
    private fun validate(): Boolean {
        val email = binding.Correo.text.toString()
        val password = binding.password.text.toString()
        val passwordConfirm = binding.configpassword.text.toString()
        val name = binding.username.text.toString()

        val isEmailValid = !email.isEmpty() && PatternsCompat.EMAIL_ADDRESS.matcher(email).matches()
        val isPasswordValid = isPasswordValid(password, passwordConfirm)
        val isNameValid = !name.isEmpty()

        if (!isNameValid) {
            binding.username.error = "El campo no puede estar vacío"
            toast.toastWarning(this, "El campo no puede estar vacío", "holi")
        } else {
            binding.username.error = null
        }

        if (!isEmailValid) {
            binding.Correo.error = "Ingresa un correo válido"
        } else {
            binding.Correo.error = null
        }

        if (!isPasswordValid) {
            binding.password.error = "Las contraseñas deben coincidir y tener al menos 8 caracteres, incluyendo un caracter especial."
            binding.configpassword.error = "Las contraseñas deben coincidir y tener al menos 8 caracteres, incluyendo un caracter especial."
        } else {
            binding.password.error = null
            binding.configpassword.error = null
        }

        return isNameValid && isEmailValid && isPasswordValid
    }

    // Método para validar la fortaleza de la contraseña
    private fun isPasswordValid(password: String, passwordConfirm: String): Boolean {
        return password == passwordConfirm && password.length >= 8 && PASSWORD_PATTERN.matcher(password).matches()
    }
}