package com.example.quizapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Toast;
import android.widget.Toolbar;

import com.google.android.material.navigation.NavigationView;

import java.util.ArrayList;
import java.util.HashMap;

public class AdminActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener{


    Intent intent;
    private Button btnUpdate;
    private Button btnDelete;
    private EditText ID,name,num,selected;
    //step 3:Create a DrawerLayout instance in the MainActivity class
    private DrawerLayout drawer ;

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

//step 1:Tell our app that we want to use our newly created toolbar
        androidx.appcompat.widget.Toolbar toolbar = findViewById(R.id.toolbar);
// step 2:Then call the setSupportActionBar() method to display the toolbar in the app.
        setSupportActionBar(toolbar);//Run the app and slide from the left side. At its current start,
//step 3: and initialize it inside the onCreate() method by referencing it with the id drawer_layout
//(the ID given to the activity_admin.xml)
        drawer = findViewById(R.id.drawer_layout);
        //link the fragement to the menu item click
        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        //step 4:Now add the code to create the ActionBarDrawerToggle instance
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawer, toolbar,
                R.string.open_navigation_draw, R.string.close_navigation_draw);
// step 5 :The next line should let the drawer instance call the addDrawerListener()
        drawer.addDrawerListener(toggle);
// step 6:The toggle instance must then call the syncState() method
        toggle.syncState();



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
                Toast.makeText(getApplicationContext(), "You should go back to LastActivity to check", Toast.LENGTH_SHORT).show();
                db.deleteUser(i);
            }
        });
    }// End of OnCreate()


    // step 7 :. Override the onBackPressed() method so that we won’t leave the app when the back button is clicked.
    @Override
    public void onBackPressed() {
        if(drawer.isDrawerOpen(GravityCompat.START)){
            drawer.closeDrawer(GravityCompat.START);
        }
        else{
            super.onBackPressed();
        }
        super.onBackPressed();
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
        switch (menuItem.getItemId()){
            case R.id.nav_chat:
                //Toast.makeText(this, "Show the updated content ", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(this,DashboardActivity.class));
                break;


            case R.id.nav_share:
                Toast.makeText(this, "Share", Toast.LENGTH_SHORT).show();
                break;

        }
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    private void setSupportActionBar(Toolbar toolbar) {
    }
}
