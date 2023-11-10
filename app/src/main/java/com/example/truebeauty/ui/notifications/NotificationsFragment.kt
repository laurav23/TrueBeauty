package com.example.truebeauty.ui.notifications

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.truebeauty.Activity_perfil
import com.example.truebeauty.Activity_rating
import com.example.truebeauty.BasicView
import com.example.truebeauty.R
import com.example.truebeauty.databinding.FragmentNotificationsBinding

class NotificationsFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_notifications, container, false)

        val btnMostrarRating: Button = view.findViewById(R.id.btnMostrarRating)
        val btnMostrarPerfil: Button = view.findViewById(R.id.btnMostrarPerfil)
        val btnNosotros: Button = view.findViewById(R.id.btnNosotros)
        btnMostrarRating.setOnClickListener {
            // Lanzar la actividad Rating
            val intent = Intent(requireContext(),Activity_rating::class.java)
            startActivity(intent)
        }

        btnMostrarPerfil.setOnClickListener {
            // Lanzar la actividad Perfil
            val intent = Intent(requireContext(), Activity_perfil::class.java)
            startActivity(intent)
        }
        btnNosotros.setOnClickListener {
            // Lanzar la actividad Perfil
            val intent = Intent(requireContext(), BasicView::class.java)
            startActivity(intent)
        }

        return view
    }
}