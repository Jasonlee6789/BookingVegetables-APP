package com.example.quizapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;

public class AdminActivity extends AppCompatActivity {


    Intent intent;
    private Button btnUpdate;
    private Button btnDelete;
    private EditText ID,name,num,selected;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin);

        btnUpdate = findViewById(R.id.btnUpdate);
        btnDelete = findViewById(R.id.btnDelete);
        ID = findViewById(R.id.deleteId);
        name = findViewById(R.id.updateName);
        num = findViewById(R.id.updateNum);
        selected = findViewById(R.id.updateSelected);

        final DbHandler db = new DbHandler(this);

        ArrayList<HashMap<String, String>> userList = db.getUsers();

        ListView listView = findViewById(R.id.user_list);
        ListAdapter adapter = new SimpleAdapter(AdminActivity.this,
                userList,
                R.layout.list_row,
                new String[]{"id","name", "num", "selected"},
                new int[]{R.id.pid, R.id.name, R.id.num, R.id.selected});
        listView.setAdapter(adapter);

       btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
            String rname = name.getText().toString();
            String rnum = num.getText().toString();
            String rselected = selected.getText().toString();
            DbHandler dbHandler = new DbHandler(AdminActivity.this);
            dbHandler.updateUserDetails(rname,rnum,rselected);

                Toast.makeText(getApplicationContext(), "Use information has been  updated", Toast.LENGTH_SHORT).show();
            }
        });

        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int i;
                i  = Integer.parseInt(ID.getText().toString());

                db.deleteUser(i);
            }
        });
    }
}
