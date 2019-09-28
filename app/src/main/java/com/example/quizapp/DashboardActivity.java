package com.example.quizapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import java.util.ArrayList;
import java.util.HashMap;

public class DashboardActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);

        final DbHandler db = new DbHandler(this);

        ArrayList<HashMap<String, String>> userList = db.getUsers();

        ListView listView = findViewById(R.id.user_list);
        ListAdapter adapter = new SimpleAdapter(DashboardActivity.this,
                userList,
                R.layout.list_row,
                new String[]{"id","name", "num", "selected"},
                new int[]{R.id.pid, R.id.name, R.id.num, R.id.selected});
        listView.setAdapter(adapter);

    }
}
