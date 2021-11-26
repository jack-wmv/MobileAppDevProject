package com.example.mobileappdevelopmentfinalproject.Dao;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.example.mobileappdevelopmentfinalproject.entities.Fitness;

import java.util.List;

@Dao
public interface FitnessDao {
    @Query("SELECT * FROM Fitness")
    List<Fitness> getAll();

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(Fitness fitness);
}
