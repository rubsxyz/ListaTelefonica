package com.example.listatelefonica.adapter.listener

import com.example.listatelefonica.model.Contacto

class ContactoOnClickListener(val clickListener: (contacto: Contacto) -> Unit) {
    fun onClick(contacto: Contacto) = clickListener
}