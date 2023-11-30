package com.example.truebeauty.Crud

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.truebeauty.ApiConexion.ApiConexion
import com.example.truebeauty.enviar.AdminUser
import com.example.truebeauty.enviar.ProductSend
import com.example.truebeauty.enviar.Tip
import com.example.truebeauty.enviar.User
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class CrudViewModel : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = ""
    }
    val text: LiveData<String> = _text

    private val _contentData = MutableLiveData<List<Tip>>()
    val contentData: LiveData<List<Tip>> = _contentData

    init {
        fetchData()
    }

    private fun fetchData() {
        CoroutineScope(Dispatchers.IO).launch {
            val apiService = ApiConexion.getApiService()
            val apiGetContent = apiService.getTips()

            apiGetContent.enqueue(object : Callback<List<Tip>> {
                override fun onResponse(call: Call<List<Tip>>, response: Response<List<Tip>>) {
                    if (response.isSuccessful) {
                        val contentResponseList = response.body()
                        contentResponseList?.let {
                            _contentData.postValue(it)

                            // Imprimir los datos en la consola para verificar
                            for (tip in it) {
                                Log.d(
                                    "Tip Data",
                                    "Title: ${tip.id}, Description: ${tip.description}"
                                )
                                // ... Puedes imprimir otros campos que quieras verificar
                            }
                        }
                    } else {
                        Log.e("API Error", "Failed to fetch data: ${response.message()}")
                    }
                }

                override fun onFailure(call: Call<List<Tip>>, t: Throwable) {
                    Log.e("API Error", "Failed to fetch data", t)
                }
            })
        }
    }
}



