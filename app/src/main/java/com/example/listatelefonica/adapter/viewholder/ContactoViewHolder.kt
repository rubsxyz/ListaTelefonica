package com.example.listatelefonica.adapter.viewholder

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.listatelefonica.R


class ContactoViewHolder(view: View) : RecyclerView.ViewHolder(view) {
    val textNome: TextView = view.findViewById(R.id.text_nome)
}