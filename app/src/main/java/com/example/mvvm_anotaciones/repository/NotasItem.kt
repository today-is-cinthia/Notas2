package com.example.mvvm_anotaciones.repository

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "Anotacion")
data class NotasItem (@PrimaryKey(autoGenerate = true) var id:Long=0,
                      var tarea:String ="",
                      var Finalizado: Boolean=false){
}
