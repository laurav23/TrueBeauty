package com.example.truebeauty

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import com.example.truebeauty.ui.home.HomeFragment

class Activity_login : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        val textView = findViewById<TextView>(R.id.registrate)
        val buttonView = findViewById<Button>(R.id.loginButton)


        textView.setOnClickListener {
            // Cuando se hace clic en el TextView
            val intent = Intent(this, Activity_registro::class.java)
            startActivity(intent)

        }
        buttonView.setOnClickListener {

            val intent = Intent(this, BasicView::class.java)
            startActivity(intent)
        }


    }


}