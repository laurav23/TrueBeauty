package com.example.truebeauty.data

import android.content.Context
import com.example.truebeauty.R
import com.example.truebeauty.model.RecyclerData
import java.util.Locale

class DataSource (private val context: Context) {

    private val originalData = loadAffirmations()
    fun loadAffirmations(): List<RecyclerData> {
        return listOf<RecyclerData>(
            RecyclerData(R.string.title1, R.drawable.unas),
            RecyclerData(R.string.title2, R.drawable.cfacial),
            RecyclerData(R.string.title3, R.drawable.ccapilar),
            RecyclerData(R.string.title4, R.drawable.maquillaje),

            RecyclerData(R.string.title1, R.drawable.unas),
            RecyclerData(R.string.title2, R.drawable.cfacial),
            RecyclerData(R.string.title3, R.drawable.ccapilar),
            RecyclerData(R.string.title4, R.drawable.maquillaje),


            )
    }

    fun search(query: String): List<RecyclerData> {
        val lowerCaseQuery = query.lowercase(Locale.getDefault())
        return originalData.filter {
            context.resources.getString(it.stringResourceId).lowercase(Locale.getDefault())
                .contains(lowerCaseQuery)
        }
    }
}