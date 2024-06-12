package com.example.geniuskids.Base_de_Datos

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import com.example.geniuskids.R
import com.example.geniuskids.videos.WebViewTos

class ContenidosMate : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_contenidos_mate)

        val Tema = findViewById<TextView>(R.id.txtTema)
        val Contenido = findViewById<TextView>(R.id.txtContenido)

        val claveTema = intent.getStringExtra("clave_tema")
        val claveContenido = intent.getStringExtra("clave_contenido")

        val temaId = resources.getIdentifier(claveTema, "string", packageName)
        val contenidoId = resources.getIdentifier(claveContenido, "string", packageName)

        val tema = getString(temaId)
        val contenido = getString(contenidoId)

        Tema.text = "$tema"
        Contenido.text = "$contenido"

        val ActividadMate = findViewById<Button>(R.id.btnActividad)
        ActividadMate.setOnClickListener {
            val intent = Intent(this, WebViewTos::class.java)
            startActivity(intent)
        }

    }
}