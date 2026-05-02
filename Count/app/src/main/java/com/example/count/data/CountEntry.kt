package com.example.count.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "counts")
data class CountEntry(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val value: Int,
    val increment: Int,
    val timestamp: Long = System.currentTimeMillis()
)
