package com.example.listatelefonica.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.listatelefonica.R
import com.example.listatelefonica.adapter.viewholder.ContactoViewHolder
import com.example.listatelefonica.model.Contacto

class ContactoListAdapter(
    private var contactoList: List<Contacto>,
    private val onClick: (Contacto) -> Unit  // Lambda for click listener
) : RecyclerView.Adapter<ContactoViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ContactoViewHolder {
        return ContactoViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.row_contacto, parent, false)
        )
    }

    override fun getItemCount(): Int {
        return contactoList.size
    }

    override fun onBindViewHolder(holder: ContactoViewHolder, position: Int) {
        val contacto = contactoList[position]
        holder.textNome.text = contacto.nome

        // Set the click listener using the lambda
        holder.itemView.setOnClickListener {
            onClick(contacto)
        }
    }

    // Function to update the list of contacts
    fun updateContactList(newList: List<Contacto>) {
        contactoList = newList
        notifyDataSetChanged() // Notify the adapter that the data has changed
    }
}
