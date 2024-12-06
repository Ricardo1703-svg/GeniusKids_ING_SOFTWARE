package com.example.geniuskids.Invitado

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.example.geniuskids.Login.AuthActivity
import com.example.geniuskids.Materias.Matematicas.Basico
import com.example.geniuskids.R

class Menu : AppCompatActivity() {

    private lateinit var btnIrMatematicas : Button
    private lateinit var btnSalir : Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_menu)

        btnIrMatematicas = findViewById(R.id.btnIrinvitado)
        btnIrMatematicas.setOnClickListener {
            val intent = Intent(this, Mate_prueba::class.java)
            startActivity(intent)
        }

        btnSalir = findViewById(R.id.btnSalir)
        btnSalir.setOnClickListener {
            val intent = Intent(this, AuthActivity::class.java)
            startActivity(intent)
        }


    }
    override fun onBackPressed() {
    }
}