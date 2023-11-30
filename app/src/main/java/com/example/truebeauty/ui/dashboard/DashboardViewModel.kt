package com.example.truebeauty.ui.dashboard

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

// ViewModel para el fragmento de Dashboard
class DashboardViewModel : ViewModel() {

    // LiveData mutable que almacena el texto para el dashboard
    private val _text = MutableLiveData<String>().apply {
        value = "This is dashboard Fragment" // Valor inicial del texto
    }

    // LiveData que expone el texto como solo lectura
    val text: LiveData<String> = _text
}
