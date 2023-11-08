package com.example.truebeauty

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import com.example.truebeauty.databinding.ActivityRatingBinding

class Activity_rating:  AppCompatActivity() {

    private lateinit var binding: ActivityRatingBinding
    var ListaRatings = arrayListOf<Float>()
    var ratingSelected = 0f
    var promedio = 0f

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)

        binding = ActivityRatingBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.ratingBarPersonal.rating = 3f

        binding.ratingBarPersonal.setOnRatingBarChangeListener { ratingBar, rating, b ->
            ratingSelected = rating

            mostrarDatos()
        }
        binding.btnAgregar.setOnClickListener { view ->

            ListaRatings.add(ratingSelected)
            calcularRatingsGeneral()
            mostrarDatos()

            binding.ratingBarPersonal.rating = 3f
        }
    }



    private fun calcularRatingsGeneral() {
        var evaluacion = 0f

        ListaRatings.forEach { rating ->
            evaluacion += rating
        }
        promedio = evaluacion / ListaRatings.size
    }

    private fun mostrarDatos() {
       binding.ratingBarGeneral.rating = promedio
        binding.tvRating.text="Evaluacion personal: ${ratingSelected}/ ${binding.ratingBarPersonal.numStars}.0 \n"+
                "Evaluacion general: ${promedio}/${binding.ratingBarGeneral.numStars}.0 \n"+
                "Total de evaluaciones: ${ListaRatings.size}"
    }
}