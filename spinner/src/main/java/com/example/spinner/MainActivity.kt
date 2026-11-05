package com.example.spinner

import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.spinner.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val adapter = ArrayAdapter.createFromResource(
            this,
            R.array.operaciones,
            R.layout.spinner_item
        )
        adapter.setDropDownViewResource(R.layout.spinner_item)
        binding.spinner.adapter = adapter

        binding.spinner.onItemSelectedListener =
            object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                val numero1 = binding.etNumero1.text.toString().toDoubleOrNull()
                val numero2 = binding.etNumero2.text.toString().toDoubleOrNull()

                if (numero1 == null || numero2 == null) {
                    return
                } else {
                    val resultado = when (position) {
                        0 -> numero1 + numero2
                        1 -> numero1 - numero2
                        2 -> numero1 * numero2
                        3 -> {
                            if (numero2 == 0.0) {
                                Toast.makeText(this@MainActivity, "No se puede dividir por cero",
                                    Toast.LENGTH_SHORT).show()
                                return
                            }
                            numero1 / numero2
                        }
                        else -> return
                    }
                    binding.tvResultado.text = resultado.toString()
                }
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {}
        }
    }
}
