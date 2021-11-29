package com.example.mobileappdevelopmentfinalproject.Activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.room.DatabaseConfiguration;
import androidx.room.InvalidationTracker;
import androidx.room.Room;
import androidx.sqlite.db.SupportSQLiteOpenHelper;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.mobileappdevelopmentfinalproject.Dao.FitnessDao;
import com.example.mobileappdevelopmentfinalproject.Dao.LoginDao;
import com.example.mobileappdevelopmentfinalproject.Dao.UserDao;
import com.example.mobileappdevelopmentfinalproject.Database.FitnessDatabase;
import com.example.mobileappdevelopmentfinalproject.R;
import com.example.mobileappdevelopmentfinalproject.entities.Login;
import com.example.mobileappdevelopmentfinalproject.entities.User;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class EditPersonalInfo extends AppCompatActivity {

    EditText firstNameEditText;
    EditText lastNameEditText;
    EditText ageEditText;
    EditText heightEditText;
    EditText weightEditText;

    String firstNameString;
    String lastNameString;
    String ageString;
    String heightString;
    String weightString;

    FitnessDatabase database;
    UserDao userDaoObject;
    User user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_personal_info);

        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);

        navigation.setSelectedItemId(R.id.settingsItem);
        Button submit = findViewById(R.id.btnSubmitChange);

        firstNameEditText = (EditText) findViewById(R.id.editPersonalFirstName);
        lastNameEditText = (EditText) findViewById(R.id.editPersonalLastName);
        ageEditText = (EditText) findViewById(R.id.editPersonalAge);
        heightEditText = (EditText) findViewById(R.id.editPersonalHeight);
        weightEditText = (EditText) findViewById(R.id.editPersonalWeight);

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                firstNameString = firstNameEditText.getText().toString();
                lastNameString = lastNameEditText.getText().toString();
                ageString = ageEditText.getText().toString();
                heightString = heightEditText.getText().toString();
                weightString = weightEditText.getText().toString();

                if(firstNameString.isEmpty() || lastNameString.isEmpty() || ageString.isEmpty()
                        || heightString.isEmpty() || weightString.isEmpty()){
                    Toast.makeText(getApplicationContext(),"Please Enter Your Credentials",Toast.LENGTH_SHORT).show();
                }
                else {
                    onSubmitPressed();
                    Intent intent = new Intent(EditPersonalInfo.this, HomePage.class);
                    startActivity(intent);
                }
            }
        });

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

        database = Room.databaseBuilder(this, FitnessDatabase.class, "fitness_db")
                .allowMainThreadQueries()
                .build();
        userDaoObject = database.userDao();
        user = userDaoObject.getInfo(LoginPage.UserText);

        firstNameString = user.getFirstName();
        lastNameString = user.getLastName();
        ageString = user.getAge();
        heightString = user.getHeight();
        weightString = user.getWeight();

        firstNameEditText.setText(firstNameString);
        lastNameEditText.setText(lastNameString);
        ageEditText.setText(ageString);
        heightEditText.setText(heightString);
        weightEditText.setText(weightString);
    }


    public void onSubmitPressed() {

        database = Room.databaseBuilder(this, FitnessDatabase.class, "fitness_db")
                .allowMainThreadQueries()
                .build();
        userDaoObject = database.userDao();
        user = userDaoObject.getInfo(LoginPage.UserText);

        user.setFirstName(firstNameString);
        user.setLastName(lastNameString);
        user.setAge(ageString);
        user.setHeight(heightString);
        user.setWeight(weightString);

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

}



