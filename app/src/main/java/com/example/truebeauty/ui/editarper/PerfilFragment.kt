package com.example.truebeauty.ui.editarper

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.widget.AppCompatButton
import androidx.fragment.app.Fragment
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.example.truebeauty.Activity_login
import com.example.truebeauty.ApiConexion.ApiConexion
import com.example.truebeauty.ImportClasses.popupalert
import com.example.truebeauty.R
import com.example.truebeauty.Traer.TraerUser
import com.example.truebeauty.databinding.ActivityLoginBinding
import com.example.truebeauty.databinding.ActivityPerfil1Binding
import com.example.truebeauty.enviar.AdminUser
import com.example.truebeauty.enviar.User
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class PerfilFragment : Fragment() {

    private val toast = popupalert()
    private var _binding: ActivityPerfil1Binding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = ActivityPerfil1Binding.inflate(inflater, container, false)
        val root: View = binding.root

        val editarPerfil = ViewModelProvider(this)[PerfilViewModel::class.java]
        editarPerfil.user.observe(viewLifecycleOwner) { newData ->
            binding.name.text = newData.name
            binding.email.text = newData.email

            context?.let {
                Glide.with(it)
                    .load(newData.profile_photo_url)
                    .placeholder(R.drawable.logo) // Imagen de carga mientras se carga la imagen
                    .error(R.drawable.logo) // Imagen de error si no se puede cargar la imagen
                    .into(binding.profileImage)
            }
        }

        binding.btnEditarPerfil.setOnClickListener {
            mostraDialogo()
        }

        return root
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        // Asociar el ViewModel
        val editPerfilModel = ViewModelProvider(this)[PerfilViewModel::class.java]

        // Guardar referencia al contexto
        val context = requireContext()

        // Observar el resultado de la eliminación
        editPerfilModel.deleteUserResult.observe(viewLifecycleOwner) { result ->
            if (result) {
                // La eliminación fue exitosa
                toast.toastSuccess(context, "TrueBeauty", "Usuario eliminado con éxito")
                binding.root.animate().alpha(0f).setDuration(1000).withEndAction {
                    // Usar startActivity con el contexto
                    startActivity(Intent(context, ActivityLoginBinding::class.java))

                    // Finalizar la actividad o fragmento actual después de la animación
                    requireActivity().finish()
                }
            } else {
                // La eliminación falló
                toast.toastSuccess(context, "TrueBeauty", "Error al eliminar el usuario")
            }
        }

        // Configurar el clic del botón para iniciar la eliminación
        binding.btnDeleteUser.setOnClickListener {
            val userId = AdminUser.getUserId()
            editPerfilModel.deleteUser(userId.toString())
        }
        binding.logout.setOnClickListener {
            logoutUser()
        }
    }





    private val _updateProfileResult = MutableLiveData<Boolean>()
    val updateProfileResult: LiveData<Boolean> get() = _updateProfileResult

    // Función para actualizar el perfil del usuario
    fun updateProfile(userRequest: TraerUser, userId: String) {
        val apiService = ApiConexion.getApiService()

        val userProfileCall: Call<User> = apiService.updateProfile(userRequest, userId)
        userProfileCall.enqueue(object : Callback<User> {
            override fun onResponse(call: Call<User>, response: Response<User>) {
                if (response.isSuccessful) {
                    _updateProfileResult.value = true
                    // Mostrar tostada de éxito
                    toast.toastSuccess(context,"TrueBeauty", "Perfil actualizado exitosamente")
                } else {
                    _updateProfileResult.value = false
                    // Mostrar tostada de error
                    toast.toastSuccess(context,"TrueBeauty", "Error al actualizar el perfil")
                }
            }

            override fun onFailure(call: Call<User>, t: Throwable) {
                _updateProfileResult.value = false
                // Mostrar tostada de error

                toast.toastSuccess(context,"TrueBeauty", "Error al actualizar el perfil")
            }
        })
    }


    private fun showToast(message: String) {
        Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
    }


    private fun mostraDialogo() {
        val builder = AlertDialog.Builder(requireContext())
        val inflater = LayoutInflater.from(requireContext())
        val dialogoView = inflater.inflate(R.layout.model_perfil, null)
        builder.setView(dialogoView)

        val editText1 = dialogoView.findViewById<EditText>(R.id.name)
        val editText2 = dialogoView.findViewById<EditText>(R.id.email)

        // Obtén el modelo actual del usuario desde el ViewModel
        val editPerfilModel = ViewModelProvider(this)[PerfilViewModel::class.java]
        val usuarioActual = editPerfilModel.user.value

        // Si el usuario actual no es nulo, establece los valores en los campos del diálogo
        usuarioActual?.let {
            editText1.setText(it.name)
            editText2.setText(it.email)
        }

        builder.setTitle("TrueBeauty")
            .setPositiveButton("Aceptar") { _dialog, which ->
                val input1 = editText1.text.toString()
                val input2 = editText2.text.toString()
                // Obtén el ID del usuario de alguna manera (dependiendo de tu implementación)
                val userId = AdminUser.getUserId().toString() // Convierte a String

                // Llamas a la función para actualizar el perfil con el ID del usuario convertido a String
                updateProfile(TraerUser(input1, input2), userId)
                editPerfilModel.fetchUserProfile()
            }
            .setNegativeButton("Cancelar") { dialog, which ->
                // Muestra una alerta de error o un Toast indicando que la operación fue cancelada
                toast.toastError(requireContext(), "TrueBeauty", "Operación cancelada")

            }

        val alertDialog = builder.create()

        alertDialog.show()
    }
    private fun logoutUser() {
        val sharedPreferences = requireActivity().getSharedPreferences("MyPrefs", Context.MODE_PRIVATE)
        val editor = sharedPreferences.edit()
        editor.remove("userToken") // Elimina el token del usuario u otros datos relacionados con la sesión
        editor.apply()

        // Aquí puedes realizar otras acciones necesarias al cerrar sesión, como redirigir a la pantalla de inicio de sesión
        val intent = Intent(requireContext(), Activity_login::class.java)
        startActivity(intent)
        requireActivity().finish() // Finaliza la actividad actual para evitar que el usuario retroceda a la pantalla anterior
    }




}

