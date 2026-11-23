package es.iesoretania.receta.fragmentos

import android.os.Bundle
import android.view.View
import android.widget.LinearLayout
import android.widget.TextView
import androidx.fragment.app.Fragment
import es.iesoretania.receta.R

class IngredientesFragment : Fragment(R.layout.fragment_ingredientes) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val lista = view.findViewById<LinearLayout>(R.id.listaIngredientes)
        val ingredientes = resources.getStringArray(R.array.ingredientes)
        ingredientes.forEach { ingrediente ->
            val texto = TextView(requireContext()).apply {
                text = getString(R.string.vineta, ingrediente)
                textSize = 16f
                setPadding(0, 4, 0, 4)
            }
            lista.addView(texto)
        }
    }
}
