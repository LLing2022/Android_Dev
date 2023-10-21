package com.example.carmanagementapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

import model.Car;

public class MainActivity extends AppCompatActivity implements View.OnClickListener, ValueEventListener {

    EditText edId, edPrice;
    RadioGroup rgBrand;
    RadioButton rbToyota, rbMazda, rbHyundai;
    CheckBox ckStatus;
    Button btnAdd, btnUpdate, btnRemove, btnFind, btnList;

    DatabaseReference carDatabase;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initialize();
    }
    public void  initialize(){
        edId=findViewById(R.id.edId);
        edPrice=findViewById(R.id.edPrice);

        rgBrand=findViewById(R.id.rgBrand);

        rbToyota=findViewById(R.id.rbToyota);
        rbMazda=findViewById(R.id.rbMazda);
        rbHyundai=findViewById(R.id.rbHyundai);
        rbToyota.setOnClickListener(this);
        rbMazda.setOnClickListener(this);
        rbHyundai.setOnClickListener(this);

        ckStatus=findViewById(R.id.ckStatus);
        ckStatus.setOnClickListener(this);

        btnAdd=findViewById(R.id.btnAdd);
        btnUpdate=findViewById(R.id.btnUpdate);
        btnRemove=findViewById(R.id.btnRemove);
        btnFind=findViewById(R.id.btnFind);
        btnList=findViewById(R.id.btnList);
        btnAdd.setOnClickListener(this);
        btnUpdate.setOnClickListener(this);
        btnRemove.setOnClickListener(this);
        btnFind.setOnClickListener(this);
        btnList.setOnClickListener(this);
        carDatabase = FirebaseDatabase.getInstance().getReference("Car");
    }

    @Override
    public void onClick(View view) {
        int id = view.getId();
        if(id == R.id.btnAdd) addUpdateCar(view, " has been added successfully.");
        if(id == R.id.btnUpdate) addUpdateCar(view, " has been updated successfully.");
        if(id == R.id.btnRemove) removeCar(view);
        if(id == R.id.btnFind) findCar(view);
        if(id == R.id.btnList) listCars(view);
    }

    private void addUpdateCar(View view, String s) {
        try {
            //get id
            String id = edId.getText().toString();
            //get brand
            String brand = "";
            if (rbToyota.isChecked()) {
                brand = rbToyota.getText().toString();
            } else if (rbMazda.isChecked()) {
                brand = rbMazda.getText().toString();
            } else if (rbHyundai.isChecked()) {
                brand = rbHyundai.getText().toString();
            } else {
                Toast.makeText(this, "Please choose a BRAND", Toast.LENGTH_LONG).show();
            }
            //get status
            String status = "";
            if(ckStatus.isChecked()) { status = "New";}
            else {
                status = "Used";
            }
            //get price
            String price = edPrice.getText().toString();

            Car car = new Car(Integer.valueOf(id), brand, status, Integer.valueOf(price));
            carDatabase.child(id).setValue(car);
            Snackbar.make(view, "Car with the id :" + id + s, Snackbar.LENGTH_LONG).show();

            clearWidgets();
        } catch (Exception e) {
            Toast.makeText(this, e.getMessage(), Toast.LENGTH_LONG).show();
        }
    }

    private void clearWidgets() {
        edId.setText(null);
        rgBrand.clearCheck();
        ckStatus.setChecked(false);
        edPrice.setText(null);
        edId.requestFocus();
    }
    private void removeCar(View view) {
        String id = edId.getText().toString();
        carDatabase.child(id).removeValue();
        Snackbar.make(view, "The car with the id " + id + " has been removed successfully.",
                Snackbar.LENGTH_LONG).show();
    }
    private void findCar(View view) {
        String id = edId.getText().toString();
        carDatabase.child(id).addValueEventListener(this); // will trigger Method DataChange
    }

    private void listCars(View view) {
        Intent intent = new Intent(MainActivity.this, SecondActivity.class);
        startActivity(intent);
    }


    @Override
    public void onDataChange(@NonNull DataSnapshot snapshot) {
        if(snapshot.exists()){
            String brand = snapshot.child("brand").getValue().toString();
            if(brand.equals("Toyota")){
                rbToyota.setChecked(true);
            } else if (brand.equals("Mazda")) {
                rbMazda.setChecked(true);
            } else if (brand.equals("Hyundai")) {
                rbHyundai.setChecked(true);
            }
            String status = snapshot.child("status").getValue().toString();
            if(status.equals("New")){
                ckStatus.setChecked(true);
            }else {
                ckStatus.setChecked(false);
            }
            String price = snapshot.child("price").getValue().toString();
            edPrice.setText(price);


        }else {
            String id = edId.getText().toString();
            Toast.makeText(this, "No Car with given ID :" + id, Toast.LENGTH_LONG).show();
            clearWidgets();
        }

    }

    @Override
    public void onCancelled(@NonNull DatabaseError error) {

    }
}