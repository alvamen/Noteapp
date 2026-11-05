package com.example.noteapp.data

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface ApunteDao {

    // Reemplaza a db.insert(ApuntesContract.TABLE, null, contentValues)
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertarApunte(apunte: Apunte)

    // Reemplaza a db.query(...) y mantiene el orden DEFAULT_SORT (creado_en DESC)
    @Query("SELECT * FROM apuntes ORDER BY creado_en DESC")
    fun obtenerApuntes(): Flow<List<Apunte>>
}
