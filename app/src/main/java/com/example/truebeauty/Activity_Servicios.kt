package com.example.truebeauty

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.Navigation
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.truebeauty.adapter.AdapterServicios
import com.example.truebeauty.data.DataServicios

import com.google.android.material.textfield.TextInputEditText


class Activity_Servicios : AppCompatActivity() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var dataSource: DataServicios
    private lateinit var adapter: AdapterServicios

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_servicios)

        // Carga de datos desde DataServicios
        val myDataset = DataServicios(this).loadAffirmations()

        // Configuración del RecyclerView y su adaptador
        val recyclerView = findViewById<RecyclerView>(R.id.idCourseRV)
        val layoutManager = GridLayoutManager(this, 2)

        recyclerView.layoutManager = layoutManager
        adapter = AdapterServicios(this, myDataset)
        recyclerView.adapter = adapter
        recyclerView.setHasFixedSize(true)

        // Inicialización de DataServicios
        val dataSource = DataServicios(this)

        // Búsqueda dinámica en el RecyclerView a través de TextInputEditText
        val searchEditText = findViewById<TextInputEditText>(R.id.tietBuscar)
        searchEditText.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}

            override fun afterTextChanged(s: Editable?) {
                val query = s.toString()
                val filteredData = dataSource.search(query)
                adapter.updateData(filteredData)
            }
        })

//        // Encuentra la referencia al botón de devolución
//        val btnDevolver = findViewById<Button>(R.id.Devolver)
//
//        // Agrega un OnClickListener al botón de devolución
//        btnDevolver.setOnClickListener {
//            // Navegar de regreso a
//            Navigation.findNavController(this, R.id.navigation_dashboard).navigateUp()
//
//        }
    }
}
