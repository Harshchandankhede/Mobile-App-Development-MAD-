package com.example.goalcount;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.button.MaterialButton;
import com.google.android.material.dialog.MaterialAlertDialogBuilder;
import com.google.android.material.progressindicator.LinearProgressIndicator;

import java.util.Locale;
import java.util.concurrent.Executors;

public class MainActivity extends AppCompatActivity {

    private AppDatabase db;
    private TextView tvTotalCount, tvProgressLabel;
    private LinearProgressIndicator progressBar;
    private EditText etCount;
    private CountAdapter adapter;
    private static final int GOAL = 11111;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        // Handle edge-to-edge padding
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        db = AppDatabase.getInstance(this);
        
        tvTotalCount = findViewById(R.id.tvTotalCount);
        tvProgressLabel = findViewById(R.id.tvProgressLabel);
        progressBar = findViewById(R.id.progressBar);
        etCount = findViewById(R.id.etCount);
        MaterialButton btnAdd = findViewById(R.id.btnAdd);
        RecyclerView rvLog = findViewById(R.id.rvLog);

        adapter = new CountAdapter(entry -> {
            new MaterialAlertDialogBuilder(this)
                    .setTitle(R.string.delete_dialog_title)
                    .setMessage(R.string.delete_dialog_msg)
                    .setPositiveButton(R.string.delete, (dialog, which) -> {
                        Executors.newSingleThreadExecutor().execute(() -> db.countDao().delete(entry));
                    })
                    .setNegativeButton(R.string.cancel, null)
                    .show();
        });

        rvLog.setLayoutManager(new LinearLayoutManager(this));
        rvLog.setAdapter(adapter);

        db.countDao().getAllEntries().observe(this, entries -> {
            adapter.submitList(entries);
        });

        db.countDao().getTotalCount().observe(this, total -> {
            int count = (total != null) ? total : 0;
            updateUI(count);
        });

        btnAdd.setOnClickListener(v -> {
            String input = etCount.getText().toString();
            if (input.isEmpty()) return;

            try {
                int amount = Integer.parseInt(input);
                if (amount <= 0) {
                    Toast.makeText(this, "Please enter a positive number", Toast.LENGTH_SHORT).show();
                    return;
                }

                Executors.newSingleThreadExecutor().execute(() -> {
                    db.countDao().insert(new CountEntry(amount, System.currentTimeMillis()));
                });
                etCount.setText("");
            } catch (NumberFormatException e) {
                Toast.makeText(this, "Invalid number", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void updateUI(int total) {
        tvTotalCount.setText(String.format(Locale.getDefault(), "%,d", total));
        tvProgressLabel.setText(String.format(Locale.getDefault(), "%,d / %,d", total, GOAL));
        progressBar.setProgress(Math.min(total, GOAL));

        if (total >= GOAL) {
            tvTotalCount.setTextColor(getResources().getColor(R.color.success, getTheme()));
            Toast.makeText(this, R.string.congrats_msg, Toast.LENGTH_LONG).show();
        } else {
            tvTotalCount.setTextColor(getResources().getColor(R.color.primary, getTheme()));
        }
    }
}

