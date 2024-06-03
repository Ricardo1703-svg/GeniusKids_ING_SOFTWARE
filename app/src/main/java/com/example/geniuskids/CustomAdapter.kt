package com.example.geniuskids

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.TextView

class CustomAdapter(private val context: Context, private val contenidoList: List<Contenido>) : BaseAdapter() {

    override fun getCount(): Int {
        return contenidoList.size
    }

    override fun getItem(position: Int): Any {
        return contenidoList[position]
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        val view: View
        val holder: ViewHolder

        if (convertView == null) {
            view = LayoutInflater.from(context).inflate(R.layout.item_contenido, parent, false)
            holder = ViewHolder()
            holder.tvId = view.findViewById(R.id.tvId)
            holder.tvNombre = view.findViewById(R.id.tvNombre)
            holder.tvDesarrollo = view.findViewById(R.id.tvDesarrollo)
            holder.tvActividad = view.findViewById(R.id.tvActividad)
            view.tag = holder
        } else {
            view = convertView
            holder = view.tag as ViewHolder
        }

        val contenido = contenidoList[position]
        holder.tvId?.text = contenido.id
        holder.tvNombre?.text = contenido.nombre
        holder.tvDesarrollo?.text = contenido.desarrollo
        holder.tvActividad?.text = contenido.actividad

        return view
    }

    private class ViewHolder {
        var tvId: TextView? = null
        var tvNombre: TextView? = null
        var tvDesarrollo: TextView? = null
        var tvActividad: TextView? = null
    }
}