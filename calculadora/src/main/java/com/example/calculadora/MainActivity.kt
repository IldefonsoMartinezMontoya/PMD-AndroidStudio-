package com.example.calculadora

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.calculadora.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    private var primerOperando: Int? = null
    private var operacionPendiente: String? = null
    private var empezarNumeroNuevo = true

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        ViewCompat.setOnApplyWindowInsetsListener(binding.root) { view, windowInsets ->
            val insets = windowInsets.getInsets(
                WindowInsetsCompat.Type.systemBars() or WindowInsetsCompat.Type.displayCutout()
            )
            view.setPadding(insets.left, insets.top, insets.right, insets.bottom)
            windowInsets
        }
        ViewCompat.requestApplyInsets(binding.root)

        configurarBotones()
    }

    private fun configurarBotones() {
        val botonesDigitos = listOf(
            binding.btn0, binding.btn1, binding.btn2, binding.btn3, binding.btn4,
            binding.btn5, binding.btn6, binding.btn7, binding.btn8, binding.btn9
        )
        botonesDigitos.forEach { boton ->
            boton.setOnClickListener { pulsarDigito(boton.text.toString()) }
        }

        val botonesOperadores = listOf(
            binding.btnSum, binding.btnRes, binding.btnMult, binding.btnDiv
        )
        botonesOperadores.forEach { boton ->
            boton.setOnClickListener { pulsarOperador(boton.text.toString()) }
        }

        binding.btnIQ.setOnClickListener { pulsarIgual() }
        binding.textView.setOnClickListener { borrar() }
    }

    private fun pulsarDigito(digito: String) {
        val pantalla = binding.textView.text.toString()
        when {
            empezarNumeroNuevo || pantalla == "0" || pantalla == "Error" -> {
                binding.textView.text = digito
                empezarNumeroNuevo = false
            }
            pantalla.length < 9 -> binding.textView.append(digito)
        }
    }

    private fun pulsarOperador(operador: String) {
        val numeroEnPantalla = binding.textView.text.toString().toIntOrNull() ?: return

        if (operacionPendiente != null && !empezarNumeroNuevo) {
            val primerNumero = primerOperando ?: return
            val resultado = calcular(primerNumero, operacionPendiente!!, numeroEnPantalla)
            if (resultado == null) {
                mostrarError()
                return
            }
            binding.textView.text = resultado.toString()
            primerOperando = resultado
            operacionPendiente = operador
            empezarNumeroNuevo = true
        } else if (primerOperando == null) {
            primerOperando = numeroEnPantalla
            operacionPendiente = operador
            empezarNumeroNuevo = true
        } else {
            // Ya hay una operación pendiente y aún no se ha escrito el segundo número.
            operacionPendiente = operador
        }
    }

    private fun pulsarIgual() {
        val primerNumero = primerOperando ?: return
        val operacion = operacionPendiente ?: return
        if (empezarNumeroNuevo) return
        val segundoNumero = binding.textView.text.toString().toIntOrNull() ?: return

        val resultado = calcular(primerNumero, operacion, segundoNumero)
        if (resultado == null) {
            mostrarError()
            return
        }

        binding.textView.text = resultado.toString()
        primerOperando = null
        operacionPendiente = null
        empezarNumeroNuevo = true
    }

    private fun calcular(a: Int, operacion: String, b: Int): Int? = try {
        when (operacion) {
            "+" -> a + b
            "-" -> a - b
            "*" -> a * b
            "/" -> a / b
            else -> null
        }
    } catch (_: ArithmeticException) {
        null
    }

    private fun mostrarError() {
        binding.textView.text = getString(R.string.calculator_error)
        primerOperando = null
        operacionPendiente = null
        empezarNumeroNuevo = true
    }

    private fun borrar() {
        binding.textView.text = getString(R.string.calculator_zero)
        primerOperando = null
        operacionPendiente = null
        empezarNumeroNuevo = true
    }
}
