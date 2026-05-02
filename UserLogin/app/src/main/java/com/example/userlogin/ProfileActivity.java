package com.example.userlogin;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.provider.MediaStore;
import android.widget.*;
import androidx.appcompat.app.AppCompatActivity;

public class ProfileActivity extends AppCompatActivity {

    private static final int CAMERA_REQUEST = 1888;
    ImageView imgProfile;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        imgProfile = findViewById(R.id.imgProfile);
        TextView tvWelcomeUser = findViewById(R.id.tvWelcomeUser);
        Button btnCapture = findViewById(R.id.btnCapture);

        // Get the username passed from LoginActivity
        String user = getIntent().getStringExtra("USER_NAME");
        tvWelcomeUser.setText("Profile: " + user);

        // Intent to open Camera
        btnCapture.setOnClickListener(v -> {
            Intent cameraIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
            startActivityForResult(cameraIntent, CAMERA_REQUEST);
        });
    }

    // This runs when the Camera app finishes taking the photo
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == CAMERA_REQUEST && resultCode == RESULT_OK) {
            Bitmap photo = (Bitmap) data.getExtras().get("data");
            imgProfile.setImageBitmap(photo);
        }
    }
}