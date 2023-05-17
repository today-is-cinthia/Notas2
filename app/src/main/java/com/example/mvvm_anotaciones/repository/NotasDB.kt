package com.example.mvvm_anotaciones.repository

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [NotasItem::class],version = 1)
abstract class NotasDB : RoomDatabase(){

    abstract fun getNotesDao() : AnotacionDAO

    companion object{

        private var instance:NotasDB? = null
        private val LOCK = Any()

        operator fun invoke(context: Context) = instance?: synchronized(LOCK){
            instance ?: createDatabase(context).also { instance = it }
        }

        private fun createDatabase(context: Context) =
            Room.databaseBuilder(context.applicationContext,
                NotasDB::class.java,
                "notes_db").build()
    }
}
