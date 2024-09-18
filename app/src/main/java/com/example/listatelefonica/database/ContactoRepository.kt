package com.example.listatelefonica.database

import android.content.Context
import com.example.listatelefonica.model.Contacto

class ContactoRepository(context: Context) {

    private val databaseHelper = DatabaseHelper.getDatabase(context).contactoDAO()

    fun insert(contacto: Contacto): Long {
        return databaseHelper.insert(contacto)
    }

    fun update(contacto: Contacto): Int {
        return databaseHelper.update(contacto)
    }

    fun delete(contacto: Contacto): Int {
        return databaseHelper.delete(contacto)
    }

    fun selectAll(): List<Contacto> {
        return databaseHelper.selectAll()
    }

    fun select(id: Int): Contacto {
        return databaseHelper.select(id)
    }
}