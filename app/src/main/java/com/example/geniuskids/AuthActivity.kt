package com.example.geniuskids

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
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

class AuthActivity : AppCompatActivity() {
    private val GOOGLE_SIGN_IN = 100
    private lateinit var OjoMostrar: ImageView
    private lateinit var contra: EditText
    private lateinit var gso: GoogleSignInOptions
    private lateinit var googleSignInClient: GoogleSignInClient
    private var passwordVisible = false
    private val PREFS_FILE = "myPrefs"
    private lateinit var binding: ActivityAuthBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAuthBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setUpActions()
        initializeDataIfNeeded()

        val sharedPreferences = getSharedPreferences(PREFS_FILE, Context.MODE_PRIVATE)
        val googleSignIn = sharedPreferences.getBoolean("googleSignIn", false)
        if (googleSignIn) {
            val currentUser = FirebaseAuth.getInstance().currentUser
            if (currentUser != null) {
                val intent = Intent(this, Nivel::class.java)
                startActivity(intent)
                finish()
            }
        } else {
            OjoMostrar = findViewById(R.id.btnVeroNo)
            OjoMostrar.setOnClickListener {
                togglePasswordVisibility()
            }

            val analytics = FirebaseAnalytics.getInstance(this)
            val bundle = Bundle()
            bundle.putString("message", "Integracion de Firebase Completa")
            analytics.logEvent("InitScreen", bundle)

            val correo = findViewById<EditText>(R.id.txtCorreo)
            contra = findViewById(R.id.txtContrasena)
            val ingresar = findViewById<Button>(R.id.btnIngresar)
            ingresar.setOnClickListener {
                val email = correo.text.toString()
                val password = contra.text.toString()

                if (email.isNotEmpty() && password.isNotEmpty()) {
                    FirebaseAuth.getInstance()
                        .signInWithEmailAndPassword(email, password)
                        .addOnCompleteListener(this) { task ->
                            if (task.isSuccessful) {
                                Log.d("AuthActivity", "Inicio de sesión exitoso")
                                val intent = Intent(this, Nivel::class.java)
                                startActivity(intent)
                                finish()
                                val editor = sharedPreferences.edit()
                                editor.putString("correo", email)
                                editor.putBoolean("googleSignIn", true)
                                editor.apply()
                            } else {
                                Log.w("AuthActivity", "Error en el inicio de sesión", task.exception)
                                Toast.makeText(this, "Error en el inicio de sesión", Toast.LENGTH_SHORT).show()
                            }
                        }
                } else {
                    Toast.makeText(this, "Por favor, ingrese correo y contraseña", Toast.LENGTH_SHORT).show()
                }
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

            val Anonimo = findViewById<ImageButton>(R.id.Anonimo)
            Anonimo.setOnClickListener {
                FirebaseAuth.getInstance().signInAnonymously()
                    .addOnCompleteListener(this) { task ->
                        if (task.isSuccessful) {
                            Log.d("AnonymousLoginActivity", "Inicio de sesión anónimo exitoso")
                            val intent = Intent(this, Nivel::class.java)
                            startActivity(intent)
                        } else {
                            Log.w("AnonymousLoginActivity", "Error en el inicio de sesión anónimo", task.exception)
                            Toast.makeText(this, "Error en el inicio de sesión anónimo", Toast.LENGTH_SHORT).show()
                        }
                    }
            }
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
                Toast.makeText(this, "Error al iniciar sesión con Google", Toast.LENGTH_SHORT).show()
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

                    val intent = Intent(this, Nivel::class.java)
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
            contra.inputType = android.text.InputType.TYPE_CLASS_TEXT or android.text.InputType.TYPE_TEXT_VARIATION_PASSWORD
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