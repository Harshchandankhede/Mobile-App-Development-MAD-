package com.example.count.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [CountEntry::class], version = 1, exportSchema = false)
abstract class CountDatabase : RoomDatabase() {
    abstract fun countDao(): CountDao

    companion object {
        @Volatile
        private var Instance: CountDatabase? = null

        fun getDatabase(context: Context): CountDatabase {
            return Instance ?: synchronized(this) {
                Room.databaseBuilder(context, CountDatabase::class.java, "count_database")
                    .build()
                    .also { Instance = it }
            }
        }
    }
}
