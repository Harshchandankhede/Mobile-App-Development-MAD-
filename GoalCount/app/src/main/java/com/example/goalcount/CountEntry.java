package com.example.goalcount;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "count_entries")
public class CountEntry {
    @PrimaryKey(autoGenerate = true)
    public int id;
    public int count;
    public long timestamp;

    public CountEntry(int count, long timestamp) {
        this.count = count;
        this.timestamp = timestamp;
    }
}
