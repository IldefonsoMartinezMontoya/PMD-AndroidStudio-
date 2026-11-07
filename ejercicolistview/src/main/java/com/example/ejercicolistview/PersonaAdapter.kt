package com.example.ejercicolistview

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ImageView
import android.widget.TextView

class PersonaAdapter(
    private val context: Context,
    private val personas: List<Persona>
) : BaseAdapter() {

    override fun getCount() = personas.size

    override fun getItem(position: Int) = personas[position]

    override fun getItemId(position: Int) = position.toLong()

    override fun getView(
        position: Int,
        convertView: View?,
        parent: ViewGroup
    ): View {
        val fila = convertView
            ?: LayoutInflater.from(context)
                .inflate(R.layout.item, parent, false)

        val persona = getItem(position)

        fila.findViewById<ImageView>(R.id.avatar).setImageResource(
            if (persona.sexo == "Hombre") R.drawable.img
            else R.drawable.img_1
        )
        fila.findViewById<TextView>(R.id.nombre).text = persona.nombre
        fila.findViewById<TextView>(R.id.apellidos).text = persona.apellidos
        fila.findViewById<TextView>(R.id.ciclo).text = persona.ciclo

        return fila
    }
}