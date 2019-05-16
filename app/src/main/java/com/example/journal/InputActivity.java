package com.example.journal;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class InputActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_input);

        Button confirmButton = findViewById(R.id.confirmbutton);
        ButtonOnClickListener a = new ButtonOnClickListener();
        confirmButton.setOnClickListener(a);
    }

    public class ButtonOnClickListener implements View.OnClickListener {
        @Override
        public void onClick(View v) {
            TextView tv = findViewById(R.id.editText);
            String title = tv.getText().toString();

            TextView cv = findViewById(R.id.editText2);
            String content = cv.getText().toString();

            TextView mv = findViewById(R.id.editText3);
            String mood = mv.getText().toString();

            JournalEntry addEntry = new JournalEntry(title, content, mood);

            EntryDatabase db = EntryDatabase.getInstance(getApplicationContext());

            db.insert(addEntry);

            Intent intentInput = new Intent(InputActivity.this, MainActivity.class);
            startActivity(intentInput);
        }
    }

}
