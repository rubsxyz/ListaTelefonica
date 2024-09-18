package com.example.listatelefonica.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.listatelefonica.database.dao.ContactoDAO
import com.example.listatelefonica.model.Contacto


@Database(entities = [Contacto::class], version = 3)
abstract class DatabaseHelper : RoomDatabase() {

    abstract fun contactoDAO(): ContactoDAO

    companion object {
        private lateinit var INSTANCE: DatabaseHelper

        fun getDatabase(context: Context): DatabaseHelper {
            if (!::INSTANCE.isInitialized) {
                synchronized(DatabaseHelper::class.java) {
                    INSTANCE = Room.databaseBuilder(context, DatabaseHelper::class.java, "database")
                        .allowMainThreadQueries()
                        .fallbackToDestructiveMigration()
                        .build()
                }
            }
            return INSTANCE
        }
    }

}