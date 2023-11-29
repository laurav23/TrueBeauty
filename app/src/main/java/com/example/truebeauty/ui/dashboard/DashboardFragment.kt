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

class DashboardFragment : Fragment() {

    private var _binding: FragmentDashboardBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentDashboardBinding.inflate(inflater, container, false)
        val root: View = binding.root

        val buttonVerMas: Button = binding.verMasButton
        val buttonAgendar: Button = binding.ButtonAgendar

        buttonVerMas.setOnClickListener {
            val intent = Intent(requireContext(), Activity_Servicios::class.java)
            startActivity(intent)

        }
        buttonAgendar.setOnClickListener {
            val intent = Intent(requireContext(), Activity_CrearCita::class.java)
            startActivity(intent)

        }



        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
