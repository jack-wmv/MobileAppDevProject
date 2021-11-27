package com.example.mobileappdevelopmentfinalproject.Database;

import android.content.Context;

import androidx.room.Dao;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.example.mobileappdevelopmentfinalproject.Dao.FitnessDao;
import com.example.mobileappdevelopmentfinalproject.Dao.LoginDao;
import com.example.mobileappdevelopmentfinalproject.Dao.UserDao;
import com.example.mobileappdevelopmentfinalproject.entities.Fitness;
import com.example.mobileappdevelopmentfinalproject.entities.Login;
import com.example.mobileappdevelopmentfinalproject.entities.User;

@Database(
        entities = {
                Login.class,
                User.class,
                Fitness.class
        },
        version = 1,
        exportSchema = false
)

public abstract class FitnessDatabase extends RoomDatabase {
    private static FitnessDatabase fitnessDatabase;

    public static synchronized FitnessDatabase getFitnessDatabase(Context context) {
        if (fitnessDatabase == null) {
            fitnessDatabase = Room.databaseBuilder(context, FitnessDatabase.class,
                    "fitness_db").build();
        }
        return fitnessDatabase;
    }
    public abstract LoginDao loginDao();
    public abstract UserDao userDao();
    public abstract FitnessDao fitnessDao();
}
