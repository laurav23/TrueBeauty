package com.example.truebeauty

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.truebeauty.databinding.FragmentDashboardBinding

class Activity_servicios : AppCompatActivity() {
    private lateinit var binding: FragmentDashboardBinding
    private lateinit var adaptador: Adaptadorservicios

    var listaUsuarios = arrayListOf<servicios>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = FragmentDashboardBinding.inflate(layoutInflater)
        setContentView(binding.root)

        llenarLista()
        setupRecyclerView()

        binding.etBuscador.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) { }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) { }

            override fun afterTextChanged(p0: Editable?) {
                filtrar(p0.toString())
            }

        })
    }

    fun llenarLista() {
        listaUsuarios.add(servicios("Ana", "12.345",""))
        listaUsuarios.add(servicios("Luis", "56.789",""))
        listaUsuarios.add(servicios("Sergio", "98.765",","))
        listaUsuarios.add(servicios("Cesar", "54.321",""))
        listaUsuarios.add(servicios("Laura", "82.123",""))
    }

    fun setupRecyclerView() {
        binding.rvLista.layoutManager = LinearLayoutManager(this)
        adaptador = Adaptadorservicios(listaUsuarios)
        binding.rvLista.adapter = adaptador
    }

    fun filtrar(texto: String) {
        var listaFiltrada = arrayListOf<servicios>()

        listaUsuarios.forEach {
            if (it.nombre.lowercase().contains(texto.lowercase())) {
                listaFiltrada.add(it)
            }
        }

        adaptador.filtrar(listaFiltrada)
    }
}