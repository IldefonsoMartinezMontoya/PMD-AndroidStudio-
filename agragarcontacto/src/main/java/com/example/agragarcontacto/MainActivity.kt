package com.example.agragarcontacto

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.agragarcontacto.databinding.ActivityMainBinding

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
        binding.btnLlamar.setOnClickListener {
            val numero = "112"
            val intent = Intent(Intent.ACTION_DIAL).apply {
                data = Uri.fromParts("tel", numero, null)
            }
            startActivity(intent)
        }
        binding.btnAgregar.setOnClickListener {
            val intent = Intent(this, MainActivity2::class.java)
            startActivity(intent)
        }
    }
}