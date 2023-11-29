package com.example.truebeauty.ui.productos

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.truebeauty.ApiConexion.ApiConexion
import com.example.truebeauty.enviar.ProductSend
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ProductsViewModel : ViewModel(){

    init {
        getAllContent()
    }

    private val _text = MutableLiveData<String>().apply {
        value = ""
    }
    val text: LiveData<String> = _text

    private val _contentData = MutableLiveData<List<ProductSend>>()
    val contentData: LiveData<List<ProductSend>> get() = _contentData

    private fun getAllContent() {
        CoroutineScope(Dispatchers.IO).launch {
            val apiGetContent = ApiConexion.getApiService().getProduct()

            apiGetContent.enqueue(object : Callback<List<ProductSend>> { // Cambiado a List<ContentResponse>
                override fun onResponse(
                    call: Call<List<ProductSend>>,
                    response: Response<List<ProductSend>>
                ) {
                    if (response.isSuccessful) {
                        val contentResponseList = response.body()
                        contentResponseList?.let {
                            _contentData.value = it
                        }
                    }
                }

                override fun onFailure(call: Call<List<ProductSend>>, t: Throwable) {
                    Log.e("Error content", t.toString())
                }
            })
        }
    }





}