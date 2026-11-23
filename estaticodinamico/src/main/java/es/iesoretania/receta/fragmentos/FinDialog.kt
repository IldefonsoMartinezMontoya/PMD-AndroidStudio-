package es.iesoretania.receta.fragmentos

import android.app.Dialog
import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.DialogFragment
import es.iesoretania.receta.MainActivity
import es.iesoretania.receta.R

class FinDialog : DialogFragment() {

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog =
        AlertDialog.Builder(requireContext())
            .setTitle(R.string.fin_titulo)
            .setMessage(R.string.fin_mensaje)
            .setPositiveButton(R.string.aceptar) { _, _ ->
                (activity as? MainActivity)?.reiniciarReceta()
            }
            .create()
}
