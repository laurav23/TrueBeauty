package com.example.truebeauty.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.truebeauty.R
import com.example.truebeauty.model.RecyclerData

class RecyclerViewAdapter (
    private val context: Context,
    private var dataset: List<RecyclerData>
) : RecyclerView.Adapter<RecyclerViewAdapter.RecyclerViewHolder>() {

    // ViewHolder para mantener las referencias a las vistas de cada elemento
    class RecyclerViewHolder(private val view: View) :
        RecyclerView.ViewHolder(view) {
        val courseTV: TextView = view.findViewById(R.id.txt) // Referencia al TextView en el layout
        val courseIV: ImageView = view.findViewById(R.id.imgv) // Referencia al ImageView en el layout
    }

    // Crea un ViewHolder al inflar el layout del elemento del RecyclerView
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerViewHolder {
        val adapterLayout = LayoutInflater.from(parent.context)
            .inflate(R.layout.card_sercivios, parent, false) // Infla el layout del elemento
        return RecyclerViewHolder(adapterLayout) // Retorna el ViewHolder con el layout inflado
    }

    // Vincula los datos a las vistas en un elemento del RecyclerView
    override fun onBindViewHolder(holder: RecyclerViewHolder, position: Int) {
        val recyclerData = dataset[position] // Obtiene los datos en la posición dada
        holder.courseTV.text = context.resources.getString(recyclerData.stringResourceId) // Asigna el texto
        holder.courseIV.setImageResource(recyclerData.imageResourceId) // Asigna la imagen
    }

    // Devuelve la cantidad de elementos en el dataset
    override fun getItemCount(): Int {
        return dataset.size
    }

    // Actualiza el dataset con nuevos datos y notifica al adaptador sobre el cambio
    fun updateData(newData: List<RecyclerData>) {
        dataset = newData // Actualiza el dataset
        notifyDataSetChanged() // Notifica al adaptador sobre el cambio en los datos
    }
}
