package com.example.mobileappdevelopmentfinalproject.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import com.example.mobileappdevelopmentfinalproject.Dao.UserDao;
import com.example.mobileappdevelopmentfinalproject.Database.FitnessDatabase;
import com.example.mobileappdevelopmentfinalproject.R;
import com.example.mobileappdevelopmentfinalproject.entities.User;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class Profile extends AppCompatActivity {

    FitnessDatabase database;
    UserDao userDaoObject;
    private User user;

    TextView nameTextView;
    TextView ageTextView;
    TextView heightTextView;
    TextView weightTextView;
    TextView distanceTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.profile);

        nameTextView = (TextView) findViewById(R.id.nameProfile);
        ageTextView = (TextView) findViewById(R.id.ageProfile);
        heightTextView = (TextView) findViewById(R.id.heightProfile);
        weightTextView = (TextView) findViewById(R.id.weightProfile);
        distanceTextView = (TextView) findViewById(R.id.distanceProfile);


        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setSelectedItemId(R.id.profileItem);

        navigation.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.profileItem:
                        return true;
                    case R.id.mapsItem:
                        Intent a = new Intent(Profile.this, Maps.class);
                        startActivity(a);
                        return true;
                    case R.id.settingsItem:
                        Intent b = new Intent(Profile.this, Settings.class);
                        startActivity(b);
                        return true;
                    case R.id.homeItem:
                        Intent c = new Intent(Profile.this, HomePage.class);
                        startActivity(c);
                        return true;
                }
                return true;
            }
        });

        setProfileInfo();

    }

    private void setProfileInfo(){
        database = Room.databaseBuilder(this, FitnessDatabase.class,"fitness_db")
                .allowMainThreadQueries()
                .build();

        userDaoObject = database.userDao();
        user = userDaoObject.getInfo(LoginPage.UserText);

        String firstNameProfileText = user.getFirstName();
        String lastNameProfileText = user.getLastName();
        String ageProfileText = user.getAge();
        String heightProfileText = user.getHeight();
        String weightProfileText = user.getWeight();

        nameTextView.setText(firstNameProfileText + " " + lastNameProfileText);
        ageTextView.setText(ageProfileText + " years old");
        heightTextView.setText(heightProfileText + " cm");
        weightTextView.setText(weightProfileText + " lbs");
        distanceTextView.setText(String.valueOf(String.format("%.2f", HomePage.kiloMeter)+ " km"));
    }
}
