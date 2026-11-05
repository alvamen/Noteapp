package com.example.noteapp.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.Room.databaseBuilder
import androidx.room.RoomDatabase

@Database(
    entities = [Apunte::class],
    version = Apunte.DB_VERSION,
    exportSchema = false
)
abstract class ApuntesDatabase : RoomDatabase() {

    abstract fun apunteDao(): ApunteDao

    companion object {
        @Volatile
        private var INSTANCE: ApuntesDatabase? = null

        fun getDatabase(context: Context): ApuntesDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = databaseBuilder(
                    context.applicationContext,
                    ApuntesDatabase::class.java,
                    Apunte.DB_NAME // "ApuntesDB.db"
                ).fallbackToDestructiveMigration(false).build()
                INSTANCE = instance
                instance
            }
        }
    }
}