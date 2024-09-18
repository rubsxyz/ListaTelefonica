package com.example.listatelefonica.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "Contacto")
data class Contacto(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val nome: String = "",
    val telefone: String = "",
    val email: String = ""
)