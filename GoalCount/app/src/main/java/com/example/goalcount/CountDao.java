package com.example.goalcount;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface CountDao {
    @Insert
    void insert(CountEntry entry);

    @Delete
    void delete(CountEntry entry);

    @Query("SELECT * FROM count_entries ORDER BY timestamp DESC")
    LiveData<List<CountEntry>> getAllEntries();

    @Query("SELECT SUM(count) FROM count_entries")
    LiveData<Integer> getTotalCount();

    @Query("DELETE FROM count_entries")
    void deleteAll();
}
