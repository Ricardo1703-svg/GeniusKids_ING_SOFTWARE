package com.example.geniuskids.Login

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.ImageButton
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import com.example.geniuskids.R
import com.google.firebase.auth.FirebaseAuth

class Recuperacion : AppCompatActivity() {
    private lateinit var CorreoS: EditText
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_recuperacion)

        val btnRegresRecu = findViewById<ImageButton>(R.id.imageButton)
        btnRegresRecu.setOnClickListener {
            val intent = Intent(this, AuthActivity::class.java)
            startActivity(intent)
        }
        CorreoS = findViewById(R.id.editTextCorreoRecuperacion)
        val Recuperar = findViewById<Button>(R.id.btnVerificar)
        Recuperar.setOnClickListener {
            val email = CorreoS.text.toString().trim()
            if (email.isNotEmpty()) {
                FirebaseAuth.getInstance().sendPasswordResetEmail(email)
                    .addOnCompleteListener { task ->
                        if (task.isSuccessful) {
                            Toast.makeText(this,
                                "Se ha enviado un correo electrónico para restablecer la contraseña",
                                Toast.LENGTH_SHORT
                            ).show()
                            finish()

                        } else {
                            Toast.makeText(
                                this,
                                "Error al enviar el correo electrónico para restablecer la contraseña",
                                Toast.LENGTH_SHORT
                            ).show()
                        }
                    }
            }else{
                Toast.makeText(this, "Por favor, ingresa tu dirección de correo electrónico", Toast.LENGTH_SHORT)
                    .show()
            }
        }
    }
}