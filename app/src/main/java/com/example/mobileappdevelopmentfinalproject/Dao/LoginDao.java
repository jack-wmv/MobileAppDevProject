package com.example.mobileappdevelopmentfinalproject.Dao;


import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.example.mobileappdevelopmentfinalproject.entities.Login;

import java.util.List;

@Dao
public interface LoginDao {

    @Query("SELECT * FROM Login")
    List<Login> getAll();

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(Login login);

    @Query("SELECT * FROM Login Where username = :username and password = :password")
    Login getCredentials(String username, String password);
}
