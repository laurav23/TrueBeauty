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
        binding.Preci.text = contentModel.precio
        binding.description.text = contentModel.image

        // Verificar si la URL es para un video o una imagen
        if (contentModel.image?.matches(Regex(".+\\.(mp4|avi|mov|mkv|wmv|flv|webm)$", RegexOption.IGNORE_CASE)) == true) {
            // Si es un video, ocultar el ImageView y mostrar el Log con la URL del video
            Log.d("imagenUrl", ApiConexion.baseUrl + contentModel.image)
            binding.imageView.visibility = View.GONE
        } else {
            // Si es una imagen, cargar la imagen usando Glide en el ImageView y mostrar el Log con la URL de la imagen
            Log.d("ImageUrl", ApiConexion.baseUrl + contentModel.image)
            binding.imageView.visibility = View.VISIBLE
            Glide.with(itemView.context)
                .load(ApiConexion.baseUrl + contentModel.image)
                .placeholder(R.drawable.logo) // Mostrar un placeholder mientras se carga la imagen
                .into(binding.imageView) // Asignar la imagen al ImageView
        }
    }
}
