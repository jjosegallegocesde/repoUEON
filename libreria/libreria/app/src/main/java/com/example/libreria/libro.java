package com.example.libreria;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class libro extends AppCompatActivity {
DataBase DataBase2=new DataBase(this,"Book",null,1);
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_libro);
        EditText id=findViewById(R.id.idbook);
        EditText nombre=findViewById(R.id.nombre);
        EditText cost=findViewById(R.id.cost);
        Button listar=findViewById(R.id.listar3);
        Button crear=findViewById(R.id.donar);
listar.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {
        startActivity(new Intent(getApplicationContext(),listar3.class));
    }
});
        crear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!id.getText().toString().isEmpty()&&!nombre.getText().toString().isEmpty()&&!cost.getText().toString().isEmpty()){
              SQLiteDatabase DBsearchBook=DataBase2.getReadableDatabase();
              String query="SELECT idBook,nombre,cost,available FROM Book WHERE idBook='"+Integer.parseInt(id.getText().toString())+"'";
              Cursor cursor_libro=DBsearchBook.rawQuery(query,null);
                    if (!cursor_libro.moveToFirst()){
                        int dispo=0;
                        SQLiteDatabase DBregisterBook=DataBase2.getWritableDatabase();
                        ContentValues Content_BOOK=new ContentValues();
                        Content_BOOK.put("idBook",Integer.parseInt(id.getText().toString()));
                        Content_BOOK.put("nombre",nombre.getText().toString());
                        Content_BOOK.put("cost",cost.getText().toString());
                        Content_BOOK.put("available",dispo);
                        DBregisterBook.insert("Book",null,Content_BOOK);
                        DBregisterBook.close();
                        Toast.makeText(getApplicationContext(),"libro añadido correctamente",Toast.LENGTH_LONG).show();
                    }else {
                        id.setText("");
                        Toast.makeText(getApplicationContext(),"YA HAY UN LIBRO CON ESE ID O NO ESXISTE",Toast.LENGTH_LONG).show();
                    }



                }else{
                    Toast.makeText(getApplicationContext(),"debes ingresar todos los datos",Toast.LENGTH_LONG).show();
                }
            }
        });
    }
}