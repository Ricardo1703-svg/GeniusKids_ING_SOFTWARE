package com.example.geniuskids

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth

class RegistroActivity : AppCompatActivity() {
    private lateinit var OjoMostrar: ImageView
    private lateinit var contraEditText: EditText
    var passwordVisible = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_registro)

        val correoEditText = findViewById<EditText>(R.id.txtCorreo)
        OjoMostrar = findViewById(R.id.btno)
        contraEditText = findViewById(R.id.txtContrasena)


        val registrarseButton = findViewById<Button>(R.id.btnRegistro)
        registrarseButton.setOnClickListener {
            val email = correoEditText.text.toString()
            val password = contraEditText.text.toString()
            if (email.isNotEmpty() && password.isNotEmpty()){
                FirebaseAuth.getInstance().createUserWithEmailAndPassword(email, password)
                    .addOnCompleteListener(this) { task ->
                        if (task.isSuccessful) {
                            Log.d("RegistroActivity", "Registro exitoso")
                            val intent = Intent(this, AuthActivity::class.java)
                            startActivity(intent)
                        } else {
                            Log.w("RegistroActivity", "Error en el registro", task.exception)
                            Toast.makeText(this, "Error en el registro", Toast.LENGTH_SHORT).show()
                        }
                    }
            } else {
                Toast.makeText(this, "Por favor, ingrese correo y contraseña", Toast.LENGTH_SHORT).show()
            }
        }

        val Reg2 = findViewById<ImageButton>(R.id.Reg2)
        Reg2.setOnClickListener {
            val intent = Intent(this, AuthActivity::class.java)
            startActivity(intent)
        }

        //------------------Mostrar Contraseña--------------------------------
        OjoMostrar.setOnClickListener {
            togglePasswordVisibility()
        }
        //--------------------------------------------------------------------
    }
    private fun togglePasswordVisibility(){
        if (!passwordVisible) {
            // Si la contraseña está oculta, mostrarla
            contraEditText.inputType = android.text.InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD
            OjoMostrar.setImageResource(R.drawable.ver)
            passwordVisible = true
        }else {
            // Si la contraseña está visible, ocultarla
            contraEditText.inputType = android.text.InputType.TYPE_CLASS_TEXT or android.text.InputType.TYPE_TEXT_VARIATION_PASSWORD
            OjoMostrar.setImageResource(R.drawable.invisible)
            passwordVisible = false
        }
        contraEditText.setSelection(contraEditText.text.length)
    }
}
