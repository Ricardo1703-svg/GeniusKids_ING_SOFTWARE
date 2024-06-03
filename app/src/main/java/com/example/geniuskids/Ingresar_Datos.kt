package com.example.geniuskids

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener


class Ingresar_Datos : AppCompatActivity() {
    private lateinit var database: DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_ingresar_datos)
        enableEdgeToEdge()
        database = FirebaseDatabase.getInstance().reference.child("contenido")


        val btnGuardar =findViewById<Button>(R.id.btnGuardar)
        btnGuardar.setOnClickListener {
            guardarContenido()
            limpiarCampos()
        }

        val btnDatosVer = findViewById<Button>(R.id.btnDatosVer)
        btnDatosVer.setOnClickListener {
            val intent = Intent(this, VisualizarDatos::class.java)
            startActivity(intent)
        }
    }

    private fun limpiarCampos() {
        val etNombre = findViewById<EditText>(R.id.etNombre)
        val etDesarrollo = findViewById<EditText>(R.id.etDesarrollo)
        val etActividad = findViewById<EditText>(R.id.etActividad)

        etNombre.setText("")
        etDesarrollo.setText("")
        etActividad.setText("")
    }

    private fun guardarContenido() {
        val etNombre = findViewById<EditText>(R.id.etNombre)
        val etDesarrollo = findViewById<EditText>(R.id.etDesarrollo)
        val etActividad = findViewById<EditText>(R.id.etActividad)

        val nombre = etNombre.text.toString().trim()
        val desarrollo = etDesarrollo.text.toString().trim()
        val actividad = etActividad.text.toString().trim()

        if (nombre.isEmpty() || desarrollo.isEmpty() || actividad.isEmpty()) {
            return
        }

        database.addListenerForSingleValueEvent(object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                var idCount = dataSnapshot.childrenCount.toInt() + 1 // Obtiene el número total de entradas y aumenta en 1
                val idPersonalizado = "MAT_${String.format("%03d", idCount)}" // Formatea el nuevo ID
                val contenido = Contenido(idPersonalizado, nombre, desarrollo, actividad)
                database.child(idPersonalizado).setValue(contenido)
                    .addOnSuccessListener {
                        limpiarCampos()
                    }
                    .addOnFailureListener {
                        // Manejar el caso de fallo en la operación de guardado
                    }
            }

            override fun onCancelled(databaseError: DatabaseError) {
                // Manejar el error en la recuperación de datos
            }
        })
    }
}