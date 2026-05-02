package com.example.databaseconnect;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.widget.*;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    EditText etName, etEmail, etMobile, etCity, etAge;
    Button btnSave;
    SQLiteDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etName = findViewById(R.id.etName);
        etEmail = findViewById(R.id.etEmail);
        etMobile = findViewById(R.id.etMobile);
        etCity = findViewById(R.id.etCity);
        etAge = findViewById(R.id.etAge);
        btnSave = findViewById(R.id.btnSave);

        DBHelper dbHelper = new DBHelper(this);
        db = dbHelper.getWritableDatabase();

        btnSave.setOnClickListener(v -> {

            String name = etName.getText().toString();
            String email = etEmail.getText().toString();
            String mobile = etMobile.getText().toString();
            String city = etCity.getText().toString();
            String age = etAge.getText().toString();

            if(name.isEmpty() || email.isEmpty() || mobile.isEmpty() || city.isEmpty() || age.isEmpty()){
                Toast.makeText(this, "Fill all fields", Toast.LENGTH_SHORT).show();
                return;
            }

            ContentValues cv = new ContentValues();
            cv.put("name", name);
            cv.put("email", email);
            cv.put("mobile", mobile);
            cv.put("city", city);
            cv.put("age", age);

            long result = db.insert("users", null, cv);

            if(result != -1){
                Toast.makeText(this, "Data Saved Successfully", Toast.LENGTH_SHORT).show();

                etName.setText("");
                etEmail.setText("");
                etMobile.setText("");
                etCity.setText("");
                etAge.setText("");

            } else {
                Toast.makeText(this, "Error saving data", Toast.LENGTH_SHORT).show();
            }
        });
    }
}