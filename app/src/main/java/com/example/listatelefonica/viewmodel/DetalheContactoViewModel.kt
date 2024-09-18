package com.example.listatelefonica.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.listatelefonica.database.ContactoRepository
import com.example.listatelefonica.model.Contacto

class DetalheContactoViewModel(application: Application) : AndroidViewModel(application) {

    private var contacto = MutableLiveData<Contacto>()
    private var update = MutableLiveData<String>()
    private var delete = MutableLiveData<String>()
    private val contactoRepository = ContactoRepository(application.applicationContext)

    fun contacto(): LiveData<Contacto> {
        return contacto
    }

    fun update(): LiveData<String> {
        return update
    }

    fun delete(): LiveData<String> {
        return delete
    }

    fun getContacto(id: Int) {
        contacto.value = contactoRepository.select(id)
    }

    fun update(contacto: Contacto) {
        if (contactoRepository.update(contacto) > 0) {
            update.value = "Update OK"
        } else {
            update.value = "Erro no Update"
        }
    }

    fun delete(contacto: Contacto) {
        if (contactoRepository.delete(contacto) > 0) {
            delete.value = "Delete OK"
        } else {
            delete.value = "Erro no Delete"
        }
    }
}