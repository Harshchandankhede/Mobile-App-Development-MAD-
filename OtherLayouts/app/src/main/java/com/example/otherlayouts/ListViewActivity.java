package com.example.otherlayouts;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import androidx.appcompat.app.AppCompatActivity;

public class ListViewActivity extends AppCompatActivity {

    // Data for the list
    String[] courses = {
            "Android Development",
            "Java Programming",
            "Python",
            "Data Structures",
            "Web Design",
            "Machine Learning",
            "Deep Learning",
            "Algorithms",
            "Operating Systems",
            "Computer Networks",
            "Database Management System",
            "Software Engineering",
            "Artificial Intelligence",
            "Cloud Computing",
            "Cyber Security",
            "Blockchain Technology",
            "Internet of Things (IoT)",
            "Mobile App Development",
            "Big Data Analytics",
            "DevOps",
            "Data Science",
            "Full Stack Development",
            "UI/UX Design",
            "Compiler Design",
            "Digital Electronics",
            "Computer Architecture",
            "Ethical Hacking",
            "Natural Language Processing"
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_view);

        ListView listView = findViewById(R.id.simpleListView);

        // Adapter: connects the array data to the list view
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, courses);
        listView.setAdapter(adapter);
    }
}