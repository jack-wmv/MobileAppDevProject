package com.example.mobileappdevelopmentfinalproject.Activities;

import android.content.Intent;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.WindowManager;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.mobileappdevelopmentfinalproject.R;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.text.DecimalFormat;

public class HomePage extends AppCompatActivity implements SensorEventListener {
    private TextView textViewStepCounter, kM, cal;
    private SensorManager sensorManager;
    private Sensor mStepCounter;
    static int stepCount = 0;
    static double kiloMeter = 0;
    double calories = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.homepage);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
        textViewStepCounter = findViewById(R.id.stepCount);
        kM = findViewById(R.id.disText);
        cal = findViewById(R.id.calNum);
        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);

        sensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);
        navigation.setSelectedItemId(R.id.homeItem);
        boolean isCounterSensorPresent;
        if(sensorManager.getDefaultSensor(Sensor.TYPE_STEP_COUNTER) !=null)
        {
            mStepCounter = sensorManager.getDefaultSensor(Sensor.TYPE_STEP_COUNTER);
            isCounterSensorPresent = true;

        } else {
            textViewStepCounter.setText("Counter Sensor is not present");
            isCounterSensorPresent = false;
        }


        navigation.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.homeItem:
                        return true;
                    case R.id.mapsItem:
                        Intent a = new Intent(HomePage.this, Maps.class);
                        startActivity(a);
                        return true;
                    case R.id.profileItem:
                        Intent b = new Intent(HomePage.this, Profile.class);
                        startActivity(b);
                        return true;
                    case R.id.settingsItem:
                        Intent c = new Intent(HomePage.this, Settings.class);
                        startActivity(c);
                        return true;
                }
                return true;
            }
        });


    }
    @Override
    protected void onPause() {
        super.onPause();
        if(sensorManager.getDefaultSensor(Sensor.TYPE_STEP_COUNTER) != null)
            sensorManager.unregisterListener(this,mStepCounter);
    }

    @Override
    protected void onResume() {
        super.onResume();
        if(sensorManager.getDefaultSensor(Sensor.TYPE_STEP_COUNTER) != null)
            sensorManager.registerListener(this,mStepCounter, SensorManager.SENSOR_DELAY_NORMAL);
    }

    @Override
    public void onSensorChanged(SensorEvent sensorEvent) {
        if(sensorEvent.sensor == mStepCounter){
            stepCount = (int) sensorEvent.values[0];
            textViewStepCounter.setText(String.valueOf(stepCount));
            kiloMeter =  stepCount/1312.3359;
            kM.setText(String.format("%.2f", kiloMeter));
            calories = stepCount * 0.03;
            cal.setText(String.format("%.2f", calories));
        }
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int i) {

    }

}
