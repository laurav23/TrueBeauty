package com.example.truebeauty

import com.google.gson.annotations.SerializedName

data class ProductsResponse (
    @SerializedName("Products")var Products: ArrayList<Products>

)