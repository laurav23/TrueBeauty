package com.example.truebeauty

import android.app.DatePickerDialog
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.EditText
import android.widget.LinearLayout
import android.widget.RadioButton
import android.widget.RadioGroup
import android.widget.Spinner
import android.widget.Toast
import androidx.cardview.widget.CardView
import java.util.Calendar

class Activity_CrearCita : AppCompatActivity() {

    private val selectedCalendar = Calendar.getInstance()
    private var selectedRadioButton: RadioButton? = null
    private var usuarioHaIniciadoSesion = false

    private fun handleLoginSuccess() {
        // Este método se llamará después de un inicio de sesión exitoso
        usuarioHaIniciadoSesion = true
    }

    private fun handleLogout() {
        // Este método se llamará después de cerrar sesión
        usuarioHaIniciadoSesion = false
    }

    private fun configurarBotonConfirmar() {
        val btnConfirm = findViewById<Button>(R.id.Confirmar)

        btnConfirm.setOnClickListener {
            if (usuarioHaIniciadoSesion) {
                // El usuario ha iniciado sesión, mostrar el Toast y finalizar la actividad
                Toast.makeText(applicationContext, "Cita creada exitosamente", Toast.LENGTH_SHORT).show()
                val intent = Intent(this, Home::class.java)
                startActivity(intent)
                finish()
            } else {
                // El usuario no ha iniciado sesión, redirigirlo al inicio de sesión
                val intent = Intent(this, Activity_login::class.java)
                startActivity(intent)
                // Puedes finalizar esta actividad para que al regresar de Activity_login no se vuelva a abrir el Toast
                finish()
            }
        }
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_crear_cita)

        val btnNext = findViewById<Button>(R.id.Siguiente)
        val btnConfirm = findViewById<Button>(R.id.Confirmar)
        val cv_siguiente = findViewById<CardView>(R.id.cv_siguiente)
        val cv_confirmar = findViewById<CardView>(R.id.cv_confirmar)

        btnNext.setOnClickListener {
            cv_siguiente.visibility = View.GONE
            cv_confirmar.visibility = View.VISIBLE
        }

        usuarioHaIniciadoSesion = intent.getBooleanExtra("usuarioHaIniciadoSesion", false)
        configurarBotonConfirmar()
//        btnConfirm.setOnClickListener {
//
//            Toast.makeText(applicationContext, "Cita realizada exitosamente", Toast.LENGTH_SHORT)
//                .show()
//            finish()
//        btnConfirm.setOnClickListener {
//            if (usuarioHaIniciadoSesion) {
//                // El usuario ha iniciado sesión, mostrar el Toast y finalizar la actividad
//                Toast.makeText(applicationContext, "Cita creada exitosamente", Toast.LENGTH_SHORT)
//                    .show()
//                finish()
//            } else {
//                // El usuario no ha iniciado sesión, redirigirlo al inicio de sesión
//                val intent = Intent(this, Activity_login::class.java)
//                startActivity(intent)
//                // Puedes finalizar esta actividad para que al regresar de Activity_login no se vuelva a abrir el Toast
//                finish()
//            }
//        }



        val spinner_especialidad = findViewById<Spinner>(R.id.spinner_especialidad)
        val spinner_estilista = findViewById<Spinner>(R.id.spinner_estilista)

        val optionsEspecialidad = arrayOf("Cabello", "Uñas", "Cejas y pestañas")
        spinner_especialidad.adapter =
            ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, optionsEspecialidad)

        val optionsEstilista = arrayOf("Estilista 1", "Estilista 2", "Estilista 3")
        spinner_estilista.adapter =
            ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, optionsEstilista)


    }


    fun onClickScheduledDate(v: View?){

        val etScheduledDate = findViewById<EditText>(R.id.et_fecha)

        val year = selectedCalendar.get(Calendar.YEAR)
        val month = selectedCalendar.get(Calendar.MONTH)
        val dayOfMonth = selectedCalendar.get(Calendar.DAY_OF_MONTH)
        val listener = DatePickerDialog.OnDateSetListener{datePicker, y, m, a ->
            selectedCalendar.set(y,m,a)
            etScheduledDate.setText("$y-$m-$a")
            displayRadioButtons()
        }
        DatePickerDialog(this, listener, year, month, dayOfMonth).show()

    }
    private fun displayRadioButtons(){
        val radioGroupLeft= findViewById<LinearLayout>(R.id.radio_grup_izq)
        val radioGroupRight= findViewById<LinearLayout>(R.id.radio_grup_der)

        radioGroupLeft.removeAllViews()
        radioGroupRight.removeAllViews()

        selectedRadioButton = null

        var goToLeft = true

        val hours = arrayOf("8:00 AM","9:15 AM", "10:20 AM", "11:30 AM","1:15 PM", "2:20 PM", "4:00 PM","5:15 PM")
        hours.forEach {
            val radioButton = RadioButton(this)
            radioButton.id = View.generateViewId()
            radioButton.text = it

            radioButton.setOnClickListener{ view ->
                selectedRadioButton?.isChecked = false
                selectedRadioButton = view as RadioButton?
                selectedRadioButton?.isChecked = true
            }

            if (goToLeft)
            radioGroupLeft.addView(radioButton)

            else
                radioGroupRight.addView(radioButton)

            goToLeft = !goToLeft


        }

    }



}