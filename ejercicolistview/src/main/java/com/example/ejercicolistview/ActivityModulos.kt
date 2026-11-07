package com.example.ejercicolistview

import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class ActivityModulos : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_modulos)

        val nombreCompleto = intent.getStringExtra(EXTRA_NOMBRE).orEmpty()
        val ciclo = intent.getStringExtra(EXTRA_CICLO).orEmpty()

        val fondo = findViewById<android.view.View>(R.id.contenidoModulos)
        val icono = findViewById<ImageView>(R.id.iconoModulo)
        val texto = findViewById<TextView>(R.id.textoModulo)
        val botonVolver = findViewById<Button>(R.id.botonVolver)

        val (color, recursoIcono) = when (ciclo) {
            "ASIR" -> R.color.asir to R.drawable.ic_asir
            "DAW" -> R.color.daw to R.drawable.ic_daw
            "DAM" -> R.color.dam to R.drawable.ic_dam
            else -> R.color.white to R.drawable.ic_asir
        }

        fondo.setBackgroundColor(getColor(color))
        icono.setImageResource(recursoIcono)
        texto.text = "$nombreCompleto\nEstás en el ciclo $ciclo"
        botonVolver.setOnClickListener { finish() }
    }

    companion object {
        const val EXTRA_NOMBRE = "com.example.ejercicolistview.EXTRA_NOMBRE"
        const val EXTRA_CICLO = "com.example.ejercicolistview.EXTRA_CICLO"
    }
}
