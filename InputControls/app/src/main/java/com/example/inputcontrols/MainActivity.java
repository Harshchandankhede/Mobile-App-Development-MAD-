package com.example.inputcontrols;

import android.os.Bundle;
import android.widget.*;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    private int progressStatus = 0; // Global variable to track progress

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // 1. Spinner Setup
        Spinner spinner = findViewById(R.id.spinner);
        String[] items = {"Select Color", "Red", "Green", "Blue"};
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, items);
        spinner.setAdapter(adapter);

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, android.view.View view, int position, long id) {
                if(position > 0) {
                    Toast.makeText(MainActivity.this, "Selected: " + items[position], Toast.LENGTH_SHORT).show();
                }
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {}
        });

        // 2. ImageButton
        ImageButton imageButton = findViewById(R.id.imageButton);
        imageButton.setOnClickListener(v ->
                Toast.makeText(this, "Image Button Clicked!", Toast.LENGTH_SHORT).show());

        // 3. CheckBox
        CheckBox checkBox = findViewById(R.id.checkBox);
        checkBox.setOnCheckedChangeListener((buttonView, isChecked) ->
                Toast.makeText(this, "Terms Accepted: " + isChecked, Toast.LENGTH_SHORT).show());

        // 4. RadioGroup
        RadioGroup radioGroup = findViewById(R.id.radioGroup);
        radioGroup.setOnCheckedChangeListener((group, checkedId) -> {
            RadioButton rb = findViewById(checkedId);
            Toast.makeText(this, "Selected: " + rb.getText(), Toast.LENGTH_SHORT).show();
        });

        // 5. ToggleButton
        ToggleButton toggleButton = findViewById(R.id.toggleButton);
        toggleButton.setOnCheckedChangeListener((buttonView, isChecked) ->
                Toast.makeText(this, isChecked ? "Toggle is ON" : "Toggle is OFF", Toast.LENGTH_SHORT).show());

        // 6. RatingBar
        RatingBar ratingBar = findViewById(R.id.ratingBar);
        ratingBar.setOnRatingBarChangeListener((rb, rating, fromUser) ->
                Toast.makeText(this, "Rating: " + rating + "/5", Toast.LENGTH_SHORT).show());

        // 7. ProgressBar Logic
        ProgressBar progressBar = findViewById(R.id.progressBar);
        Button btnUpdateProgress = findViewById(R.id.btnUpdateProgress);

        btnUpdateProgress.setOnClickListener(v -> {
            if (progressStatus < 100) {
                progressStatus += 10;
                progressBar.setProgress(progressStatus);
            } else {
                Toast.makeText(this, "Progress Finished! Resetting...", Toast.LENGTH_SHORT).show();
                progressStatus = 0;
                progressBar.setProgress(progressStatus);
            }
        });

        // 8. Alert Dialog
        Button btnAlert = findViewById(R.id.btnAlert);
        btnAlert.setOnClickListener(v -> {
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setTitle("Input Controls App")
                    .setMessage("This app demonstrates various Android UI widgets. Do you want to exit?")
                    .setCancelable(false)
                    .setPositiveButton("Yes", (dialog, which) -> finish())
                    .setNegativeButton("No", (dialog, which) -> dialog.dismiss())
                    .show();
        });
    }
}