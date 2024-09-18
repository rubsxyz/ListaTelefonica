package com.example.listatelefonica.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.listatelefonica.database.ContactoRepository
import com.example.listatelefonica.model.Contacto


class MainViewModel(application: Application) : AndroidViewModel(application) {
    private val listaContacto: MutableLiveData<List<Contacto>> = MutableLiveData(listOf())

    private val contactoRepository = ContactoRepository(application.applicationContext)

    fun listaContactos(): LiveData<List<Contacto>> {
        return listaContacto
    }

    fun getListaContacto() {
        listaContacto.value = contactoRepository.selectAll()
    }
}