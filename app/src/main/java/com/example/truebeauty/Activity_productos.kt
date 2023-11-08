package com.example.truebeauty

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.truebeauty.adapter.RecyclerViewAdapter
import com.example.truebeauty.data.DataSource
import com.google.android.material.textfield.TextInputEditText

class Activity_productos: AppCompatActivity() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var dataSource: DataSource
    private lateinit var adapter: RecyclerViewAdapter ///////////AGREGADO


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.fragment_home)

        val myDataset = DataSource(this).loadAffirmations()
        val recyclerView = findViewById<RecyclerView>(R.id.idCourseRV)

        val layoutManager = GridLayoutManager(this, 1)

        recyclerView.layoutManager = layoutManager
        recyclerView.adapter = RecyclerViewAdapter(this, myDataset)
        recyclerView.setHasFixedSize(true)

        ////////////////agregADO

        val dataSource = DataSource(this)
        val adapter = RecyclerViewAdapter(this, myDataset)

        recyclerView.adapter = adapter

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
    }
}