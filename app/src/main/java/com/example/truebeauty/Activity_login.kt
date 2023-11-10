package com.example.truebeauty

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.truebeauty.databinding.ActivityLoginBinding

class Activity_login : AppCompatActivity() {
    private lateinit var binding: ActivityLoginBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val textView = binding.registrate
        val buttonView = binding.loginButton

        textView.setOnClickListener {
            // Cuando se hace clic en el TextView
            val intent = Intent(this, Activity_registro::class.java)
            startActivity(intent)
        }

        buttonView.setOnClickListener {
            val intent = Intent(this, Home::class.java)
            startActivity(intent)
        }
    }
}
