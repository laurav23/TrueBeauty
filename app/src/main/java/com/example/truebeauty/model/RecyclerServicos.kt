package com.example.truebeauty.model

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes

// Modelo de datos para elementos individuales del RecyclerView
data class RecyclerServicos(@StringRes val stringResourceId: Int, // ID del recurso de cadena (String) para texto
                            @DrawableRes val imageResourceId: Int // ID del recurso drawable (imagen) para la imagen
)

//Buscar por id y  traer desde la base de datos