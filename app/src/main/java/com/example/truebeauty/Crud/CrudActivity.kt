package com.example.truebeauty.Crud

import android.util.Log
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.truebeauty.ApiConexion.ApiConexion
import com.example.truebeauty.R
import com.example.truebeauty.databinding.ItemContentBinding
import com.example.truebeauty.enviar.ProductSend

class CrudActivity(view: View) : RecyclerView.ViewHolder(view) {

    private val binding: ItemContentBinding = ItemContentBinding.bind(view)
    fun render(contentModel: ProductSend) {
        binding.title.text = contentModel.name
        binding.Preci.text = contentModel.precio
        binding.description.text = contentModel.image


        // Verificar si la URL es para un video o una imagen
        if (contentModel.image?.matches(Regex(".+\\.(mp4|avi|mov|mkv|wmv|flv|webm)$", RegexOption.IGNORE_CASE)) == true) {
            // Si es un video, usar VideoView
            Log.d("imagenUrl", ApiConexion.baseUrl + contentModel.image)
            binding.imageView.visibility = View.GONE
        } else {
            // Si es una imagen, usar Glide para cargar la imagen en el ImageView
            Log.d("ImageUrl", ApiConexion.baseUrl + contentModel.image)
            binding.imageView.visibility = View.VISIBLE
            Glide.with(itemView.context)
                .load(ApiConexion.baseUrl + contentModel.image)
                .placeholder(R.drawable.logo)
                .into(binding.imageView)
        }
    }
}