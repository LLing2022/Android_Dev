package com.example.conversionapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    EditText edName;
    Button btnTemperature, btnMetrix, btnQuit;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initialize();
    }

    private void initialize() {
        edName = findViewById(R.id.edName);
        btnTemperature = findViewById(R.id.btnTemperature);
        btnMetrix = findViewById(R.id.btnMetrix);
        btnQuit = findViewById(R.id.btnQuit);
        btnTemperature.setOnClickListener(this);
        btnMetrix.setOnClickListener(this);
        btnQuit.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        int id = view.getId();
        if(id == R.id.btnQuit){
            finishAffinity();
        }else if (id == R.id.btnTemperature || id == R.id.btnMetrix){
            if(!edName.getText().toString().isEmpty()){
                if(id == R.id.btnTemperature){
                    GoToSecondActivity();
                }else if(id == R.id.btnMetrix) {
                    GoToThirdActivity();
                }
            }else{
                Toast.makeText(this,"Please Enter The Developer's Name.", Toast.LENGTH_SHORT).show();
            }
        }

    }
    private void GoToSecondActivity() {
        String name = edName.getText().toString();
        Intent intent = new Intent(this, SecondActivity.class);
        intent.putExtra("name", name);
        startActivity(intent);
    }
    private void GoToThirdActivity() {
        String name = edName.getText().toString();
        Intent intent = new Intent(this, ThirdActivity.class);
        intent.putExtra("name", name);
        startActivity(intent);
    }


}