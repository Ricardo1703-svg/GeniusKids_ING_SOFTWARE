package com.example.geniuskids.Login

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.widget.Button
import android.widget.EditText
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import com.example.geniuskids.R
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.storage.FirebaseStorage

class RegistroActivity : AppCompatActivity() {
    //------------------Mostrar Contraseña--------------------------------
    private lateinit var OjoMostrar: ImageView
    var passwordVisible = false
    //--------------------------------------------------------------------

    private lateinit var usuarioEditText: EditText
    private lateinit var correoEditText: EditText
    private lateinit var contraEditText: EditText
    private lateinit var btnRegistro: Button
    private lateinit var ivProfilePicture: ImageView
    private lateinit var btnSelectImage: Button

    private lateinit var auth: FirebaseAuth
    private lateinit var firestore: FirebaseFirestore
    private lateinit var storage: FirebaseStorage
    private var selectedImageUri: Uri? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_registro)

        // Inicializar FirebaseAuth y Firestore
        auth = FirebaseAuth.getInstance()
        firestore = FirebaseFirestore.getInstance()
        storage = FirebaseStorage.getInstance()

        // Inicializa los EditText y botones
        usuarioEditText = findViewById(R.id.txtUsuario)
        correoEditText = findViewById(R.id.txtCorreo)
        contraEditText = findViewById(R.id.txtContrasena)
        btnRegistro = findViewById(R.id.btnRegistro)
        ivProfilePicture = findViewById(R.id.ivProfilePicture)
        btnSelectImage = findViewById(R.id.btnSelectImage)

        OjoMostrar = findViewById(R.id.btno) // Mover aquí para mantener la consistencia


        // Inicializar FirebaseAuth y Firestore
        auth = FirebaseAuth.getInstance()
        firestore = FirebaseFirestore.getInstance()
        storage = FirebaseStorage.getInstance()

        btnSelectImage.setOnClickListener {
            openImageSelector()
        }

        btnRegistro.setOnClickListener {
            val email = correoEditText.text.toString()
            val password = contraEditText.text.toString()
            val username = usuarioEditText.text.toString()
            if (selectedImageUri != null) {
                registerUser(email, password, username, selectedImageUri!!)
            } else {
                Toast.makeText(this, "Por favor selecciona una imagen", Toast.LENGTH_SHORT).show()
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

    private fun openImageSelector() {
        val intent = Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI)
        imageSelectorLauncher.launch(intent)
    }

    private val imageSelectorLauncher =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
            if (result.resultCode == RESULT_OK) {
                val data: Intent? = result.data
                selectedImageUri = data?.data
                // Puedes establecer una vista previa de la imagen seleccionada aquí si lo deseas
                ivProfilePicture.setImageURI(selectedImageUri)
            }
        }
    private fun registerUser(email: String, password: String, username: String, imageUri: Uri) {
        auth.createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    val user = auth.currentUser
                    user?.let {
                        uploadProfileImage(imageUri, it.uid, username)
                    }
                } else {
                    Toast.makeText(this, "Error al registrarse: ${task.exception?.message}", Toast.LENGTH_SHORT).show()
                }
            }
    }

    private fun uploadProfileImage(imageUri: Uri, userId: String, username: String) {
        val storageRef = storage.reference.child("profile_images/$userId")
        storageRef.putFile(imageUri)
            .addOnSuccessListener {
                storageRef.downloadUrl.addOnSuccessListener { uri ->
                    saveUserData(userId, username, uri.toString())
                }
            }
            .addOnFailureListener { e ->
                Toast.makeText(this, "Error al subir la imagen: ${e.message}", Toast.LENGTH_SHORT).show()
            }
    }

    private fun saveUserData(userId: String, username: String, profilePictureUrl: String) {
        val userMap = hashMapOf(
            "username" to username,
            "email" to auth.currentUser?.email,
            "profilePicture" to profilePictureUrl
        )

        firestore.collection("users").document(userId).set(userMap)
            .addOnSuccessListener {
                Toast.makeText(this, "Registro exitoso", Toast.LENGTH_SHORT).show()
            }
            .addOnFailureListener { e ->
                Toast.makeText(this, "Error al guardar el usuario: ${e.message}", Toast.LENGTH_SHORT).show()
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
