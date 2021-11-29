package com.example.mobileappdevelopmentfinalproject.entities;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.io.Serializable;

@Entity(tableName = "User")
public class User implements Serializable {


    @PrimaryKey(autoGenerate = true)
    private int user_id;

    @ColumnInfo(name = "Username")
    private String username;

    @ColumnInfo(name = "FirstName")
    private String firstName;

    @ColumnInfo(name = "LastName")
    private String lastName;

    @ColumnInfo(name = "Age")
    private String age;

    @ColumnInfo(name = "Height")
    private String height;

    @ColumnInfo(name = "Weight")
    private String weight;

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public String getHeight() {
        return height;
    }

    public void setHeight(String height) {
        this.height = height;
    }

    public String getWeight() {
        return weight;
    }

    public void setWeight(String weight) {
        this.weight = weight;
    }




    public int getUser_id() { return user_id; }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }


    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }




}
