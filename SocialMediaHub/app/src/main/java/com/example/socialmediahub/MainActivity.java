package com.example.socialmediahub;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    Button btnInstagram, btnWhatsapp, btnX, btnYoutube, btnTelegram;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnInstagram = findViewById(R.id.btnInstagram);
        btnWhatsapp = findViewById(R.id.btnWhatsapp);
        btnX = findViewById(R.id.btnX);
        btnYoutube = findViewById(R.id.btnYoutube);
        btnTelegram = findViewById(R.id.btnTelegram);

        btnInstagram.setOnClickListener(v -> openApp("com.instagram.android"));
        btnWhatsapp.setOnClickListener(v -> openApp("com.whatsapp"));
        btnX.setOnClickListener(v -> openApp("com.twitter.android"));
        btnYoutube.setOnClickListener(v -> openApp("com.google.android.youtube"));
        btnTelegram.setOnClickListener(v -> openApp("org.telegram.messenger"));
    }

    private void openApp(String packageName) {
        PackageManager pm = getPackageManager();
        Intent intent = pm.getLaunchIntentForPackage(packageName);

        if (intent != null) {
            startActivity(intent);
        } else {
            Toast.makeText(this, "App not installed", Toast.LENGTH_SHORT).show();
            startActivity(new Intent(Intent.ACTION_VIEW,
                    Uri.parse("https://play.google.com/store/apps/details?id=" + packageName)));
        }
    }
}
