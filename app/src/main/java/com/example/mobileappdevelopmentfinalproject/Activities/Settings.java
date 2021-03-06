package com.example.mobileappdevelopmentfinalproject.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.room.DatabaseConfiguration;
import androidx.room.InvalidationTracker;
import androidx.sqlite.db.SupportSQLiteOpenHelper;

import com.example.mobileappdevelopmentfinalproject.Dao.FitnessDao;
import com.example.mobileappdevelopmentfinalproject.Dao.LoginDao;
import com.example.mobileappdevelopmentfinalproject.Dao.UserDao;
import com.example.mobileappdevelopmentfinalproject.Database.FitnessDatabase;
import com.example.mobileappdevelopmentfinalproject.R;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class Settings extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.settings);

        FitnessDatabase db = new FitnessDatabase() {
            @Override
            public LoginDao loginDao() {
                return null;
            }

            @Override
            public UserDao userDao() {
                return null;
            }

            @Override
            public FitnessDao fitnessDao() {
                return null;
            }

            @NonNull
            @Override
            protected SupportSQLiteOpenHelper createOpenHelper(DatabaseConfiguration config) {
                return null;
            }

            @NonNull
            @Override
            protected InvalidationTracker createInvalidationTracker() {
                return null;
            }

            @Override
            public void clearAllTables() {

            }
        };

        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);

        navigation.setSelectedItemId(R.id.settingsItem);
        Button changePass = findViewById(R.id.btnChangePassword);
        Button editInfo = findViewById(R.id.btnEditPersonalInfor);

        changePass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Settings.this, ChangePassword.class);
                startActivity(intent);
            }
        });
        editInfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Settings.this, EditPersonalInfo.class);
                startActivity(intent);
            }
        });

        navigation.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.settingsItem:
                        return true;
                    case R.id.mapsItem:
                        Intent a = new Intent(Settings.this, Maps.class);
                        startActivity(a);
                        return true;
                    case R.id.profileItem:
                        Intent b = new Intent(Settings.this, Profile.class);
                        startActivity(b);
                        return true;
                    case R.id.homeItem:
                        Intent c = new Intent(Settings.this, HomePage.class);
                        startActivity(c);
                        return true;
                }
                return true;
            }
        });
    }
}
