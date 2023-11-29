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
import com.example.truebeauty.model.RecyclerServicos

class AdapterServicios(
    private val context: Context,
    private var dataset: List<RecyclerServicos>
):
    RecyclerView.Adapter<AdapterServicios.RecyclerViewHolder>() {


    class RecyclerViewHolder(private val view: View) :
        RecyclerView.ViewHolder(view) {
        val courseTV: TextView = view.findViewById(R.id.txt)
        val courseIV: ImageView = view.findViewById(R.id.imgv)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int):
            RecyclerViewHolder {

        val adapterLayout = LayoutInflater.from(parent.context).
        inflate(R.layout.card_layout, parent, false)
        return RecyclerViewHolder(adapterLayout)
    }


    override fun onBindViewHolder(holder: RecyclerViewHolder, position: Int) {
        val recyclerData = dataset[position]
        holder.courseTV.text = context.resources.getString(recyclerData.stringResourceId)
        holder.courseIV.setImageResource(recyclerData.imageResourceId)
    }

    override fun getItemCount(): Int {
        return dataset.size
    }

    fun updateData(newData: List<RecyclerServicos>) {
        dataset = newData
        notifyDataSetChanged()
    }

}