package com.example.geniuskids

import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.ListView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.database.*

class VisualizarDatos : AppCompatActivity() {
    private lateinit var database: DatabaseReference
    private lateinit var listView: ListView
    private lateinit var contenidoList: MutableList<Contenido>
    private lateinit var adapter: CustomAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_visualizar_datos)
        enableEdgeToEdge()

        database = FirebaseDatabase.getInstance().reference.child("contenido")
        listView = findViewById(R.id.listView)
        contenidoList = mutableListOf()

        adapter = CustomAdapter(this, contenidoList)
        listView.adapter = adapter

        database.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                contenidoList.clear()
                for (snapshot in dataSnapshot.children) {
                    val contenido = snapshot.getValue(Contenido::class.java)
                    contenido?.let {
                        contenidoList.add(it)
                    }
                }
                adapter.notifyDataSetChanged()
            }

            override fun onCancelled(databaseError: DatabaseError) {
                // Manejar el error en la recuperación de datos
            }
        })
    }
}