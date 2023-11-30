package com.example.truebeauty.Crud

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.truebeauty.R
import com.example.truebeauty.enviar.ProductSend

class CrudAdapter(private val contentList: List<ProductSend>) : RecyclerView.Adapter<CrudActivity>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int):  CrudActivity {
        val layoutInflater = LayoutInflater.from(parent.context)
        return CrudActivity(layoutInflater.inflate(R.layout.item_rv_product, parent, false))
    }


    override fun getItemCount(): Int =  contentList.size

    override fun onBindViewHolder(holder: CrudActivity, position: Int) {
        val item = contentList[position]
       // holder.render(item)
    }

}