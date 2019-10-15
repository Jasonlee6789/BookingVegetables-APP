package com.example.quizapp;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CheckedTextView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

public class ListActivity extends AppCompatActivity {
    TextView textUser;
    public String name;
    public String tel;
    public ArrayList<String> productList = new ArrayList<>();
    public ArrayList<String> selectedList = new ArrayList<>();
    private Button btnSelected;
    private Button btnReset;
    private Button btnSave;
    private ListView list;
    private boolean isOldList = true;

    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);

        //get listView from  the reference
        list = findViewById(R.id.orderList);
        btnSelected = findViewById(R.id.btn_selected);
        btnReset = findViewById(R.id.btn_reset);

        btnSave = findViewById(R.id.btn_save);
        btnSelected.setVisibility(View.VISIBLE);
        btnSave.setVisibility(View.GONE);
        getIntentData();
        textUser = findViewById(R.id.textUser);
        textUser.setText("name:" + name + "   TEL:" + tel);

        createList();

        initListView();

        initListener();


    }// end of create method

    private void getIntentData() {
        Intent intent = getIntent();// return the intent that started this activity
        name = intent.getStringExtra(IntentKey.USER_NAME);
        tel = intent.getStringExtra(IntentKey.USER_PHONE);
    }

    private void createList() {
        productList.add("September 1th week:Apple");
        productList.add("September  2th week:Banana");
        productList.add("September  3th week:Potato");
        productList.add("September  4th week：Tomato");

    }

    private void initListView() {


        //ceate ArrayAdapter a new object "adapter" and set it  with type and content
        ArrayAdapter adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_checked, productList);
        //combine listview and adapter to add  the data
        list.setAdapter(adapter);
        list.setChoiceMode(ListView.CHOICE_MODE_SINGLE);

        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                if (isOldList) {
                    selectedList.add(productList.get(i));
                }
            }
        });
    }

    private void initListener() {

        btnSelected.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                isOldList = false;
                final ArrayAdapter adapter1 = new ArrayAdapter(ListActivity.this, android.R.layout.simple_list_item_1, selectedList);
                list.setAdapter(adapter1);
                btnSave.setVisibility(View.VISIBLE);
                btnSelected.setVisibility(View.GONE);
            }
        });

        btnReset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                isOldList = true;
                selectedList.clear();
                final ArrayAdapter adapter = new ArrayAdapter(ListActivity.this, android.R.layout.simple_list_item_multiple_choice, productList);
                list.setAdapter(adapter);
                list.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE);

            }
        });
// Insert the SQL table with the button SAVE
        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String selected = selectedList.get(0);

                DbHandler dbHandler = new DbHandler(ListActivity.this);

                dbHandler.insertUserDetails(name, tel, selected);

                Toast.makeText(ListActivity.this, "Your order has been received by NZSQL", Toast.LENGTH_SHORT).show();
            }
        });
    }


}
