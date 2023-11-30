package com.example.truebeauty.ui.productos

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.truebeauty.R
import com.example.truebeauty.enviar.ProductSend

// Adaptador para la lista de productos
class ProductAdapter(private val contentList: List<ProductSend>) : RecyclerView.Adapter<ProductViewHolder>() {

    // Crea y devuelve una nueva instancia de ProductViewHolder
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return ProductViewHolder(layoutInflater.inflate(R.layout.item_content, parent, false))
    }

    // Devuelve la cantidad de elementos en la lista de productos
    override fun getItemCount(): Int = contentList.size

    // Vincula los datos de un elemento de la lista con las vistas en el ProductViewHolder
    override fun onBindViewHolder(holder: ProductViewHolder, position: Int) {
        val item = contentList[position]
        holder.render(item)
    }
}
