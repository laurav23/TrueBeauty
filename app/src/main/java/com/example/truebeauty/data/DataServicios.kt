package com.example.truebeauty.data

import android.content.Context
import com.example.truebeauty.R
//import com.example.truebeauty.model.RecyclerData
import com.example.truebeauty.model.RecyclerServicos
import java.util.Locale

// Clase que gestiona los datos de los servicios
class DataServicios(private val context: Context) {

    // Datos originales de los servicios
    private val originalData = loadAffirmations()

    // Carga inicial de datos de servicios
    fun loadAffirmations(): List<RecyclerServicos> {
        return listOf(
            RecyclerServicos(R.string.manicura, R.drawable.manicure),
            RecyclerServicos(R.string.pedicura, R.drawable.pedicure),
            RecyclerServicos(R.string.pestañas, R.drawable.pestanas),
            RecyclerServicos(R.string.cuidadofacial, R.drawable.cuifacial),
            RecyclerServicos(R.string.tratamientos, R.drawable.tratamiento),
            RecyclerServicos(R.string.cejas, R.drawable.cejas)
        )
    }

    // Función de búsqueda que filtra los servicios basados en la consulta
    fun search(query: String): List<RecyclerServicos> {
        val lowerCaseQuery = query.lowercase(Locale.getDefault())
        return originalData.filter {
            context.resources.getString(it.stringResourceId)
                .lowercase(Locale.getDefault())
                .contains(lowerCaseQuery)
        }
    }
}
