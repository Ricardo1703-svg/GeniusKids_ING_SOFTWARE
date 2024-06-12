package com.example.geniuskids.Base_de_Datos

import android.os.Bundle
import android.widget.ListView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import com.example.geniuskids.R
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener

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
            }
        })
    }
}