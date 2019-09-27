package com.example.quizapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private Button btnLogin;
    GridView gridView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main); //this place  is different

        Toolbar toolbar = findViewById(R.id.app_bar);
        setSupportActionBar(toolbar);



        btnLogin = findViewById(R.id.btnLogin);
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, LoginActivity.class);
                startActivity(intent);
            }
        });

        gridView = findViewById(R.id.gridView);
        gridView.setAdapter(new ImageAdapter(this));
        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                //Toast.makeText(getBaseContext(),"You selceted package at :"+i, Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(getApplicationContext(),GridItemActivity.class);
               intent.putExtra("name",ImageAdapter.packNames[i]);
               intent.putExtra("image", ImageAdapter.image_Id[i]);
                startActivity(intent);
            }
        });
    }  // end of onCreate method

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu, menu);

        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.help_menu:
                Intent helpintent = new Intent(this, HelpActivity.class);
                startActivity(helpintent);
                Toast.makeText(this, "Help!!!Jason", Toast.LENGTH_SHORT).show();
                break;
            case R.id.admin:
                Intent adminIntent = new Intent(this, AdminActivity.class);
                startActivity(adminIntent);
                Toast.makeText(this, "This is the web admin can see the result", Toast.LENGTH_SHORT).show();
                break;
            default:
        }
        return super.onOptionsItemSelected(item);
    }

/*    public void EmailHelp(View view){
        // using implict Intent,before writing the below code ,I have configure the mainfest file first
        Intent intent = new Intent();
        intent.setAction(Intent.ACTION_SEND);
        intent.setType("text/plain");
        intent.putExtra("message","jeffersonlee@163.com");
        startActivity(intent);
    }*/

}
