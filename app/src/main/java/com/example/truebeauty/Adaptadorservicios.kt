package com.example.truebeauty

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class Adaptadorservicios (
var listaUsuarios: ArrayList<servicios>
): RecyclerView.Adapter<Adaptadorservicios.ViewHolder>() {

    class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        val tvNombre = itemView.findViewById(R.id.tvNombre) as TextView
        val tvTelefono = itemView.findViewById(R.id.tvTelefono) as TextView
        val tvEmail = itemView.findViewById(R.id.tvEmail) as TextView
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val vista = LayoutInflater.from(parent.context).inflate(R.layout.fragment_dashboard, parent, false)
        return ViewHolder(vista)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val usuario = listaUsuarios[position]

        holder.tvNombre.text = usuario.nombre
        holder.tvTelefono.text = usuario.precio
        holder.tvEmail.text = usuario.servicio
    }

    override fun getItemCount(): Int {
        return listaUsuarios.size
    }

    fun filtrar(listaFiltrada: ArrayList<servicios>) {
        this.listaUsuarios = listaFiltrada
        notifyDataSetChanged()
    }
}