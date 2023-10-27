package com.example.menuapp_lab4;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import Model.Schedule;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

   // Button btnTest;

    TextView[] tvList;
    int widgets[] = {R.id.tvbMotu, R.id.tvbWe, R.id.tvbThfr, R.id.tvlMo, R.id.tvlTu,
            R.id.tvlWe, R.id.tvlTh, R.id.tvlFr, R.id.tvsMo, R.id.tvsTuwe, R.id.tvsThfr};
    Schedule[] scheduleList;
    // some other declarations
    ActivityResultLauncher<Intent> actLauncher;
    TextView clickedTv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initialize();
    }

    private void initialize() {
//        btnTest = findViewById(R.id.btnTest);
//        btnTest.setOnClickListener(this);

        tvList = new TextView[widgets.length];
        scheduleList = new Schedule[widgets.length];
        scheduleList[0] = new Schedule(1, "Milk", Color.BLUE, Color.YELLOW);
        scheduleList[1] = new Schedule(2, "Apple");
        scheduleList[2] = new Schedule(3, "Banana", Color.BLACK, Color.YELLOW);
        scheduleList[3] = new Schedule(4, "Peach", Color.RED, Color.GRAY);
        scheduleList[4] = new Schedule(5, "Rice", Color.BLACK, Color.BLUE);
        scheduleList[5] = new Schedule(6, "Noodle", Color.GREEN, Color.YELLOW);
        scheduleList[6] = new Schedule(7, "Chick", Color.BLUE, Color.GRAY);
        scheduleList[7] = new Schedule(8, "Frits", Color.BLUE, Color.GREEN);
        scheduleList[8] = new Schedule(9, "Pork", Color.WHITE, Color.RED);
        scheduleList[9] = new Schedule(10, "Beef", Color.GREEN, Color.WHITE);
        scheduleList[10] = new Schedule(11, "Nuts", Color.YELLOW, Color.BLUE);
        for (int i = 0; i < widgets.length; i++){
            tvList[i] = findViewById(widgets[i]);
            Schedule sch = scheduleList[i];
            tvList[i].setText(sch.getDescription());
            tvList[i].setTextColor(sch.getTxtColor());
            tvList[i].setBackgroundColor(sch.getBgColor());
            tvList[i].setOnClickListener(this);
        }
        registerActResLauncher();
    }

    private void registerActResLauncher() {
        actLauncher = registerForActivityResult(
                new ActivityResultContracts.StartActivityForResult(),
                new ActivityResultCallback<ActivityResult>() {
                    @Override
                    public void onActivityResult(ActivityResult o) {
                        if(o.getResultCode() == RESULT_OK){
                            String newData = o.getData().getStringExtra("new_schedule");
                            int newColor = o.getData().getIntExtra("new_color",Color.BLACK);
                            int newBgColor = o.getData().getIntExtra("new_BgColor",Color.WHITE);
                            clickedTv.setText(newData);
                            clickedTv.setTextColor(newColor);
                            clickedTv.setBackgroundColor(newBgColor);
                        }else {
                            Toast.makeText(MainActivity.this, "No change made",Toast.LENGTH_SHORT).show();
                        }
                    }
                }
        );
    }


    @Override
    public void onClick(View v) {

        clickedTv = (TextView) v;
        Intent intent = new Intent(this, SecondActivity.class);
        intent.putExtra("schedule", clickedTv.getText().toString());
        actLauncher.launch(intent);

//        int id = v.getId();
//        if (id == R.id.btnTest){
//            System.exit(0);
//        }
    }
}