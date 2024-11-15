package com.example.geniuskids.Login

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.geniuskids.Materias.Materias
import com.example.geniuskids.Perfil
import com.example.geniuskids.R
import com.example.geniuskids.databinding.ActivityAuthBinding
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.api.ApiException
import com.google.firebase.analytics.FirebaseAnalytics
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.GoogleAuthProvider
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class AuthActivity : AppCompatActivity() {

    private lateinit var db: FirebaseFirestore

    private val GOOGLE_SIGN_IN = 100
    private lateinit var OjoMostrar: ImageView
    private lateinit var correo: EditText
    private lateinit var contra: EditText
    private lateinit var gso: GoogleSignInOptions
    private lateinit var googleSignInClient: GoogleSignInClient
    private var passwordVisible = false
    private val PREFS_FILE = "myPrefs"
    private lateinit var binding: ActivityAuthBinding

    private lateinit var auth: FirebaseAuth
    private lateinit var firestore: FirebaseFirestore

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAuthBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setUpActions()
        initializeDataIfNeeded()

        db = Firebase.firestore

        val sharedPreferences = getSharedPreferences(PREFS_FILE, Context.MODE_PRIVATE)
        val googleSignIn = sharedPreferences.getBoolean("googleSignIn", false)
        if (googleSignIn) {
            val currentUser = FirebaseAuth.getInstance().currentUser
            if (currentUser != null) {
                val intent = Intent(this, Materias::class.java)
                startActivity(intent)
                finish()
            }
        } else {
            OjoMostrar = findViewById(R.id.btnVeroNo)
            OjoMostrar.setOnClickListener {
                togglePasswordVisibility()
            }

            auth = FirebaseAuth.getInstance()
            firestore = FirebaseFirestore.getInstance()

            val analytics = FirebaseAnalytics.getInstance(this)
            val bundle = Bundle()
            bundle.putString("message", "Integracion de Firebase Completa")
            analytics.logEvent("InitScreen", bundle)

            correo = findViewById(R.id.txtCorreo)
            contra = findViewById(R.id.txtContrasena)

            val ingresar = findViewById<Button>(R.id.btnIngresar)
            ingresar.setOnClickListener {
                loginUserFirestore(correo.text.toString(), contra.text.toString())
            }

            val registrarse = findViewById<Button>(R.id.btnRegistro)
            registrarse.setOnClickListener {
                val intent = Intent(this, RegistroActivity::class.java)
                startActivity(intent)
            }

            val Restablecer1 = findViewById<Button>(R.id.btnRestablecer1)
            Restablecer1.setOnClickListener {
                val intent = Intent(this, Recuperacion::class.java)
                startActivity(intent)
            }

            val Anonimo = findViewById<Button>(R.id.Anonimo)
            Anonimo.setOnClickListener {
                Invitado()
            }
        }
    }
    override fun onBackPressed() {
        Toast.makeText(this, "La acción de retroceso está deshabilitada en esta pantalla", Toast.LENGTH_SHORT).show()
    }

    private fun Invitado(){
        FirebaseAuth.getInstance().signInAnonymously()
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    Log.d("AnonymousLoginActivity", "Inicio de sesión anónimo exitoso")
                    val intent = Intent(this, Materias::class.java)
                    startActivity(intent)
                } else {
                    Log.w(
                        "AnonymousLoginActivity",
                        "Error en el inicio de sesión anónimo",
                        task.exception
                    )
                    Toast.makeText(
                        this,
                        "Error en el inicio de sesión anónimo",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }
    }

    private fun loginUserFirestore(username: String, password: String) {
        // Accede a la colección "users" y busca documentos con el mismo nombre de usuario
        db.collection("users")
            .whereEqualTo("username", username)
            .whereEqualTo("password", password)
            .get()
            .addOnSuccessListener { documents ->
                if (!documents.isEmpty) {
                    val document = documents.documents[0]
                    val username = document.getString("username") ?: ""
                    val profileImageUrl = document.getString("profileImageUrl") ?: ""

                    // Pasar los datos a la nueva actividad
                    val intent = Intent(this, Perfil::class.java).apply {
                        putExtra("username", username)
                        putExtra("profileImageUrl", profileImageUrl)
                    }
                    val sharedPreferences = getSharedPreferences(PREFS_FILE, Context.MODE_PRIVATE)
                    val editor = sharedPreferences.edit()
                    editor.putBoolean("googleSignIn", false)
                    editor.apply()

                    // Iniciar la nueva actividad y finalizar la actual
                    startActivity(intent)
                    finish()
                } else {
                    Toast.makeText(this, "Nombre de usuario o contraseña incorrectos", Toast.LENGTH_SHORT).show()
                }
            }
            .addOnFailureListener { e ->
                // Error en la conexión a Firebase
                Toast.makeText(this, "Error al iniciar sesión: ${e.message}", Toast.LENGTH_SHORT).show()
            }
    }

    private fun setUpActions() {
        gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestIdToken(getString(R.string.default_web_client_id))
            .requestEmail()
            .build()

        googleSignInClient = GoogleSignIn.getClient(this, gso)

        binding.Google.setOnClickListener {
            signInWithGoogle()
        }
    }

    private fun signInWithGoogle() {
        googleSignInClient.signOut().addOnCompleteListener {
            val signInIntent = googleSignInClient.signInIntent
            startActivityForResult(signInIntent, GOOGLE_SIGN_IN)
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == GOOGLE_SIGN_IN) {
            val task = GoogleSignIn.getSignedInAccountFromIntent(data)
            try {
                val account = task.getResult(ApiException::class.java)
                if (account != null) {
                    firebaseAuthWithGoogle(account.idToken!!)
                }
            } catch (e: ApiException) {
                Log.w("AuthActivity", "Google sign in failed", e)
                Toast.makeText(this, "Error al iniciar sesión con Google", Toast.LENGTH_SHORT)
                    .show()
            }
        }
    }

    private fun firebaseAuthWithGoogle(idToken: String) {
        val credential = GoogleAuthProvider.getCredential(idToken, null)
        FirebaseAuth.getInstance().signInWithCredential(credential)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    Log.d("AuthActivity", "signInWithCredential:success")
                    val user = FirebaseAuth.getInstance().currentUser
                    if (user != null) {
                        checkIfUserExists(user)
                    }

                    val sharedPreferences = getSharedPreferences(PREFS_FILE, Context.MODE_PRIVATE)
                    val editor = sharedPreferences.edit()
                    editor.putBoolean("googleSignIn", true)
                    editor.apply()

                    val intent = Intent(this, Materias::class.java)
                    startActivity(intent)
                    finish()
                } else {
                    Log.w("AuthActivity", "signInWithCredential:failure", task.exception)
                    Toast.makeText(this, "Autenticación fallida.", Toast.LENGTH_SHORT).show()
                }
            }
    }

    private fun checkIfUserExists(user: FirebaseUser) {
        val database = FirebaseDatabase.getInstance().reference
        val userReference = database.child("users").child(user.uid)

        userReference.get().addOnSuccessListener { dataSnapshot ->
            if (!dataSnapshot.exists()) {
                saveUserToDatabase(user)
            }
        }.addOnFailureListener { exception ->
            Log.w("AuthActivity", "Error checking user existence", exception)
        }
    }

    private fun saveUserToDatabase(user: FirebaseUser) {
        val database = FirebaseDatabase.getInstance().reference
        val userReference = database.child("users").child(user.uid)

        val userData = mapOf(
            "uid" to user.uid,
            "name" to (user.displayName ?: ""),
            "email" to (user.email ?: ""),
            "photoUrl" to (user.photoUrl?.toString() ?: "")
        )

        userReference.setValue(userData).addOnCompleteListener { task ->
            if (task.isSuccessful) {
                Log.d("AuthActivity", "User saved to database successfully")
            } else {
                Log.w("AuthActivity", "Failed to save user to database", task.exception)
            }
        }
    }

    private fun togglePasswordVisibility() {
        if (!passwordVisible) {
            contra.inputType = android.text.InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD
            OjoMostrar.setImageResource(R.drawable.ver)
            passwordVisible = true
        } else {
            contra.inputType =
                android.text.InputType.TYPE_CLASS_TEXT or android.text.InputType.TYPE_TEXT_VARIATION_PASSWORD
            OjoMostrar.setImageResource(R.drawable.invisible)
            passwordVisible = false
        }
        contra.setSelection(contra.text.length)
    }

    private fun initializeDataIfNeeded() {
        val sharedPreferences = getSharedPreferences(PREFS_FILE, Context.MODE_PRIVATE)
        if (!sharedPreferences.contains("initialized")) {
            val editor = sharedPreferences.edit()
            editor.putBoolean("initialized", true)
            editor.apply()
        }
    }
}