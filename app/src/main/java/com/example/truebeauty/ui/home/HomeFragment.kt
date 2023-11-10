package com.example.truebeauty.ui.home

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.truebeauty.Activity_productos
import com.example.truebeauty.databinding.FragmentHomeBinding

class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        val root: View = binding.root

        val buttonVerMas: Button = binding.verMasButton

        buttonVerMas.setOnClickListener {
            // Aquí inicia la nueva actividad o fragmento cuando se hace clic en el botón "Ver Más"
            // Por ejemplo, iniciar una nueva actividad:
            val intent = Intent(requireContext(), Activity_productos::class.java)
            startActivity(intent)

            // O iniciar un nuevo fragmento (asegúrate de tener un FragmentManager disponible):
            // val nuevoFragmento = NuevoFragmento()
            // fragmentManager?.beginTransaction()?.replace(R.id.contenedor, nuevoFragmento)?.commit()
        }

        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
