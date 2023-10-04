package com.example.conversionapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class SecondActivity extends AppCompatActivity implements View.OnClickListener {
    EditText edTemperatureInC, edTemperatureInF, edAuthor;

    Button btnConvert, btnReturn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        initialize();
    }

    private void initialize() {
        edTemperatureInC = findViewById(R.id.edTemperatureInC);
        edTemperatureInF = findViewById(R.id.edTemperatureInF);
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

        if(edTemperatureInF.getText().toString().isEmpty() && edTemperatureInC.getText().toString().isEmpty() && btnId == R.id.btnConvert){
            Toast.makeText(this, "Please Enter A Temperature to Be Convert.", Toast.LENGTH_SHORT).show();
        }else if(edTemperatureInF.getText().toString().isEmpty() && btnId == R.id.btnConvert){
            if(checkInput(edTemperatureInC)){
                String tempInCString = edTemperatureInC.getText().toString();
                double tempInC = Double.parseDouble(tempInCString);
                double resultCtoF = Math.round ( (tempInC * 9/5) + 32);
                String finalResultF =  Double.toString(resultCtoF);
                edTemperatureInF.setText(finalResultF);
                /*
                 * How to let user continue to convert without click return ?????
                 */
                //edTemperatureInC.setText(null);
            }else {
                Toast.makeText(this, "Please Enter A Valid Number of Temperature.", Toast.LENGTH_SHORT).show();
            }
        }else if(edTemperatureInC.getText().toString().isEmpty() && btnId == R.id.btnConvert){
            if(checkInput(edTemperatureInF)){
                String tempInFString = edTemperatureInF.getText().toString();
                double tempInF = Double.parseDouble(tempInFString);
                double resultFtoC = Math.round ((tempInF - 32) * 5/9);
                String finalResultC = Double.toString(resultFtoC);
                edTemperatureInC.setText(finalResultC);
            }else {
                Toast.makeText(this, "Please Enter A Valid Number of Temperature.", Toast.LENGTH_SHORT).show();
            }

        } else if (btnId == R.id.btnReturn) { BackToMain();}

    }

    private void BackToMain() {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
        finish();
    }
}