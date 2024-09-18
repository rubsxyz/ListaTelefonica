package com.example.listatelefonica.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.listatelefonica.database.ContactoRepository
import com.example.listatelefonica.model.Contacto

class NovoContactoViewModel(application: Application) : AndroidViewModel(application) {

    private val novoContacto = MutableLiveData<Long>()
    private val repository = ContactoRepository(application)

    fun novoContacto(): LiveData<Long> {
        return novoContacto
    }

    fun insert(nome: String, email: String, telefone: String) {
        novoContacto.value = repository.insert(
            Contacto(
                nome = nome,
                email = email,
                telefone = telefone,
            )
        )
    }

}