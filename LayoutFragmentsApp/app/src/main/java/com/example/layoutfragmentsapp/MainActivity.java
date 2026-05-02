package com.example.layoutfragmentsapp;

import android.os.Bundle;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button btnLinear = findViewById(R.id.btnLinear);
        Button btnRelative = findViewById(R.id.btnRelative);
        Button btnTable = findViewById(R.id.btnTable);
        Button btnForm = findViewById(R.id.btnForm);

        loadFragment(new LinearFragment());

        btnLinear.setOnClickListener(v -> loadFragment(new LinearFragment()));
        btnRelative.setOnClickListener(v -> loadFragment(new RelativeFragment()));
        btnTable.setOnClickListener(v -> loadFragment(new TableFragment()));
        btnForm.setOnClickListener(v -> loadFragment(new FormFragment()));
    }

    private void loadFragment(Fragment fragment) {
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.fragmentContainer, fragment)
                .commit();
    }
}
