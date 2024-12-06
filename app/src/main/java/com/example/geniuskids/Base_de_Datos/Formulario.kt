package com.example.geniuskids.Base_de_Datos

import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.EditText
import android.widget.NumberPicker
import android.widget.RadioGroup
import android.widget.Spinner
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.geniuskids.R
import com.google.firebase.firestore.FirebaseFirestore


class Formulario : AppCompatActivity() {

    // Variables para los elementos de la UI
    private lateinit var editTextVideoName: EditText
    private lateinit var editTextVideoUrl: EditText
    private lateinit var radioGroupCategory: RadioGroup
    private lateinit var spinnerMateria: Spinner
    private lateinit var spinnerDificultad: Spinner
    private lateinit var numberPickerOrder: NumberPicker
    private lateinit var buttonSave: Button

    private lateinit var buttonShowVideos: Button
    private lateinit var txterror: TextView

    // Instancia de Firebase Firestore
    private val db = FirebaseFirestore.getInstance()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_formulario)

        // Inicializar elementos de la UI
        editTextVideoName = findViewById(R.id.editTextVideoName)
        editTextVideoUrl = findViewById(R.id.editTextVideoUrl)
        radioGroupCategory = findViewById(R.id.radioGroupCategory)
        spinnerMateria = findViewById(R.id.spinnerMateria)
        spinnerDificultad = findViewById(R.id.spinnerDificultad)
        buttonSave = findViewById(R.id.buttonSave)
        numberPickerOrder = findViewById(R.id.numberPickerOrder)
        buttonShowVideos = findViewById(R.id.buttonShowVideos)
        txterror = findViewById(R.id.txterror)

        // Configurar opciones para el Spinner de Materias
        val materias = listOf("Sociales", "Lenguaje", "Matemática", "Ciencias")
        val materiaAdapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, materias)
        materiaAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinnerMateria.adapter = materiaAdapter

        // Configurar opciones para el Spinner de Dificultad
        val dificultades = listOf("Fácil", "Intermedio", "Avanzado")
        val dificultadAdapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, dificultades)
        dificultadAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinnerDificultad.adapter = dificultadAdapter

        // Configurar el NumberPicker
        numberPickerOrder.minValue = 1
        numberPickerOrder.maxValue = 5
        numberPickerOrder.value = 1 // Valor predeterminado

        // Configurar eventos de los botones
        buttonSave.setOnClickListener {
            saveVideoUrl()
        }

        buttonShowVideos.setOnClickListener {
            Toast.makeText(this, "Función para mostrar videos aún no implementada", Toast.LENGTH_SHORT).show()
        }
    }

    private fun saveVideoUrl() {
        val videoName = editTextVideoName.text.toString().trim()
        val videoUrl = editTextVideoUrl.text.toString().trim()
        val selectedCategoryId = radioGroupCategory.checkedRadioButtonId
        val orderNumber = numberPickerOrder.value
        val materia = spinnerMateria.selectedItem.toString()
        val dificultad = spinnerDificultad.selectedItem.toString()

        // Determinar la categoría seleccionada
        val category = when (selectedCategoryId) {
            R.id.radioContenido -> "contenido"
            R.id.radioActividad -> "actividad"
            else -> ""
        }

        // Validar que todos los campos estén completos
        if (videoName.isEmpty() || videoUrl.isEmpty() || category.isEmpty()) {
            Toast.makeText(this, "Por favor, completa todos los campos", Toast.LENGTH_SHORT).show()
            return
        }

        // Crear el mapa de datos para Firestore
        val videoData = hashMapOf(
            "videoName" to videoName,
            "videoUrl" to videoUrl,
            "category" to category,
            "materia" to materia,
            "dificultad" to dificultad,
            "orderNumber" to orderNumber
        )

        // Guardar los datos en Firestore
        db.collection("videos").add(videoData)
            .addOnSuccessListener {
                Toast.makeText(this, "Video guardado exitosamente", Toast.LENGTH_SHORT).show()
                limpiarFormulario()
            }
            .addOnFailureListener { e ->
                Toast.makeText(this, "Error al guardar: ${e.message}", Toast.LENGTH_SHORT).show()
                txterror.text = "Error al guardar: ${e.message}"
                txterror.visibility = TextView.VISIBLE
            }
    }

    private fun limpiarFormulario(){
       // editTextVideoName.text.clear()
        editTextVideoUrl.text.clear()
        //radioGroupCategory.clearCheck()
        //numberPickerOrder.value = 1
        //spinnerMateria.setSelection(0)
        //spinnerDificultad.setSelection(0)
        //txterror.visibility = TextView.GONE
    }
}