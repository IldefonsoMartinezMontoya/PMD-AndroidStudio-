package es.iesoretania.receta

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import es.iesoretania.receta.databinding.ActivityMainBinding
import es.iesoretania.receta.fragmentos.FinDialog
import es.iesoretania.receta.fragmentos.InicioFragment
import es.iesoretania.receta.fragmentos.PasoFragment

/**
 * Activity de la receta paso a paso.
 * Ya tiene ViewBinding y el bloque de insets: no hace falta tocarlos.
 * Los fragmentos créalos en el paquete es.iesoretania.receta.fragmentos.
 */
class MainActivity : AppCompatActivity() {

    // Objeto generado por ViewBinding a partir de activity_main.xml
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()   // dibujamos de borde a borde

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // 16dp extra convertidos a píxeles según la densidad de la pantalla
        val extra = (16 * resources.displayMetrics.density).toInt()

        // Relleno = zona ocupada por las barras del sistema y la cámara + 16dp.
        // Solo se hace aquí, en la Activity: los fragmentos se dibujan dentro de binding.main.
        ViewCompat.setOnApplyWindowInsetsListener(binding.main) { vista, insets ->
            val barras = insets.getInsets(
                WindowInsetsCompat.Type.systemBars() or WindowInsetsCompat.Type.displayCutout()
            )
            vista.setPadding(
                barras.left + extra, barras.top + extra,
                barras.right + extra, barras.bottom + extra
            )
            insets
        }

        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(binding.contenedorDinamico.id, InicioFragment())
                .commit()
        }

        binding.botonSiguiente.setOnClickListener { avanzarPaso() }
        binding.botonAnterior.setOnClickListener { retrocederPaso() }
    }

    private fun avanzarPaso() {
        val pasos = resources.getStringArray(es.iesoretania.receta.R.array.pasos)
        when (val fragment = supportFragmentManager.findFragmentById(binding.contenedorDinamico.id)) {
            is PasoFragment -> {
                val siguiente = fragment.numeroPaso + 1
                if (siguiente < pasos.size) {
                    mostrarPaso(siguiente)
                } else {
                    FinDialog().show(supportFragmentManager, "fin")
                }
            }
            else -> mostrarPaso(0)
        }
    }

    private fun retrocederPaso() {
        when (val fragment = supportFragmentManager.findFragmentById(binding.contenedorDinamico.id)) {
            is PasoFragment -> {
                if (fragment.numeroPaso == 0) {
                    mostrarInicio()
                } else {
                    mostrarPaso(fragment.numeroPaso - 1)
                }
            }
            else -> Unit
        }
    }

    private fun mostrarInicio() {
        supportFragmentManager.beginTransaction()
            .replace(binding.contenedorDinamico.id, InicioFragment())
            .commit()
    }

    private fun mostrarPaso(numero: Int) {
        supportFragmentManager.beginTransaction()
            .replace(binding.contenedorDinamico.id, PasoFragment.newInstance(numero))
            .commit()
    }

    fun reiniciarReceta() {
        mostrarInicio()
    }
}
