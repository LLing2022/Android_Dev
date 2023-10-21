package com.example.carmanagementapp;


import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

import model.Car;


public class SecondActivity extends AppCompatActivity implements View.OnClickListener, ValueEventListener {
    Button btnFinish;
    TextView tvCarInfo;
    /*
    - FirebaseDatabase.getInstance(): This part of the code is used to get an instance of the Firebase Realtime Database. FirebaseDatabase is a class in the Firebase SDK
    that provides access to the Firebase Realtime Database. getInstance() is a method used to obtain a reference to the Firebase Database instance that your app is configured to use.
    - getReference(): Once you have an instance of the Firebase Database, you can call the getReference() method to get a reference to the root of the database.
     This is the starting point from which you can access and manipulate data in the database.
    - child("cars"): This part of the code is used to specify a child location within the database. In this case, it's specifying a child node called "cars."
     This means that you want to access or manipulate data under the "cars" node in the database. In Firebase Realtime Database, data is structured as a JSON tree, so you can have various nodes and sub-nodes, and this line of code allows you to access the "cars" node.
     */
    DatabaseReference carsReference;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        initialize();
    }

    private void initialize() {
        tvCarInfo = findViewById(R.id.tvCarInfo);
        btnFinish = findViewById(R.id.btnFinish);
        btnFinish.setOnClickListener(this);
        carsReference = FirebaseDatabase.getInstance().getReference().child("Car");
        // Add a listener to retrieve data
        carsReference.addValueEventListener(this);
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        if(id == R.id.btnFinish){
            GoBackToMain();
        }
    }

    private void GoBackToMain() {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

    @Override
    public void onDataChange(@NonNull DataSnapshot snapshot) {
        if (snapshot.exists()) {
            List<Car> carList = new ArrayList<>();
            for (DataSnapshot carSnapshot : snapshot.getChildren()) {
                Car car = carSnapshot.getValue(Car.class);
                if (car != null) {
                    carList.add(car);
                }
            }
            displayCarList(carList);
        }
    }

    private void displayCarList(List<Car> carList) {
        StringBuilder carListString = new StringBuilder();

        for (Car car : carList) {
            carListString.append("").append(car.getId()).append("\t\t");
            carListString.append(",").append(car.getBrand()).append("\t\t");
            carListString.append(",").append(car.getStatus()).append("\t\t");
            carListString.append(",").append(car.getPrice()).append("\t\t\n");
        }

        // Display the car list in a TextView
        tvCarInfo.setText(carListString.toString());
    }

    @Override
    public void onCancelled(@NonNull DatabaseError error) {

    }
}