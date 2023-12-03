package com.example.truebeauty.enviar

data class ProductSend(
    var id: Int,
    var name: String,
    var description: String,
    var image_path: String,
    var price: Double,
    var quantity: Int,
    var status: Int,
    var subcategory_id: Int
)