package com.example.socialhub; // Check your package name

import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    Button btnInsta, btnWhatsapp, btnX, btnYoutube, btnTelegram;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnInsta = findViewById(R.id.btnInsta);
        btnWhatsapp = findViewById(R.id.btnWhatsapp);
        btnX = findViewById(R.id.btnX);
        btnYoutube = findViewById(R.id.btnYoutube);
        btnTelegram = findViewById(R.id.btnTelegram);

        // 1. Instagram
        btnInsta.setOnClickListener(v -> openApp("com.instagram.android", "https://www.instagram.com"));

        // 2. WhatsApp
        btnWhatsapp.setOnClickListener(v -> openApp("com.whatsapp", "https://web.whatsapp.com"));

        // 3. X (Twitter)
        btnX.setOnClickListener(v -> openApp("com.twitter.android", "https://twitter.com"));

        // 4. YouTube
        btnYoutube.setOnClickListener(v -> openApp("com.google.android.youtube", "https://www.youtube.com"));

        // 5. Telegram
        btnTelegram.setOnClickListener(v -> openApp("org.telegram.messenger", "https://web.telegram.org"));
    }

    // Helper Method to Open App or Browser
    private void openApp(String packageName, String browserUrl) {
        try {
            // Try to launch the app directly
            Intent intent = getPackageManager().getLaunchIntentForPackage(packageName);
            if (intent != null) {
                startActivity(intent);
            } else {
                // App not found, throw exception to trigger catch block
                throw new PackageManager.NameNotFoundException();
            }
        } catch (PackageManager.NameNotFoundException e) {
            // Fallback: Open in Browser
            Toast.makeText(this, "App not installed, opening Browser...", Toast.LENGTH_SHORT).show();
            Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(browserUrl));
            startActivity(browserIntent);
        }
    }
}