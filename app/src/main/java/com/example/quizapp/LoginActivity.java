package com.example.quizapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import java.util.List;

import static com.example.quizapp.IntentKey.USER_NAME;
import static com.example.quizapp.IntentKey.USER_PHONE;

public class LoginActivity extends AppCompatActivity {


    private Button btnGo;
    private EditText et_name;
    private EditText et_tel;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        et_name = findViewById(R.id.et_name);
        et_tel = findViewById(R.id.et_tel);


        btnGo = findViewById(R.id.btnGo);
        btnGo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
              String name = et_name.getText().toString();
              String tel = et_tel.getText().toString();
              Intent intent = new Intent(LoginActivity.this, ListActivity.class);// change
              intent.putExtra(IntentKey.USER_NAME, name);//使用putExtra（）系列方法向Intent对象存储数据
              intent.putExtra(IntentKey.USER_PHONE,tel);
              startActivity(intent);
            }
        });
    }
}
