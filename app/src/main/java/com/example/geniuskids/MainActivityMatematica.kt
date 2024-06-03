package com.example.geniuskids

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity

class MainActivityMatematica : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main_matematica)

        val contenido1 = findViewById<Button>(R.id.btnIrConte_uno_mate)
        val contenido2 = findViewById<Button>(R.id.btnIrConte_dos_mate)
        val contenido3 = findViewById<Button>(R.id.btnIrConte_tres_mate)

        contenido1.setOnClickListener {
            val intent = Intent(this, WebViewTos::class.java)
            startActivity(intent)
        }

        contenido2.setOnClickListener {
            mostrarContenido("MAT_002")
        }

        contenido3.setOnClickListener {
            mostrarContenido("MAT_003")
        }
    }

    private fun mostrarContenido(id: String) {
        val intent = Intent(this, DetalleContenidoActivity::class.java)
        intent.putExtra("CONTENIDO_ID", id)
        startActivity(intent)
    }
}