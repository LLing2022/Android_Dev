package com.example.pizzaorderapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import java.util.ArrayList;

import model.Orders;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

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
        if(btnId == R.id.btnQuit) System.exit(0);
        if(btnId == R.id.rbVegen) { pizzaType = rbVegen.getText().toString(); showAmount(); }
        if(btnId == R.id.rbMexico) { pizzaType = rbMexico.getText().toString(); showAmount(); }
        if(btnId == R.id.rbCheese) { pizzaType = rbCheese.getText().toString(); showAmount(); }
        
    }

    private void showAmount() {
        //test for git push 01
    }

    private void showAll() {
    }

    private void saveOrder() {
        
    }
    
    
}
