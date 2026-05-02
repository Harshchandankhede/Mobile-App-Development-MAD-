package com.example.filehandling;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.io.FileInputStream;
import java.io.FileOutputStream;

public class MainActivity extends AppCompatActivity {
    Button b1, b2, b3;
    TextView tv;
    EditText ed1;
    String data;
    private String file = "mydata.txt";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        b1 = (Button) findViewById(R.id.button);
        b2 = (Button) findViewById(R.id.button2);
        b3 = (Button) findViewById(R.id.button3);
        ed1 = (EditText) findViewById(R.id.editText);
        tv = (TextView) findViewById(R.id.textview2);

        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                data = ed1.getText().toString();
                try {
                    FileOutputStream fOut = openFileOutput(file, MODE_APPEND);
                    fOut.write(data.getBytes());
                    fOut.close();
                    Toast.makeText(getBaseContext(), "file saved", Toast.LENGTH_SHORT).show();
                    ed1.setText("");
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });

        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    FileInputStream fin = openFileInput(file);
                    int c;
                    StringBuilder temp = new StringBuilder();
                    while ((c = fin.read()) != -1) {
                        temp.append((char) c);
                    }
                    tv.setText(temp.toString());
                    Toast.makeText(getBaseContext(), "file read", Toast.LENGTH_SHORT).show();
                    fin.close();
                } catch (Exception e) {
                    tv.setText("");
                    Toast.makeText(getBaseContext(), "File is empty", Toast.LENGTH_SHORT).show();
                }
            }
        });

        b3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    // Opening in MODE_PRIVATE without appending will overwrite the file
                    FileOutputStream fOut = openFileOutput(file, MODE_PRIVATE);
                    fOut.write("".getBytes());
                    fOut.close();
                    tv.setText("");
                    Toast.makeText(getBaseContext(), "File cleared", Toast.LENGTH_SHORT).show();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }
}