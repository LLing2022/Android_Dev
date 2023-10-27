package com.example.menuapp_lab4;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;

public class SecondActivity extends AppCompatActivity implements View.OnClickListener {
    EditText edDes;
    RadioGroup rgColor, rgbgColor;
    RadioButton rbcRed, rbcGreen, rbcMagenta, rbYellow, rbWhite;
    Button btnReturn;
    String data;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        initialize();
    }

    private void initialize() {
        edDes = findViewById(R.id.edDes);
        rgbgColor = findViewById(R.id.rgbgColor);
        rgColor = findViewById(R.id.rgColor);
        rbcRed = findViewById(R.id.rbcRed);
        rbcGreen = findViewById(R.id.rbcGreen);
        rbcMagenta = findViewById(R.id.rbcMagenta);
        rbYellow = findViewById(R.id.rbYellow);
        rbWhite = findViewById(R.id.rbWhite);
        btnReturn = findViewById(R.id.btnReturn);
        btnReturn.setOnClickListener(this);
        data = getIntent().getStringExtra("schedule");
        edDes.setText(data);
    }

    @Override
    public void onClick(View v) {

        String newData = edDes.getText().toString();
        int newColorId = rgColor.getCheckedRadioButtonId();
        int newBgColorId= rgbgColor.getCheckedRadioButtonId();
        int newColor = 0, newBgColor = 0;
        if (newColorId == R.id.rbcRed) {
            newColor = Color.RED;
        }
        else if (newColorId == R.id.rbcGreen) {
            newColor = Color.GREEN;
        }
        else if (newColorId == R.id.rbcMagenta) {
            newColor = Color.MAGENTA;
        }

        if (newBgColorId == R.id.rbYellow) {
            newBgColor = Color.YELLOW;
        }
        else if (newBgColorId == R.id.rbWhite) {
            newBgColor = Color.WHITE;
        }

        Intent intent = new Intent();
        if(data.equalsIgnoreCase(newData)){
            setResult(RESULT_CANCELED,intent);
        }
        else {
            intent.putExtra("new_schedule",newData);
            intent.putExtra("new_color",newColor);
            intent.putExtra("new_BgColor",newBgColor);
            setResult(RESULT_OK,intent);
        }
        finish();
    }
}