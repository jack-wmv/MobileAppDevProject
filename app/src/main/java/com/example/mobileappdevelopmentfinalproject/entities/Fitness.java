package com.example.mobileappdevelopmentfinalproject.entities;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.io.Serializable;

@Entity(tableName = "Fitness")
public class Fitness implements Serializable {

    @PrimaryKey(autoGenerate = true)
    private int fitnessID;

    @ColumnInfo(name = "Steps")
    private int steps;

    @ColumnInfo(name = "Distance")
    private double distance;

    public int getFitnessID() { return fitnessID;}

    public void setFitnessID(int fitnessID) {this.fitnessID = fitnessID;}

    public int getSteps() {return steps;}

    public void setSteps(int steps) {this.steps =steps;}

    public double getDistance() {return distance;}

    public void setDistance(double distance) { this.distance = distance;}
}
