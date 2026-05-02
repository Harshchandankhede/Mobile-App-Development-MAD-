package com.example.count.data

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface CountDao {
    @Query("SELECT * FROM counts ORDER BY timestamp DESC")
    fun getAllCounts(): Flow<List<CountEntry>>

    @Query("SELECT * FROM counts ORDER BY id DESC LIMIT 1")
    suspend fun getLastCount(): CountEntry?

    @Insert
    suspend fun insert(entry: CountEntry)

    @Query("DELETE FROM counts")
    suspend fun deleteAll()
}
