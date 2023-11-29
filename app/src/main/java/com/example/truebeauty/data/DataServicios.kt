package com.example.truebeauty.data

import android.content.Context
import com.example.truebeauty.R
import com.example.truebeauty.model.RecyclerData
import com.example.truebeauty.model.RecyclerServicos
import java.util.Locale

class DataServicios(private val context: Context) {

    private val originalData = loadAffirmations()
    fun loadAffirmations(): List<RecyclerServicos>{
        return listOf<RecyclerServicos>(

            RecyclerServicos(R.string.manicura, R.drawable.manicure),
            RecyclerServicos(R.string.pedicura, R.drawable.pedicure),
            RecyclerServicos(R.string.pestañas, R.drawable.pestanas),
            RecyclerServicos(R.string.cuidadofacial, R.drawable.cuifacial),
            RecyclerServicos(R.string.tratamientos, R.drawable.tratamiento),
            RecyclerServicos(R.string.cejas, R.drawable.cejas),


            )
    }

    fun search(query: String): List<RecyclerServicos> {
        val lowerCaseQuery = query.lowercase(Locale.getDefault())
        return originalData.filter {
            context.resources.getString(it.stringResourceId).lowercase(Locale.getDefault())
                .contains(lowerCaseQuery)
        }
    }


}