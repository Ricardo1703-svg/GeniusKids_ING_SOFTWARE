package com.example.geniuskids

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.ImageButton
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.firebase.auth.FirebaseAuth

class Perfil : AppCompatActivity() {
    private lateinit var googleAccountStatus: TextView
    private lateinit var btnSignOut: Button
    private val PREFS_FILE = "myPrefs"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        setContentView(R.layout.activity_perfil)
        googleAccountStatus = findViewById(R.id.nombre_perfil)
        updateGoogleAccountStatus()

        val cerrarSesion = findViewById<ImageButton>(R.id.btnCerrarSesion)
        cerrarSesion.setOnClickListener {
            signOut()
        }
    }

    private fun signOut() {
        val sharedPreferences = getSharedPreferences(PREFS_FILE, Context.MODE_PRIVATE)
        val editor = sharedPreferences.edit()
        editor.remove("googleSignIn")
        editor.remove("correo")
        editor.remove("contra")
        editor.apply()

        FirebaseAuth.getInstance().signOut()

        signOutFromGoogle()
    }

    private fun signOutFromGoogle() {
        val googleClient = GoogleSignIn.getClient(this, GoogleSignInOptions.DEFAULT_SIGN_IN)
        googleClient.signOut().addOnCompleteListener(this) {
            if (it.isSuccessful) {
                FirebaseAuth.getInstance().signOut()

                val sharedPreferences = getSharedPreferences(PREFS_FILE, Context.MODE_PRIVATE)
                val editor = sharedPreferences.edit()
                editor.putBoolean("googleSignIn", false)
                editor.apply()

                updateGoogleAccountStatus()

                Toast.makeText(this, "Sesión de Google cerrada", Toast.LENGTH_SHORT).show()

                val intent = Intent(this, AuthActivity::class.java)
                startActivity(intent)
                finish()
            } else {
                Toast.makeText(this, "Error al cerrar sesión de Google", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun updateGoogleAccountStatus() {
        val account = GoogleSignIn.getLastSignedInAccount(this)
        if (account != null) {
            googleAccountStatus.text = "${account.displayName}"
        } else {
            googleAccountStatus.text = "Estado de la cuenta de Google: No conectado"
        }
    }
}