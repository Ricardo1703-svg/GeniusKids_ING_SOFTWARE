package com.example.geniuskids.Login

import android.app.Activity
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.geniuskids.R
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.FirebaseStorage

class RegistroActivity : AppCompatActivity() {
    //------------------Mostrar Contraseña--------------------------------
    private lateinit var OjoMostrar: ImageView
    var passwordVisible = false
    //--------------------------------------------------------------------

    private lateinit var db: FirebaseFirestore

    private lateinit var usuarioEditText: EditText
    private lateinit var correoEditText: EditText
    private lateinit var contraEditText: EditText
    private lateinit var btnRegistro: Button
    private lateinit var ImagendePerfil: ImageView
    private lateinit var SeleccionarImagen: Button
    private lateinit var textoDeError: TextView

    private lateinit var auth: FirebaseAuth
    private lateinit var firestore: FirebaseFirestore
    private lateinit var storage: FirebaseStorage

    private val PICK_IMAGE_REQUEST = 1
    private var imageUri: Uri? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_registro)

        // Inicializar FirebaseAuth y Firestore
        auth = FirebaseAuth.getInstance()
        firestore = FirebaseFirestore.getInstance()
        storage = FirebaseStorage.getInstance()

        db = Firebase.firestore

        // Inicializa los EditText y botones
        usuarioEditText = findViewById(R.id.txtUsuario)
        correoEditText = findViewById(R.id.txtCorreo)
        contraEditText = findViewById(R.id.txtContrasena)
        btnRegistro = findViewById(R.id.btnRegistro)
        ImagendePerfil = findViewById(R.id.ImagendePerfil)
        SeleccionarImagen = findViewById(R.id.SeleccionarImagen)

        textoDeError = findViewById(R.id.textoDeError)

        OjoMostrar = findViewById(R.id.btno)


        // Inicializar FirebaseAuth y Firestore
        auth = FirebaseAuth.getInstance()
        firestore = FirebaseFirestore.getInstance()
        storage = FirebaseStorage.getInstance()

        SeleccionarImagen.setOnClickListener {
            openGallery()
        }

        btnRegistro.setOnClickListener {
            RegistrarUsuario()
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
    //Quitar el retroceso en la pantalla
    override fun onBackPressed() {
    }

    private fun RegistrarUsuario() {
        val username = usuarioEditText.text.toString().trim()
        val email = correoEditText.text.toString().trim()
        val password = contraEditText.text.toString().trim()

        if (username.isEmpty()) {
            usuarioEditText.error = "Nombre de usuario requerido"
            return
        }

        if (email.isEmpty()) {
            correoEditText.error = "Correo electrónico requerido"
            return
        }

        if (password.isEmpty()) {
            contraEditText.error = "Contraseña requerida"
            return
        }

        // Llamar a la función para guardar el usuario en Firebase
        saveUserToFirestore(username, email, password)
    }

    private fun openGallery() {
        val intent = Intent(Intent.ACTION_PICK)
        intent.type = "image/*"
        startActivityForResult(intent, PICK_IMAGE_REQUEST)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == PICK_IMAGE_REQUEST && resultCode == Activity.RESULT_OK && data != null && data.data != null) {
            imageUri = data.data
            ImagendePerfil.setImageURI(imageUri)
        }
    }

    private fun saveUserToFirestore(username: String, email: String, password: String) {
        val user = hashMapOf(
            "username" to username,
            "email" to email,
            "password" to password,
            "profileImageUrl" to (imageUri?.toString() ?: "")
        )

        db.collection("users")
            .add(user)
            .addOnSuccessListener {
                textoDeError.text = "Registro guardado en Firebase" // Mostrar el mensaje en el TextView Toast.makeText(this, "Registro guardado en Firebase", Toast.LENGTH_SHORT).show()
            }
            .addOnFailureListener { e ->
                textoDeError.text = "Error al guardar registro: ${e.message}" // Mostrar el error en el TextView Toast.makeText(this, "Error al guardar registro: ${e.message}", Toast.LENGTH_SHORT).show()
            }
    }

    private fun togglePasswordVisibility() {
        if (!passwordVisible) {
            contraEditText.inputType = android.text.InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD
            contraEditText.transformationMethod = null // Mostrar la contraseña
            OjoMostrar.setImageResource(R.drawable.ver)
            passwordVisible = true
        } else {
            contraEditText.inputType = android.text.InputType.TYPE_CLASS_TEXT or android.text.InputType.TYPE_TEXT_VARIATION_PASSWORD
            contraEditText.transformationMethod = android.text.method.PasswordTransformationMethod.getInstance() // Ocultar la contraseña
            OjoMostrar.setImageResource(R.drawable.invisible)
            passwordVisible = false
        }
        contraEditText.setSelection(contraEditText.text.length)
    }
}