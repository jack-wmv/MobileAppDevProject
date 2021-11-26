package com.example.mobileappdevelopmentfinalproject.Activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.mobileappdevelopmentfinalproject.Dao.LoginDao;
import com.example.mobileappdevelopmentfinalproject.Database.FitnessDatabase;
import com.example.mobileappdevelopmentfinalproject.R;
import com.example.mobileappdevelopmentfinalproject.entities.Login;
import com.example.mobileappdevelopmentfinalproject.entities.User;

import java.util.List;

public class LoginPage extends AppCompatActivity {

    private TextView username;
    private TextView password;
    private List<Login> loginList;
    LoginDao db;
    FitnessDatabase database;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_page);


        username = (TextView) findViewById(R.id.editTextTextPersonName);
        password = (TextView) findViewById(R.id.editTextTextPassword);

        database = Room.databaseBuilder(this,FitnessDatabase.class,"fitness_db")
                .allowMainThreadQueries()
                .build();

        db = database.loginDao();

        Button login = findViewById(R.id.loginButton);
        TextView Register = findViewById(R.id.register);

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String UserText = username.getText().toString();
                String PassText = password.getText().toString();

                Login data = db.getCredentials(UserText,PassText);

                if(UserText.isEmpty() || PassText.isEmpty()){
                    Toast.makeText(getApplicationContext(),"Please Enter Your Credentials",Toast.LENGTH_SHORT).show();
                }
                else {
                    if (data != null) {
                        Intent intent = new Intent(LoginPage.this, HomePage.class);
                        intent.putExtra("loginUsername", UserText);
                        startActivityForResult(intent, 1);
                    } else {
                        Toast.makeText(LoginPage.this, "Wrong Credentials", Toast.LENGTH_SHORT).show();
                    }
                }

            }
        });

        Register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(LoginPage.this, Registration.class);
                startActivity(intent);
            }
        });
    }
}