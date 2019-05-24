package com.example.journal;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.widget.TextView;

public class DetailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        Intent intent = getIntent();

        String title = intent.getStringExtra("title");

        String mood = intent.getStringExtra("mood");

        String content = intent.getStringExtra("content");

        String date = intent.getStringExtra("date");

        TextView titleView = findViewById(R.id.textTitle);
        TextView dateView = findViewById(R.id.textDate);
        TextView moodView = findViewById(R.id.textMood);
        TextView contentView = findViewById(R.id.textContent);

        titleView.setText(title);
        contentView.setText(content);
        moodView.setText(mood);
        dateView.setText(date);
    }
}
