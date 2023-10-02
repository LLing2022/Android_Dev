package com.example.android_lab1_calculator;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    EditText edNumber1, edNumber2, edResult;
    Button btnCalculate, btnClear, btnQuit;
    RadioButton rbtnAdd, rbtnMultiply, rbtnSubstract;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initialize();
    }

    private void initialize() {
        edNumber1 = findViewById(R.id.edNumber1);
        edNumber2 = findViewById(R.id.edNumber2);
        edResult = findViewById(R.id.edResult);
        rbtnAdd = findViewById(R.id.rbtnAdd);
        rbtnMultiply = findViewById(R.id.rbtnMultiply);
        rbtnSubstract = findViewById(R.id.rbtnSubtract);
    }
    public void quit(View view) {System.exit(0);}
    public boolean checkInput(EditText input){
        try{
            String userInput = input.getText().toString();
            Double.parseDouble(userInput);
            return true;
        }catch (Exception e){
            return false;
        }
    }
//    public void Add(EditText edNumber1, EditText edNumber2){
//
//        String num1 = edNumber1.getText().toString();
//        String num2 = edNumber2.getText().toString();
//        try{
//            if(checkInput(num1) && checkInput(num2)){
//                double result = Double.parseDouble(num1) + Double.parseDouble(num2);
//                edResult = findViewById(R.id.edResult);
//                String finalResult = Double.toString(result);
//                edResult.setText(finalResult);
//            }
//        }catch (Exception e){
//            Toast.makeText(this, e.getMessage(), Toast.LENGTH_LONG).show();
//        }
//    }
        public void Add(EditText edNumber1, EditText edNumber2, EditText edResult) {
            try {
                if (checkInput(edNumber1) && checkInput(edNumber2) && rbtnAdd.isChecked()) {
                    String num1 = edNumber1.getText().toString();
                    String num2 = edNumber2.getText().toString();
                    double result = Double.parseDouble(num1) + Double.parseDouble(num2);
                    String finalResult = Double.toString(result);
                    edResult.setText(finalResult);
                }
            } catch (Exception e) {
                Toast.makeText(this, "Invalid input: Please enter valid numbers.", Toast.LENGTH_LONG).show();
            }
        }


    public void Multiply(EditText edNumber1, EditText edNumber2){
        try{
            if(checkInput(edNumber1) && checkInput(edNumber2) && rbtnMultiply.isChecked()){
                String num1 = edNumber1.getText().toString();
                String num2 = edNumber2.getText().toString();
                double result = Double.parseDouble(num1) * Double.parseDouble(num2);
                String finalResult = Double.toString(result);
                edResult.setText(finalResult);
            }
        }catch (Exception e){
            Toast.makeText(this, e.getMessage(), Toast.LENGTH_LONG).show();
        }
    }

    public void Subtract(EditText edNumber1, EditText edNumber2){
        try{
            if(checkInput(edNumber1) && checkInput(edNumber2) && rbtnSubstract.isChecked()){
                String num1 = edNumber1.getText().toString();
                String num2 = edNumber2.getText().toString();
                double result = Double.parseDouble(num1) - Double.parseDouble(num2);
                edResult = findViewById(R.id.edResult);
                String finalResult = Double.toString(result);
                edResult.setText(finalResult);
            }
        }catch (Exception e){
            Toast.makeText(this, e.getMessage(), Toast.LENGTH_LONG).show();
        }
    }


    public void process(View view){
        int btnID = view.getId();
        try{
            if(rbtnAdd.isChecked()){
                this.Add(edNumber1,edNumber2, edResult);
            }
            if (rbtnMultiply.isChecked()) {
                this.Multiply(edNumber1,edNumber2);
            }
            if (rbtnSubstract.isChecked()) {
                this.Subtract(edNumber1,edNumber2);
            }
            if (btnID == R.id.btnClear) {
                edNumber1.getText().clear();
                edNumber2.getText().clear();
                edResult.getText().clear();
                rbtnAdd.setChecked(false);
                rbtnMultiply.setChecked(false);
                rbtnSubstract.setChecked(false);
            }
            if (btnID == R.id.btnQuit) {
                System.exit(0);
            }
        }catch (Exception e){
            Toast.makeText(this, e.getMessage(), Toast.LENGTH_LONG).show();
        }

    }

}