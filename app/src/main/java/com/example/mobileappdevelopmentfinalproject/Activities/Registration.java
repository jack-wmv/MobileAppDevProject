package com.example.mobileappdevelopmentfinalproject.Activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Database;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.mobileappdevelopmentfinalproject.Database.FitnessDatabase;
import com.example.mobileappdevelopmentfinalproject.R;
import com.example.mobileappdevelopmentfinalproject.entities.Login;
import com.example.mobileappdevelopmentfinalproject.entities.User;

public class Registration extends AppCompatActivity {

    private TextView usernameView;
    private TextView passwordView;
    private TextView firstnameView;
    private TextView lastnameView;
    private TextView ageView;
    private TextView heightView;
    private TextView weightView;
    final User user = new User();
    final Login login = new Login();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);

        usernameView = (TextView) findViewById(R.id.UserName_reg);
        passwordView = (TextView) findViewById(R.id.Password_reg);
        firstnameView = (TextView) findViewById(R.id.firstname_reg);
        lastnameView = (TextView) findViewById(R.id.lastname_reg);
        ageView = (TextView) findViewById(R.id.age_reg);
        heightView = (TextView) findViewById(R.id.height_reg);
        weightView = (TextView) findViewById(R.id.weight_reg);

        Button btn = findViewById(R.id.register_btn);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String userText = usernameView.getText().toString();
                String passText = passwordView.getText().toString();
                String firstText = firstnameView.getText().toString();
                String lastText = lastnameView.getText().toString();
                String ageText = ageView.getText().toString();
                String heightText = heightView.getText().toString();
                String weightText = weightView.getText().toString();

                if (userText.isEmpty() || passText.isEmpty() || firstText.isEmpty() || lastText.isEmpty() || ageText.isEmpty() || heightText.isEmpty()|| weightText.isEmpty()) {
                    Toast.makeText(getApplicationContext(), "Please Enter Your Credentials", Toast.LENGTH_SHORT).show();
                } else {
                    Intent intent = new Intent(Registration.this, LoginPage.class);
                    user.setUsername(userText);
                    user.setFirstName(firstText);
                    user.setLastName(lastText);
                    user.setAge(ageText);
                    user.setHeight(heightText);
                    user.setWeight(weightText);
                    SaveUserInfo();

                    login.setUsername(userText);
                    login.setPassword(passText);
                    SaveLoginInfo();
                }
            }
        });
    }


    private void SaveUserInfo() {
        @SuppressLint("StaticFieldLeak")
        class SaveUserInfoTask extends AsyncTask<Void, Void, Void> {
            @Override
            protected Void doInBackground(Void... voids) {
                FitnessDatabase.getFitnessDatabase(getApplicationContext()).userDao().insert(user);
                return null;
            }

            @Override
            protected void onPostExecute(Void aVoid) {
                super.onPostExecute(aVoid);
                Intent intent = new Intent();
                setResult(RESULT_OK, intent);
                finish();
            }
        }
        new SaveUserInfoTask().execute();
    }

    private void SaveLoginInfo() {

        @SuppressLint("StaticFieldLeak")
        class SaveLoginInfoTask extends AsyncTask<Void, Void, Void> {
            @Override
            protected Void doInBackground(Void... voids) {
                FitnessDatabase.getFitnessDatabase(getApplicationContext()).loginDao().insert(login);
                return null;
            }

            @Override
            protected void onPostExecute(Void aVoid) {
                super.onPostExecute(aVoid);
                Intent intent = new Intent();
                setResult(RESULT_OK, intent);
                finish();
            }

        }
        new SaveLoginInfoTask().execute();
    }
}

