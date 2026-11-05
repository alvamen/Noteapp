package com.example.noteapp.data

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

// Define la tabla 'apuntes' en SQLite
@Entity(tableName = Apunte.TABLE_NAME)
data class Apunte(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = COL_ID)
    val id: Int = 0,

    @ColumnInfo(name = COL_DETALLE)
    val detalleApunte: String,

    @ColumnInfo(name = COL_CREADO_EN)
    val creadoEn: Long = System.currentTimeMillis()
) {
    companion object {
        const val DB_NAME = "ApuntesDB.db"
        const val DB_VERSION = 1
        const val TABLE_NAME = "apuntes"
        const val COL_ID = "_id"
        const val COL_DETALLE = "detalle_apunte"
        const val COL_CREADO_EN = "creado_en"
    }
}
