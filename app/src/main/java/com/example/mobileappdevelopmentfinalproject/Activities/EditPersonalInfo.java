package com.example.mobileappdevelopmentfinalproject.Activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.room.DatabaseConfiguration;
import androidx.room.InvalidationTracker;
import androidx.room.Room;
import androidx.sqlite.db.SupportSQLiteOpenHelper;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.mobileappdevelopmentfinalproject.Dao.FitnessDao;
import com.example.mobileappdevelopmentfinalproject.Dao.LoginDao;
import com.example.mobileappdevelopmentfinalproject.Dao.UserDao;
import com.example.mobileappdevelopmentfinalproject.Database.FitnessDatabase;
import com.example.mobileappdevelopmentfinalproject.R;
import com.example.mobileappdevelopmentfinalproject.entities.User;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class EditPersonalInfo extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_personal_info);

        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);

        navigation.setSelectedItemId(R.id.settingsItem);
        Button submit = findViewById(R.id.btnSubmitChange);

        submit.setOnClickListener(view -> onSubmitPressed());

        navigation.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.settingsItem:
                        return true;
                    case R.id.mapsItem:
                        Intent a = new Intent(EditPersonalInfo.this, Maps.class);
                        startActivity(a);
                        return true;
                    case R.id.profileItem:
                        Intent b = new Intent(EditPersonalInfo.this, Profile.class);
                        startActivity(b);
                        return true;
                    case R.id.homeItem:
                        Intent c = new Intent(EditPersonalInfo.this, HomePage.class);
                        startActivity(c);
                        return true;
                }
                return true;
            }
        });

    }

    /**
    public void onSubmitPressed() {
        FitnessDatabase database = Room.databaseBuilder(this, FitnessDatabase.class, "fitness_db")
                .allowMainThreadQueries()
                .build();
        UserDao userDaoObject = database.userDao();
        UserDao user = userDaoObject.getInfo();

    }
     **/
}



