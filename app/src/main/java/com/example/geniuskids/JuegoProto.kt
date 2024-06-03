package com.example.geniuskids

import android.graphics.Color
import android.graphics.Paint
import android.graphics.Path
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.geniuskids.PaintView.Companion.colorList
import com.example.geniuskids.PaintView.Companion.currentBrush
import com.example.geniuskids.PaintView.Companion.pathList

class JuegoProto : AppCompatActivity() {

    companion object{
        var path = Path()
        var paintBrush = Paint()
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_juego_proto)
        supportActionBar?.hide()

        val Rojo = findViewById<Button>(R.id.redButton)
        Rojo.setOnClickListener {
            Toast.makeText(this, "Rojo", Toast.LENGTH_SHORT).show()
            paintBrush.setColor(Color.RED)
            currentColor(paintBrush.color)
        }
        val Azul = findViewById<Button>(R.id.blueButton)
        Azul.setOnClickListener {
            Toast.makeText(this, "Azul", Toast.LENGTH_SHORT).show()
            paintBrush.setColor(Color.BLUE)
            currentColor(paintBrush.color)
        }
        val Pinzel = findViewById<ImageView>(R.id.btnPincel)
        Pinzel.setOnClickListener {
            Toast.makeText(this, "Pinzel", Toast.LENGTH_SHORT).show()
            paintBrush.setColor(Color.BLACK)
            currentColor(paintBrush.color)
        }
        val Borrador = findViewById<ImageView>(R.id.btnBorrador)
        Borrador.setOnClickListener {
            Toast.makeText(this, "Borrador", Toast.LENGTH_SHORT).show()
            pathList.clear()
            colorList.clear()
            path.reset()
        }
    }

    private fun currentColor(color: Int){
        currentBrush = color
        path = Path()
    }
}