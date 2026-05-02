package com.example.layoutshowcase; // Check your actual package name

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // 1. Load default fragment (Linear) on start
        if (savedInstanceState == null) {
            loadFragment(new LinearFragment());
        }

        // 2. Set Click Listeners for buttons
        findViewById(R.id.btnLinear).setOnClickListener(v -> loadFragment(new LinearFragment()));
        findViewById(R.id.btnRelative).setOnClickListener(v -> loadFragment(new RelativeFragment()));
        findViewById(R.id.btnTable).setOnClickListener(v -> loadFragment(new TableFragment()));
        findViewById(R.id.btnFrame).setOnClickListener(v -> loadFragment(new FrameFragment()));
    }

    // Helper method to replace fragments
    private void loadFragment(Fragment fragment) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

        // Replace the content of the container with the new fragment
        fragmentTransaction.replace(R.id.fragment_container, fragment);
        fragmentTransaction.commit();
    }
}