package com.example.libreria;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

public class listar3 extends AppCompatActivity {
    ListView list;
    ArrayList<String> arr_user;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listar3);
        list=findViewById(R.id.listbooks);
        loadUsers();

    }
    private void loadUsers(){
        arr_user=retrieveUsers();
        ArrayAdapter<String> user_adapter=new ArrayAdapter<String>(this,android.R.layout.simple_expandable_list_item_1,arr_user);
        list.setAdapter(user_adapter);
    }

    private ArrayList<String> retrieveUsers() {
        ArrayList<String> userdata = new ArrayList<String>();
        DataBase bd1 = new DataBase(this, "Book", null, 1);
        SQLiteDatabase bdusers = bd1.getReadableDatabase();
        String query = "SELECT idBook,nombre,cost,available From Book Limit 100";
        Cursor cursor_user = bdusers.rawQuery(query, null);
        if (cursor_user.moveToFirst()) {
            do {
                String line = cursor_user.getString(0) + "-" + cursor_user.getString(1) + "-" + cursor_user.getInt(2) + "-" + cursor_user.getInt(3);
                userdata.add(line);
            }
            while (cursor_user.moveToNext());
            bdusers.close();
        }
        return userdata;
    }
}