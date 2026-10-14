package com.example.pmd

import android.annotation.SuppressLint
import android.app.Activity
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.pmd.databinding.ActivityMainBinding
import java.math.BigDecimal
import java.math.RoundingMode

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnCalcular.setOnClickListener {
            sumar()
        }
    }
    @SuppressLint("SetTextI18n")
    private fun sumar() {
        val numero1 = binding.etNumero1.text.toString()
            .trim()
            .replace(',', '.')
            .toBigDecimalOrNull()
        val numero2 = binding.etNumero2.text.toString()
            .trim()
            .replace(',', '.')
            .toBigDecimalOrNull()
        val numero3 = binding.etNumero3.text.toString()
            .trim()
            .replace(',', '.')
            .toBigDecimalOrNull()
        if (numero1 == null || numero2 == null || numero3 == null) {
            binding.tvResultado.text = "Introduce tres números válidos"
            return
        }

        val media = numero1.add(numero2).add(numero3)
            .divide(BigDecimal("3"), 2, RoundingMode.HALF_UP)

        if (media >= BigDecimal(5)) {
            binding.tvResultado.text = "Aprobado con Media de: ${media.stripTrailingZeros().toPlainString()}"
        } else {
            binding.tvResultado.text = "Suspendido con Media de: ${media.stripTrailingZeros().toPlainString()}"
        }
    }
}
