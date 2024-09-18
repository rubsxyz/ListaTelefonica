package com.example.listatelefonica.database.dao

import androidx.room.*
import com.example.listatelefonica.model.Contacto


@Dao
interface ContactoDAO {

    @Insert
    fun insert(contacto: Contacto): Long

    @Update
    fun update(contacto: Contacto): Int

    @Delete
    fun delete(contacto: Contacto): Int

    @Query("SELECT * FROM Contacto")
    fun selectAll(): List<Contacto>

    @Query("SELECT * FROM Contacto WHERE id=:id")
    fun select(id: Int): Contacto
}