package com.example.truebeauty.ui.dashboard

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import com.example.truebeauty.Activity_CrearCita
import com.example.truebeauty.Activity_Servicios
import com.example.truebeauty.databinding.FragmentDashboardBinding

// Fragmento para la sección de dashboard en la interfaz de usuario
class DashboardFragment : Fragment() {

    private var _binding: FragmentDashboardBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflar y vincular la vista del fragmento usando el databinding
        _binding = FragmentDashboardBinding.inflate(inflater, container, false)
        val root: View = binding.root

        // Obtener referencias a los botones en la vista
        val buttonVerMas: Button = binding.verMasButton


        // Configurar el evento clic para el botón "Ver Más" que abre la actividad de servicios
        buttonVerMas.setOnClickListener {
            val intent = Intent(requireContext(), Activity_Servicios::class.java)
            startActivity(intent)
        }



        return root // Devolver la vista raíz del fragmento
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null // Liberar la referencia al binding al destruir la vista del fragmento
    }
}
