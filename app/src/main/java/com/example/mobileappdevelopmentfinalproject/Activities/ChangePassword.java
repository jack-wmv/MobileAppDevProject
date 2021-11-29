package com.example.mobileappdevelopmentfinalproject.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import com.example.mobileappdevelopmentfinalproject.Dao.LoginDao;
import com.example.mobileappdevelopmentfinalproject.Database.FitnessDatabase;
import com.example.mobileappdevelopmentfinalproject.R;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class ChangePassword extends AppCompatActivity {

    EditText oldPass,newPass,checkPass;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_password);

        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);

        navigation.setSelectedItemId(R.id.settingsItem);
        Button submit = findViewById(R.id.btnSubmit);

        oldPass = findViewById(R.id.editTextTextPassword);
        newPass = findViewById(R.id.editTextNewPass);
        checkPass = findViewById(R.id.editTextNewPasswordAgain);

        submit.setOnClickListener(View -> onSubmitPassword());

        navigation.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.settingsItem:
                        return true;
                    case R.id.mapsItem:
                        Intent a = new Intent(ChangePassword.this, Maps.class);
                        startActivity(a);
                        return true;
                    case R.id.profileItem:
                        Intent b = new Intent(ChangePassword.this, Profile.class);
                        startActivity(b);
                        return true;
                    case R.id.homeItem:
                        Intent c = new Intent(ChangePassword.this, HomePage.class);
                        startActivity(c);
                        return true;
                }
                return true;
            }
        });

    }

    /**
    public void onSubmitPassword() {
        FitnessDatabase database = Room.databaseBuilder(this, FitnessDatabase.class, "fitness_db")
                .allowMainThreadQueries()
                .build();
        LoginDao loginDaoObject = database.loginDao();
        //LoginDao login = loginDaoObject.getCredentials();

    }
     **/
}



