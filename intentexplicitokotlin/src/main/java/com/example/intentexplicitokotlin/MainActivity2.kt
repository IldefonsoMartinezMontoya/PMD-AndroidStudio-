package com.example.intentexplicitokotlin

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.intentexplicitokotlin.databinding.ActivityMain2Binding
import com.example.intentexplicitokotlin.databinding.ActivityMainBinding

class MainActivity2 : AppCompatActivity() {
    private lateinit var binding: ActivityMain2Binding
    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMain2Binding.inflate(layoutInflater)
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
        val nota1 = intent.getStringExtra(MainActivity.EXTRA_NOTA1) ?: "desconocido"
        binding.tvNota1.text = "Nota de Matemáticas $nota1"
        val nota2 = intent.getStringExtra(MainActivity.EXTRA_NOTA2) ?: "desconocido"
        binding.tvNota2.text = "Nota de Física $nota2"
        val nota3 = intent.getStringExtra(MainActivity.EXTRA_NOTA3) ?: "desconocido"
        binding.tvNota3.text = "Nota de Química $nota3"
        val media = (nota1.toFloat() + nota2.toFloat() + nota3.toFloat()) / 3f
        if (media >= 5) {
            binding.tvMedia.text = "Aprobado con media de ${media}"
        } else {
            binding.tvMedia.text = "Suspenso con media de ${media}"
        }
    }
}