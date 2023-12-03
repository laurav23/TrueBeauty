package com.example.truebeauty.ui.productos

import android.util.Log
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.truebeauty.ApiConexion.ApiConexion
import com.example.truebeauty.R
import com.example.truebeauty.databinding.ItemContentBinding
import com.example.truebeauty.enviar.ProductSend

class ProductViewHolder(view: View) : RecyclerView.ViewHolder(view) {

    // Referencia al binding generado para el elemento de la vista
    private val binding: ItemContentBinding = ItemContentBinding.bind(view)

    // Función para renderizar los datos del producto en la vista
    fun render(contentModel: ProductSend) {
        // Asignar el nombre, precio y URL de la imagen del producto a los elementos de la vista
        binding.title.text = contentModel.name
        binding.Preci.text = contentModel.price.toString()
        binding.description.text = contentModel.description
    }
}
