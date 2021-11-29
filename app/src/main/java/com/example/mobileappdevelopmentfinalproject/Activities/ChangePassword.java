package com.example.mobileappdevelopmentfinalproject.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import com.example.mobileappdevelopmentfinalproject.Dao.LoginDao;
import com.example.mobileappdevelopmentfinalproject.Dao.UserDao;
import com.example.mobileappdevelopmentfinalproject.Database.FitnessDatabase;
import com.example.mobileappdevelopmentfinalproject.R;
import com.example.mobileappdevelopmentfinalproject.entities.Login;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class ChangePassword extends AppCompatActivity {

    EditText oldPass,newPass,checkPass;
    String oldPassString, newPassString, newPassStringAgain;

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


    public void onSubmitPassword() {
        FitnessDatabase database = Room.databaseBuilder(this, FitnessDatabase.class, "fitness_db")
                .allowMainThreadQueries()
                .build();
        LoginDao loginDaoObject = database.loginDao();


        oldPass = (EditText) findViewById(R.id.oldPassEdit);
        oldPassString = oldPass.getText().toString();
        newPass = (EditText) findViewById(R.id.editTextNewPass);
        newPassString = newPass.getText().toString();
        oldPass = (EditText) findViewById(R.id.editTextNewPasswordAgain);
        newPassStringAgain = checkPass.getText().toString();


        Login login = loginDaoObject.getCredentials(LoginPage.UserText, oldPassString);

        if(login != null){
            if(newPassString.equals(newPassStringAgain)) {

                login.setPassword(newPassString);
                database.loginDao().insert(login);

                Intent intent = new Intent(ChangePassword.this, HomePage.class);
                startActivity(intent);
            }
            else{
                Toast.makeText(ChangePassword.this, "Please Retype the Same Password",Toast.LENGTH_SHORT).show();
            }
        }
        else {
            Toast.makeText(ChangePassword.this, "Incorrect Current Password",Toast.LENGTH_SHORT).show();
        }

    }

}



