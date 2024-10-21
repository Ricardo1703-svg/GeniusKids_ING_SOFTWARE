package com.example.geniuskids

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.example.geniuskids.Base_de_Datos.Ingresar_Datos
import com.example.geniuskids.Login.AuthActivity
import com.example.geniuskids.Materias.Materias
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.firebase.auth.FirebaseAuth

class Perfil : AppCompatActivity() {
    private lateinit var googleAccountStatus: TextView
    private lateinit var btnSignOut: Button
    private lateinit var fotoPerfil: ImageView
    private val PREFS_FILE = "myPrefs"
    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_perfil)

        auth = FirebaseAuth.getInstance()

        googleAccountStatus = findViewById(R.id.nombre_perfil)
        fotoPerfil = findViewById(R.id.foto_perfil)
        updateGoogleAccountStatus()

        val cerrarSesion = findViewById<Button>(R.id.btnCerrarSesion)
        cerrarSesion.setOnClickListener {
            signOut()
        }

        //-------------------Barra de Nvegacion------------------------------------------
        val Home = findViewById<ImageButton>(R.id.btnHome)
        Home.setOnClickListener {
            val intent = Intent(this, Materias::class.java)
            startActivity(intent)
        }

        val btnPerfil = findViewById<ImageButton>(R.id.btnPerfil)
        btnPerfil.setOnClickListener {
            val intent = Intent(this, Perfil::class.java)
            startActivity(intent)
        }

        val btnMaterias = findViewById<ImageButton>(R.id.btnMaterias)
        btnMaterias.setOnClickListener{
            val intent = Intent(this, Ingresar_Datos::class.java)
            startActivity(intent)
        }
        //-------------------------------------------------------------------------------
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
        val googleSignInClient = GoogleSignIn.getClient(this, GoogleSignInOptions.DEFAULT_SIGN_IN)

        googleSignInClient.silentSignIn().addOnCompleteListener(this) { task ->
            if (task.isSuccessful) {

                // Se obtuvo la cuenta actualizada con éxito
                val account = task.result
                if (account != null) {
                    googleAccountStatus.text = "${account.displayName}"

                    // Cargar la foto de perfil con Glide
                    val photoUrl = account.photoUrl
                    if (photoUrl != null) {
                        Glide.with(this)
                            .load(photoUrl)
                            .into(fotoPerfil)
                    } else {
                        fotoPerfil.setImageResource(R.drawable.logo)
                    }
                }
            } else {
                // Si no se pudo actualizar la cuenta, usar la versión en caché
                val cachedAccount = GoogleSignIn.getLastSignedInAccount(this)
                if (cachedAccount != null) {
                    googleAccountStatus.text = "${cachedAccount.displayName}"

                    // Cargar la foto de perfil desde la versión en caché
                    val photoUrl = cachedAccount.photoUrl
                    if (photoUrl != null) {
                        Glide.with(this)
                            .load(photoUrl)
                            .into(fotoPerfil)
                    } else {
                        fotoPerfil.setImageResource(R.drawable.logo)
                    }
                } else {
                    googleAccountStatus.text = "Cuenta de Google no Asociada"
                    fotoPerfil.setImageResource(R.drawable.logo)
                }
            }
        }
    }

}