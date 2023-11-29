package com.example.truebeauty.ImportClasses

import android.app.Activity
import android.content.Context
import androidx.core.content.res.ResourcesCompat
import com.example.truebeauty.R
import www.sanju.motiontoast.MotionToast
import www.sanju.motiontoast.MotionToastStyle

class popupalert {

    fun toastSuccess(context: Context?, title: String, message: String)
    {
        MotionToast.createColorToast(
            context as Activity,
            title,
            message,
            MotionToastStyle.SUCCESS,
            MotionToast.GRAVITY_BOTTOM,
            MotionToast.LONG_DURATION,
            ResourcesCompat.getFont(context, R.font.fontfamily)
        )
    }
    fun toastError(context: Context, title: String, message: String)
    {
        MotionToast.createColorToast(
            context as Activity,
            title,
            message,
            MotionToastStyle.ERROR,
            MotionToast.GRAVITY_BOTTOM,
            MotionToast.LONG_DURATION,
            ResourcesCompat.getFont(context, R.font.fontfamily)
        )
    }


    fun toastWarning(context: Context, title: String, message: String)
    {
        MotionToast.darkToast(
            context as Activity,
            title,
            message,
            MotionToastStyle.WARNING,
            MotionToast.GRAVITY_BOTTOM,
            MotionToast.LONG_DURATION,
            ResourcesCompat.getFont(context, R.font.fontfamily))
         }


}