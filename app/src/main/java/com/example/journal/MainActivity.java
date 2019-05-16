package com.example.journal;

import android.app.AlertDialog;
import android.content.Intent;
import android.database.Cursor;
import android.content.DialogInterface;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

public class MainActivity extends AppCompatActivity {

    EntryDatabase db;
    EntryAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        db = EntryDatabase.getInstance(getApplicationContext());
        Cursor cursor = db.selectAll();
        adapter = new EntryAdapter(this, R.layout.entry_row, cursor);
        ListView listview = findViewById(R.id.listview);
        listview.setAdapter(adapter);

        FloatingActionButton Button = findViewById(R.id.floatingActionButton);
        ButtonOnClickListener a = new ButtonOnClickListener();
        Button.setOnClickListener(a);

        ListItemClickListener onclick = new ListItemClickListener();
        listview.setOnItemClickListener(onclick);

        ListOnItemLongClickListener onclicklong = new ListOnItemLongClickListener();
        listview.setOnItemLongClickListener(onclicklong);

    }

    //  Update the listView on resume
    @Override
    protected void onResume() {
        super.onResume();
        updateData();
    }

    // when button is pressed, go to Input
    public class ButtonOnClickListener implements View.OnClickListener {

        @Override
        public void onClick(View v) {
            Intent intentInput = new Intent(MainActivity.this, InputActivity.class);
            startActivity(intentInput);
        }
    }


    public class ListItemClickListener implements AdapterView.OnItemClickListener {

        @Override
        public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

            Log.d("check", "checkcheck");

            Cursor cursor = (Cursor) adapterView.getItemAtPosition(i);

            String title = cursor.getString(cursor.getColumnIndex("title"));

            String date = cursor.getString(cursor.getColumnIndex("date"));

            String mood = cursor.getString(cursor.getColumnIndex("mood"));

            String content = cursor.getString(cursor.getColumnIndex("content"));

            Intent intentDetail = new Intent(MainActivity.this, DetailActivity.class);

            intentDetail.putExtra("title", title);

            intentDetail.putExtra("mood", mood);

            intentDetail.putExtra("content", content);

            intentDetail.putExtra("date", date);

            startActivity(intentDetail);
        }
    }

    public class ListOnItemLongClickListener implements AdapterView.OnItemLongClickListener {
        @Override

        public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {

            Cursor cursor = (Cursor) adapterView.getItemAtPosition(i);

            int IDdel = cursor.getInt(cursor.getColumnIndex("_id"));

            EntryDatabase db = EntryDatabase.getInstance(getApplicationContext());

            db.delete(IDdel);
            updateData();
            return true;
        }
    }

    private void updateData() {

        Cursor newCursor = db.selectAll();
        adapter.swapCursor(newCursor);
    }

}
