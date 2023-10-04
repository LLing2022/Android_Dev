package com.example.conversionapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class ThirdActivity extends AppCompatActivity implements View.OnClickListener {

    EditText edMeter, edCentimeter, edKilometer, edAuthor;
    Button btnConvert, btnReturn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_third);
        initialize();
    }

    private void initialize() {
        edMeter = findViewById(R.id.edMeter);
        edCentimeter = findViewById(R.id.edCentimeter);
        edKilometer = findViewById(R.id.edKilometer);
        edAuthor = findViewById(R.id.edAuthor);
        btnConvert = findViewById(R.id.btnConvert);
        btnConvert.setOnClickListener(this);
        btnReturn = findViewById(R.id.btnReturn);
        btnReturn.setOnClickListener(this);
        String name = getIntent().getStringExtra("name");
        edAuthor.setText(name);

    }

    public boolean checkInput(EditText input){
        try{
            String userInput = input.getText().toString();
            Double.parseDouble(userInput);
            return true;
        }catch (Exception e){
            return false;
        }
    }
    @Override
    public void onClick(View view) {
        int btnId = view.getId();
        if(edMeter.getText().toString().isEmpty() && edCentimeter.getText().toString().isEmpty() &&
        edKilometer.getText().toString().isEmpty() && btnId == R.id.btnConvert){
            Toast.makeText(this,"Please Enter A Valid Metric To Convert", Toast.LENGTH_SHORT).show();
        } else if (edCentimeter.getText().toString().isEmpty() &&
                edKilometer.getText().toString().isEmpty() && btnId == R.id.btnConvert) {
            if(checkInput(edMeter)){
                double Meter = Double.parseDouble(edMeter.getText().toString());
                String finalResultCentimeter =  Double.toString(Meter * 100);
                String finalResultKilometer = Double.toString(Meter / 1000);
                edCentimeter.setText(finalResultCentimeter);
                edKilometer.setText(finalResultKilometer);
            }else{
                Toast.makeText(this,"Please Enter A Valid Number of Meter", Toast.LENGTH_SHORT).show();
            }
        } else if (edMeter.getText().toString().isEmpty() &&
                edKilometer.getText().toString().isEmpty() && btnId == R.id.btnConvert) {
            if(checkInput(edCentimeter)){
                double Centimeter = Double.parseDouble(edCentimeter.getText().toString());
                String finalResultMeter =  Double.toString(Centimeter / 100);
                String finalResultKilometer = Double.toString(Centimeter / 100000);
                edMeter.setText(finalResultMeter);
                edKilometer.setText(finalResultKilometer);
            }else{
                Toast.makeText(this,"Please Enter A Valid Number of Centimeter", Toast.LENGTH_SHORT).show();
            }
        } else if (edMeter.getText().toString().isEmpty() &&
                edCentimeter.getText().toString().isEmpty() && btnId == R.id.btnConvert) {
            if(checkInput(edKilometer)){
                double Kilometer = Double.parseDouble(edKilometer.getText().toString());
                String finalResultMeter =  Double.toString(Kilometer * 1000);
                String finalResultCentimeter = Double.toString(Kilometer * 100000);
                edMeter.setText(finalResultMeter);
                edCentimeter.setText(finalResultCentimeter);
            }else{
                Toast.makeText(this,"Please Enter A Valid Number of Kilometer", Toast.LENGTH_SHORT).show();
            }
        } else if (btnId == R.id.btnReturn) {
            GoBackToMain();
        }

    }

    private void GoBackToMain() {
        /*
        "Create an intent from this activity (this) to
        launch the MainActivity class." It's essentially preparing
        an instruction to navigate from the current activity
        to the MainActivity when the intent is executed,
        typically by calling startActivity(intent).
         */
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
        finish();
    }


}