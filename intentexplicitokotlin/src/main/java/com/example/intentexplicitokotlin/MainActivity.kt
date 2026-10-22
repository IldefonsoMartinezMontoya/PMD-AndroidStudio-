package com.example.intentexplicitokotlin

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.intentexplicitokotlin.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    companion object {
        const val EXTRA_NOTA1 = "nota1"
        const val EXTRA_NOTA2 = "nota2"
        const val EXTRA_NOTA3 = "nota3"
    }
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
        binding.Evaluar.setOnClickListener {
            val nota1 = binding.etNota1.text.toString()
            val nota2 = binding.etNota2.text.toString()
            val nota3 = binding.etNota3.text.toString()
            val intent = Intent(this, MainActivity2::class.java)
            intent.putExtra(EXTRA_NOTA1, nota1)
            intent.putExtra(EXTRA_NOTA2, nota2)
            intent.putExtra(EXTRA_NOTA3, nota3)
            startActivity(intent)
        }
    }
}