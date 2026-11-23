package es.iesoretania.receta.fragmentos

import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.fragment.app.Fragment
import es.iesoretania.receta.R

class PasoFragment : Fragment(R.layout.fragment_paso) {

    val numeroPaso: Int
        get() = requireArguments().getInt(ARG_NUMERO_PASO)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val pasos = resources.getStringArray(R.array.pasos)
        view.findViewById<TextView>(R.id.tvContadorPaso).text =
            getString(R.string.contador_pasos, numeroPaso + 1, pasos.size)
        view.findViewById<TextView>(R.id.tvTextoPaso).text = pasos[numeroPaso]
    }

    companion object {
        private const val ARG_NUMERO_PASO = "numero_paso"

        fun newInstance(numero: Int) = PasoFragment().apply {
            arguments = Bundle().apply { putInt(ARG_NUMERO_PASO, numero) }
        }
    }
}
