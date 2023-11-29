package com.example.truebeauty.data

import android.content.Context
import com.example.truebeauty.R
import com.example.truebeauty.model.RecyclerData
import java.util.Locale

class DataSource(private val context: Context) {

    private val originalData = loadAffirmations()
    fun loadAffirmations(): List<RecyclerData>{
        return listOf<RecyclerData>(

            RecyclerData(R.string.title1, R.drawable.jabonfacial),
            RecyclerData(R.string.title2, R.drawable.termoprotector),
            RecyclerData(R.string.title3, R.drawable.preshampo),
            RecyclerData(R.string.title4, R.drawable.primer),
            RecyclerData(R.string.title5, R.drawable.bbcream),
            RecyclerData(R.string.title5, R.drawable.rubor),
            RecyclerData(R.string.title6, R.drawable.shampoo),
            RecyclerData(R.string.title7, R.drawable.serum),



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

