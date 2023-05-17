package com.example.mvvm_anotaciones.repository

import androidx.room.*
import kotlinx.coroutines.flow.Flow

//Objeto que nos permite asociar queries con llamadas a metodos
//Objeto que nos permite asociar queries con llamadas a metodos
@Dao
interface AnotacionDAO {
    @Query("SELECT * FROM Anotacion ORDER BY tarea ASC")
    fun getAllTask(): Flow<List<Anotacion>>

    @Insert
    suspend fun insert(tarea: Anotacion)

    @Update
    suspend fun update(tarea: Anotacion)

    @Delete
    suspend fun deleteTask(tarea: Anotacion)

    @Query("DELETE FROM Anotacion")
    suspend fun deleteAll()

}