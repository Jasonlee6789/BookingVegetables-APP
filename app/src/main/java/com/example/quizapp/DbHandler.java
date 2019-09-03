package com.example.quizapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;//The SQLiteDatabase class provides various methods to perform create, read, update and delete operations.
import android.database.sqlite.SQLiteOpenHelper;//The SQLiteOpenHelper class provides all the functionality for sqlite database.

import java.util.ArrayList;
import java.util.HashMap;

public class DbHandler extends SQLiteOpenHelper {

    private static final int DB_VERSION = 1;
    private static final String DB_NAME = "usersdb";
    private static final String TABLE_Users = "userdetails";
    private static final String KEY_ID = "id";
    private static final String KEY_NAME = "name";
    private static final String KEY_NUM = "num";
    private static final String KEY_SELECTED= "selected";
//For creating the database we will call constructor of SQLiteOpenHelper class using super().
//Pass the DB_NAME and DB_VERSION in the superclass within your constructor.
//VERSION =1  STANDS FOR ONCE CREATING , if you want to change the space below in the green TEXT, you have to delete the app
    public DbHandler(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }


    //In the onCreate() method, create a SQL query that will allow the user to create a table
    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_TABLE = "CREATE TABLE " + TABLE_Users + "("
                + KEY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                + KEY_NAME + " TEXT,"
                + KEY_NUM + " TEXT,"
                + KEY_SELECTED + " TEXT"
                + ")";
        db.execSQL(CREATE_TABLE);
//The code required to create table will be written inside onCreate() method.
    }


    //In the onUpgrade() method, write a SQL command to drop the table if it exists ad re-create it if there’s a new table to be created.
    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_Users);
        // Drop older table if exists
        onCreate(db);
        // Create table again
        //onUpgrade() method contains the code required to update the database.
    }

    // create a new method  to insert user details
    public void insertUserDetails(String name, String num, String selected) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues cValues = new ContentValues();
        cValues.put(KEY_NAME, name);
        cValues.put(KEY_NUM, num);
        cValues.put(KEY_SELECTED, selected);

// Insert the new row, returning the primary key-value of the new row then close the db after insertion
        long newRodId = db.insert(TABLE_Users, null, cValues);
        db.close();
    }



    // method to get the uers from the database
    public ArrayList<HashMap<String, String>> getUsers() {
        //In this method, create an instance of the SQLiteDatabase class
        //and initialise it by calling the getWritableDatabase() method.
        SQLiteDatabase db = this.getWritableDatabase();
//For the return type, you need to create a new ArrayList that contains a HashMap as shown:
        ArrayList<HashMap<String, String>> userList = new ArrayList<>();

//Then make a string query that will select the name,  num ,selected from the table in the database.
        String query = "SELECT name, num, selected FROM " + TABLE_Users;

        //Create an instance of the Cursor class and then pass the raw query to it
        //The results of the query are returned to you in a Cursor object
        Cursor cursor = db.rawQuery(query, null);
//Cursor object that will be used to fetch the records one by one.
//The Cursor is always the mechanism with which you can navigate results from a database query and read rows and columns.

//Iterate over the cursor object using a while loop and calling the moveToNext() method.
//Within this while loop, collect the requested information and save it to a hashmap.
        while (cursor.moveToNext()) {
            HashMap<String, String> user = new HashMap<>();
            user.put("id", String.valueOf(cursor.getColumnIndex(KEY_ID)));
            user.put("name", cursor.getString(cursor.getColumnIndex(KEY_NAME)));
            user.put("num", cursor.getString(cursor.getColumnIndex(KEY_NUM)));
            user.put("selected", cursor.getString(cursor.getColumnIndex(KEY_SELECTED)));
//Then add the hashmap to the arraylist.
            userList.add(user);
        }
        db.close();
        return userList;
    } // Finally, return the arraylist.


//   create a method to updating person's details
    public int updateUserDetails( String name,String num, String selected) {
     SQLiteDatabase db = this.getWritableDatabase();

      ContentValues contentValues = new ContentValues();

        contentValues.put(KEY_NAME, name);
        contentValues.put(KEY_NUM, num);
        contentValues.put(KEY_SELECTED, selected);
        int count = db.update(TABLE_Users,contentValues,KEY_NUM +" = ?",new String[]{String.valueOf(num)});

       return count;
    }

    //create  a method to delete
    public void deleteUser(int userId) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_Users, KEY_ID + " =?", new String[]{String.valueOf(userId)});
        db.close();
    }
}

