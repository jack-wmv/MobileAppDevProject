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

    private TextView Username;
    private TextView Password;
    private TextView FirstName;
    private TextView LastName;
    private TextView Address;
    private TextView DateofBirth;
    final User user = new User();
    final Login login = new Login();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);

        Username = (TextView) findViewById(R.id.UserName_reg);
        Password = (TextView) findViewById(R.id.Password_reg);
        FirstName = (TextView) findViewById(R.id.firstname_reg);
        LastName = (TextView) findViewById(R.id.lastname_reg);
        Address = (TextView) findViewById(R.id.Address_reg);
        DateofBirth = (TextView) findViewById(R.id.dateofbirth_reg);

        Button btn = findViewById(R.id.register_btn);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String UserText = Username.getText().toString();
                String PassText = Password.getText().toString();
                String FirstText = FirstName.getText().toString();
                String LastText = LastName.getText().toString();
                String AddressText = Address.getText().toString();
                String BirthText = DateofBirth.getText().toString();


                if (UserText.isEmpty() || PassText.isEmpty() || FirstText.isEmpty() || LastText.isEmpty() || AddressText.isEmpty() || BirthText.isEmpty()) {
                    Toast.makeText(getApplicationContext(), "Please Enter Your Credentials", Toast.LENGTH_SHORT).show();
                } else {
                    Intent intent = new Intent(Registration.this, LoginPage.class);
                    user.setUsername(UserText);
                    user.setFirstName(FirstText);
                    user.setLastName(LastText);
                    user.setAddress(AddressText);
                    user.setDateOfBirth(BirthText);
                    SaveUserInfo();
                    login.setUsername(UserText);
                    login.setPassword(PassText);
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

