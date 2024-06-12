package com.example.geniuskids.Base_de_Datos

import android.os.Bundle
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import com.example.geniuskids.R
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener

class DetalleContenidoActivity : AppCompatActivity() {
    private lateinit var database: DatabaseReference
    private lateinit var tvId: TextView
    private lateinit var tvNombre: TextView
    private lateinit var tvDesarrollo: TextView
    private lateinit var tvActividad: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detalle_contenido)
        enableEdgeToEdge()

        tvId = findViewById(R.id.tvId)
        tvNombre = findViewById(R.id.tvNombre)
        tvDesarrollo = findViewById(R.id.tvDesarrollo)
        tvActividad = findViewById(R.id.tvActividad)

        // Recupera el ID del contenido pasado en el Intent
        val contenidoId = intent.getStringExtra("CONTENIDO_ID")

        if (contenidoId != null) {
            database = FirebaseDatabase.getInstance().reference.child("contenido").child(contenidoId)
            database.addListenerForSingleValueEvent(object : ValueEventListener {
                override fun onDataChange(dataSnapshot: DataSnapshot) {
                    val contenido = dataSnapshot.getValue(Contenido::class.java)
                    contenido?.let {
                        tvId.text = it.id
                        tvNombre.text = it.nombre
                        tvDesarrollo.text = it.desarrollo
                        tvActividad.text = it.actividad
                    }
                }
                override fun onCancelled(databaseError: DatabaseError) {
                }
            })
        }
    }
}