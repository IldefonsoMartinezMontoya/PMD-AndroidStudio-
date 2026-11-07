package com.example.ejercicolistview

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.ejercicolistview.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(binding.root) {vista, insets ->
            val barras = insets.getInsets(
                WindowInsetsCompat.Type.systemBars() or WindowInsetsCompat.Type.displayCutout()
            )
            val extra = (16 * resources.displayMetrics.density).toInt()
            vista.setPadding(
                barras.left + extra,
                barras.top + extra,
                barras.right + extra,
                barras.bottom + extra
            )
            insets
        }
        val personas = listOf(
            Persona("Miguel", "López Sánchez", "Hombre", "ASIR"),
            Persona("Juan", "Pérez Pérez", "Hombre", "DAW"),
            Persona("María", "Martínez Fernández", "Mujer", "DAM"),
            Persona("Antonio", "González García", "Hombre", "DAW"),
            Persona("Ana", "Díaz Torres", "Mujer", "ASIR")
        )

        binding.listView.adapter = PersonaAdapter(this, personas)

        binding.listView.setOnItemClickListener { _, _, position, _ ->
            val persona = personas[position]
            val intent = android.content.Intent(this, ActivityModulos::class.java).apply {
                putExtra(ActivityModulos.EXTRA_NOMBRE, "${persona.nombre} ${persona.apellidos}")
                putExtra(ActivityModulos.EXTRA_CICLO, persona.ciclo)
            }
            startActivity(intent)
        }
    }
}
