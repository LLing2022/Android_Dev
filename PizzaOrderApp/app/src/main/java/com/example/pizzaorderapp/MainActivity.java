package com.example.pizzaorderapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import model.Orders;

public class MainActivity extends AppCompatActivity implements View.OnClickListener, TextWatcher {

    private EditText edClNumber, edNbOfSlices;
    private RadioGroup rgPizza;
    private Button btnSave, btnShowAll, btnQuit;
    private RadioButton rbCheese, rbVegen, rbMexico;
    private TextView edAmount;

    private String pizzaType = "";
    private Orders onOrder;
    private float price;
    private ArrayList<Orders> orderList;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initialize();
    }

    private void initialize() {
        edClNumber = findViewById(R.id.edClNumber);
        edNbOfSlices = findViewById(R.id.edNbOfSlices);
        //New method
        edNbOfSlices.addTextChangedListener(this);
        //
        rgPizza = findViewById(R.id.rgPizza);
        edAmount = findViewById(R.id.edAmount);
        btnSave = findViewById(R.id.btnSave);
        btnShowAll = findViewById(R.id.btnShowAll);
        btnQuit = findViewById(R.id.btnQuit);
        rbCheese = findViewById(R.id.rbCheese);
        rbVegen = findViewById(R.id.rbVegen);
        rbMexico = findViewById(R.id.rbMexico);
        
        btnSave.setOnClickListener(this);
        btnShowAll.setOnClickListener(this);
        btnQuit.setOnClickListener(this);
        rbCheese.setOnClickListener(this);
        rbVegen.setOnClickListener(this);
        rbMexico.setOnClickListener(this);

        orderList = new ArrayList<Orders>();
    }


    @Override
    public void onClick(View view) {
        int btnId = view.getId();
        if(btnId == R.id.btnSave) saveOrder();
        if(btnId == R.id.btnShowAll) showAll();
        if(btnId == R.id.btnQuit) finishAffinity();
        if(btnId == R.id.rbVegen) { pizzaType = rbVegen.getText().toString(); showAmount(); }
        if(btnId == R.id.rbMexico) { pizzaType = rbMexico.getText().toString(); showAmount(); }
        if(btnId == R.id.rbCheese) { pizzaType = rbCheese.getText().toString(); showAmount(); }
        
    }


    private void saveOrder() {
        try{
            int clNumber = Integer.valueOf(edClNumber.getText().toString());
            int nbOfSlices = Integer.valueOf(edNbOfSlices.getText().toString());
            Orders oneOrder = new Orders (clNumber, pizzaType, nbOfSlices);
            orderList.add(oneOrder);
            Toast.makeText(this, "One Order has been set succesfully", Toast.LENGTH_SHORT).show();

        }catch (Exception e){
            Toast.makeText(this, e.getMessage(), Toast.LENGTH_SHORT).show();
        }

    }
    private void clearWidgets(){
        edClNumber.setText(null);
        edNbOfSlices.setText(null);
        rgPizza.clearCheck();
        edAmount.setText(null);
        edClNumber.requestFocus();
    }

    private void showAmount() {
        try{
//            float price = Orders.getPrice(pizzaType);
//            int nbOfSlices = Integer.valueOf(edNbOfSlices.getText().toString());
//            float amount = price * nbOfSlices;
            int nbOfSlices = Integer.valueOf(edNbOfSlices.getText().toString());
            float amount = Orders.getAmount(pizzaType, nbOfSlices);
            edAmount.setText("" + amount);
        }catch (Exception e){
            Toast.makeText(this, e.getMessage(),Toast.LENGTH_SHORT).show();
        }
    }

//    private float getPrice(String pizzaType) throws Exception {
//        float price = 0f;
//        if(pizzaType.equals("Vegen")){
//            price = 2.5f;
//        } else if (pizzaType.equals("Cheese")) {
//            price = 2.0f;
//        } else if (pizzaType.equals("Mexico")) {
//            price = 2.4f;
//        }else
//            throw new Exception("Please select Pizza Type");
//        return price;
//    }

    private void showAll() {
        Intent intent = new Intent(this, SecondActivity.class);
        intent.putExtra("list",orderList);
        startActivity(intent);
    }


    @Override
    public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

    }

    @Override
    public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

    }

    @Override
    public void afterTextChanged(Editable editable) {
        showAmount();
    }
}
