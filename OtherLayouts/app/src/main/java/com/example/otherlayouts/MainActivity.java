package com.example.otherlayouts;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button btnList = findViewById(R.id.btn_list);
        Button btnGrid = findViewById(R.id.btn_grid);
        Button btnAbs = findViewById(R.id.btn_absolute);
        Button btnConst = findViewById(R.id.btn_constraint);

        btnList.setOnClickListener(v -> startActivity(new Intent(MainActivity.this, ListViewActivity.class)));
        btnGrid.setOnClickListener(v -> startActivity(new Intent(MainActivity.this, GridActivity.class)));
        btnAbs.setOnClickListener(v -> startActivity(new Intent(MainActivity.this, AbsoluteActivity.class)));
        btnConst.setOnClickListener(v -> startActivity(new Intent(MainActivity.this, ConstraintActivity.class)));
    }
}