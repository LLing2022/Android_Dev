package com.example.pizzaorderapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;

import model.Orders;

public class SecondActivity extends AppCompatActivity implements View.OnClickListener {

    TextView tvOrder, tvTotalOrder;

    Button btnFinish;

    ArrayList<Orders> orderList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        initialize();
    }

    private void initialize() {

        tvOrder = findViewById(R.id.tvOrder);
        tvTotalOrder = findViewById(R.id.tvTotalOrder);
        btnFinish = findViewById(R.id.btnFinish);
        btnFinish.setOnClickListener(this);
        orderList = (ArrayList<Orders>) getIntent().getExtras().getSerializable("list");
        StringBuilder sbList = new StringBuilder("");
        float total = 0f;
        for(Orders oneOrder : orderList){
            sbList.append(oneOrder + "\n");
            float amount = Orders.getAmount(oneOrder.getPizzaType(), oneOrder.getNbSlices());
            total = total + amount;

        }
        tvOrder.setText(sbList);
        tvTotalOrder.append("" + total);

    }

    @Override
    public void onClick(View view) {
        int btnid = view.getId();
        if(btnid == R.id.btnFinish){
            GoBackToMain();
        }
    }

    private void GoBackToMain() {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
}