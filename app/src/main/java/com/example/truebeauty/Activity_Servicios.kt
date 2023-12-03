package com.example.truebeauty

import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.widget.Button
import android.widget.Toast
//import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContentProviderCompat.requireContext
//import androidx.navigation.Navigation
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.truebeauty.adapter.AdapterServicios
import com.example.truebeauty.data.DataServicios
import com.example.truebeauty.databinding.ActivityServiciosBinding
import com.example.truebeauty.databinding.FragmentDashboardBinding

import com.google.android.material.textfield.TextInputEditText


class Activity_Servicios : AppCompatActivity() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var dataSource: DataServicios
    private lateinit var adapter: AdapterServicios
    private lateinit var binding: ActivityServiciosBinding // Declaración del binding

    // Flag para indicar si el usuario ha iniciado sesión
    private var usuarioHaIniciadoSesion = false


    // Método llamado después de un inicio de sesión exitoso
    private fun handleLoginSuccess() {
        usuarioHaIniciadoSesion = true
    }

    // Método llamado después de cerrar sesión
    private fun handleLogout() {
        usuarioHaIniciadoSesion = false
    }

    // Configuración del botón de confirmación
    private fun configurarBotonAgendar() {
        val btnConfirm = findViewById<Button>(R.id.ButtonAgendar)

        btnConfirm.setOnClickListener {
            if (usuarioHaIniciadoSesion) {
                // Mostrar mensaje y finalizar la actividad si el usuario ha iniciado sesión
                Toast.makeText(applicationContext, "Cita creada exitosamente", Toast.LENGTH_SHORT).show()
                val intent = Intent(this, Home::class.java)
                startActivity(intent)
                finish()
            } else {
                // Redirigir al usuario al inicio de sesión si no ha iniciado sesión
                val intent = Intent(this, Activity_login::class.java)
                startActivity(intent)
                finish() // Finalizar esta actividad para evitar duplicados después del inicio de sesión
            }
        }
    }







    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityServiciosBinding.inflate(layoutInflater) // Inicialización del binding
        setContentView(binding.root)

        // Carga de datos desde DataServicios
        val myDataset = DataServicios(this).loadAffirmations()

        // Configuración del RecyclerView y su adaptador
        recyclerView = binding.idCourseRV // Uso del RecyclerView desde el binding
        val layoutManager = GridLayoutManager(this, 2)
        val buttonAgendar: Button = binding.ButtonAgendar

        recyclerView.layoutManager = layoutManager
        adapter = AdapterServicios(this, myDataset)
        recyclerView.adapter = adapter
        recyclerView.setHasFixedSize(true)

        // Inicialización de DataServicios
        dataSource = DataServicios(this)

        // Búsqueda dinámica en el RecyclerView a través de TextInputEditText
        val searchEditText = binding.tietBuscar // Uso de TextInputEditText desde el binding
        searchEditText.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}

            override fun afterTextChanged(s: Editable?) {
                val query = s.toString()
                val filteredData = dataSource.search(query)
                adapter.updateData(filteredData)
            }
        })

        // Configurar el evento clic para el botón "Agendar" que abre la actividad de crear cita
        buttonAgendar.setOnClickListener {
            val intent = Intent(this@Activity_Servicios, Activity_CrearCita::class.java)
            startActivity(intent)
        }

        // Obtener información sobre si el usuario ha iniciado sesión o no
        usuarioHaIniciadoSesion = intent.getBooleanExtra("Usuario a IniciadoSesion", false)
        configurarBotonAgendar()
    }
}
