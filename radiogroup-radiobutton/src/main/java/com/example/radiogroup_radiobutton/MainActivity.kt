package com.example.radiogroup_radiobutton

import android.annotation.SuppressLint
import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.radiogroup_radiobutton.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnSuma.setOnClickListener {
            val valor1 = binding.etValor1.text.toString()
            val valor2 = binding.etValor2.text.toString()
            binding.tvResultado.text = "${valor1.toInt() + valor2.toInt()}"
        }
        binding.btnResta.setOnClickListener {
            val valor1 = binding.etValor1.text.toString()
            val valor2 = binding.etValor2.text.toString()
            binding.tvResultado.text = "${valor1.toInt() - valor2.toInt()}"
        }
        binding.btnMulti.setOnClickListener {
            val valor1 = binding.etValor1.text.toString()
            val valor2 = binding.etValor2.text.toString()
            binding.tvResultado.text = "${valor1.toInt() * valor2.toInt()}"
        }
        binding.btnDivision.setOnClickListener {
            val valor1 = binding.etValor1.text.toString()
            val valor2 = binding.etValor2.text.toString()
            if (valor2 == "0") {
                val mensaje = "No se puede dividir por cero"
                val duracion = Toast.LENGTH_SHORT
                val toast = Toast.makeText(this, mensaje, duracion)
                toast.show()
            } else {
                binding.tvResultado.text = "${valor1.toInt() / valor2.toInt()}"
            }
        }
    }
}