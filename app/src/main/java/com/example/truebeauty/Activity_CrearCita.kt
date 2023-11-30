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

    // Calendario utilizado para seleccionar la fecha
    private val selectedCalendar = Calendar.getInstance()

    // Variable para almacenar el RadioButton seleccionado
    private var selectedRadioButton: RadioButton? = null

    // Flag para indicar si el usuario ha iniciado sesión
    private var usuarioHaIniciadoSesion = false

    // Método llamado después de un inicio de sesión exitoso
    private fun handleLoginSuccess() {
        usuarioHaIniciadoSesion = true
    }

    // Método llamado después de cerrar sesión
    private fun handleLogout() {
        usuarioHaIniciadoSesion = false
    }

    // Configuración del botón de confirmación
    private fun configurarBotonConfirmar() {
        val btnConfirm = findViewById<Button>(R.id.Confirmar)

        btnConfirm.setOnClickListener {
            if (usuarioHaIniciadoSesion) {
                // Mostrar mensaje y finalizar la actividad si el usuario ha iniciado sesión
                Toast.makeText(applicationContext, "Cita creada exitosamente", Toast.LENGTH_SHORT).show()
                val intent = Intent(this, Home::class.java)
                startActivity(intent)
                finish()
            } else {
                // Redirigir al usuario al inicio de sesión si no ha iniciado sesión
                val intent = Intent(this, Activity_login::class.java)
                startActivity(intent)
                finish() // Finalizar esta actividad para evitar duplicados después del inicio de sesión
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

        // Acción al presionar el botón "Siguiente"
        btnNext.setOnClickListener {
            cv_siguiente.visibility = View.GONE
            cv_confirmar.visibility = View.VISIBLE
        }

        // Obtener información sobre si el usuario ha iniciado sesión o no
        usuarioHaIniciadoSesion = intent.getBooleanExtra("usuarioHaIniciadoSesion", false)
        configurarBotonConfirmar()

        // Configuración de Spinners para seleccionar especialidad y estilista
        val spinner_especialidad = findViewById<Spinner>(R.id.spinner_especialidad)
        val spinner_estilista = findViewById<Spinner>(R.id.spinner_estilista)

        val optionsEspecialidad = arrayOf("Cabello", "Uñas", "Cejas y pestañas")
        spinner_especialidad.adapter =
            ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, optionsEspecialidad)

        val optionsEstilista = arrayOf("Estilista 1", "Estilista 2", "Estilista 3")
        spinner_estilista.adapter =
            ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, optionsEstilista)
    }

    // Método para manejar la selección de la fecha programada
    fun onClickScheduledDate(v: View?){
        val etScheduledDate = findViewById<EditText>(R.id.et_fecha)

        val year = selectedCalendar.get(Calendar.YEAR)
        val month = selectedCalendar.get(Calendar.MONTH)
        val dayOfMonth = selectedCalendar.get(Calendar.DAY_OF_MONTH)
        val listener = DatePickerDialog.OnDateSetListener{datePicker, y, m, a ->
            selectedCalendar.set(y,m,a)
            etScheduledDate.setText("$y-$m-$a")
            displayRadioButtons() // Mostrar opciones de horarios al seleccionar una fecha
        }
        DatePickerDialog(this, listener, year, month, dayOfMonth).show()
    }

    // Método para mostrar los botones de selección de horarios
    private fun displayRadioButtons(){
        val radioGroupLeft= findViewById<LinearLayout>(R.id.radio_grup_izq)
        val radioGroupRight= findViewById<LinearLayout>(R.id.radio_grup_der)

        radioGroupLeft.removeAllViews()
        radioGroupRight.removeAllViews()

        selectedRadioButton = null

        var goToLeft = true

        // Horarios disponibles para citas
        val hours = arrayOf("8:00 AM","9:15 AM", "10:20 AM", "11:30 AM","1:15 PM", "2:20 PM", "4:00 PM","5:15 PM")
        hours.forEach {
            val radioButton = RadioButton(this)
            radioButton.id = View.generateViewId()
            radioButton.text = it

            // Acción al hacer clic en un RadioButton
            radioButton.setOnClickListener{ view ->
                selectedRadioButton?.isChecked = false
                selectedRadioButton = view as RadioButton?
                selectedRadioButton?.isChecked = true
            }

            // Agregar RadioButton a la vista, alternando entre izquierda y derecha
            if (goToLeft)
                radioGroupLeft.addView(radioButton)
            else
                radioGroupRight.addView(radioButton)

            goToLeft = !goToLeft
        }
    }
}
