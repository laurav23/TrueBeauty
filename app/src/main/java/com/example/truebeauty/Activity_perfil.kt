package com.example.truebeauty

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity

import com.example.truebeauty.databinding.ActivityPerfilBinding


class Activity_perfil : AppCompatActivity() {

    lateinit var binding: ActivityPerfilBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_perfil)
        binding = ActivityPerfilBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val textView = binding.cancelar


        textView.setOnClickListener {
            // Cuando se hace clic en el TextView
            val intent = Intent(this, Home::class.java)
            startActivity(intent)
        }
    }
}