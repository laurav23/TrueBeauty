package com.example.truebeauty.model

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes


data class RecyclerData (@StringRes val stringResourceId: Int,
                         @DrawableRes val imageResourceId: Int)