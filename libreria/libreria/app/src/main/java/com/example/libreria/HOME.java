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

public class HOME extends AppCompatActivity {
    DataBase DataBase3=new DataBase(this,"Book",null,1);
    DataBase Database4=new DataBase(this,"Rent",null,1);
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        EditText id=findViewById(R.id.idBook);
        EditText idrent=findViewById(R.id.idRent);
        EditText date=findViewById(R.id.fecha);
        Button rentar=findViewById(R.id.rentar);
        Button listar=findViewById(R.id.listar2);
        Button libro=findViewById(R.id.setLibro);
        String idUser=getIntent().getStringExtra("Eident");
        libro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),libro.class));
            }
        });
        listar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),listar2.class));
            }
        });

        rentar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!id.getText().toString().isEmpty()&&!idrent.getText().toString().isEmpty()&&!date.getText().toString().isEmpty()){
                    SQLiteDatabase database1=DataBase3.getReadableDatabase();
                    String query="SELECT idBook,nombre,cost,available FROM Book WHERE idBook='"+Integer.parseInt(id.getText().toString())+"'AND available='"+0+"'";
                    Cursor Cursor_book=database1.rawQuery(query,null);
                    if(Cursor_book.moveToFirst()){
                        SQLiteDatabase db3=Database4.getWritableDatabase();
                        ContentValues con_rentar=new ContentValues();
                        con_rentar.put("idRent", Integer.parseInt(idrent.getText().toString()));
                        con_rentar.put("idUser", Integer.parseInt(idUser));
                        con_rentar.put("idBook", Integer.parseInt(id.getText().toString()));
                        con_rentar.put("date",date.getText().toString());
                        db3.insert("Rent",null,con_rentar);
                        db3.close();
                        SQLiteDatabase db2=DataBase3.getWritableDatabase();
                        String query2="UPDATE Book SET available='"+1+"'WHERE idBook='"+Integer.parseInt(id.getText().toString())+"'";
                        db2.execSQL(query2);
                        Toast.makeText(getApplicationContext(),"libro rentado exitosamente",Toast.LENGTH_LONG).show();
                    }
                    else {
                        Toast.makeText(getApplicationContext(),"el libro no esta disponible o no existe",Toast.LENGTH_LONG).show();
                    }



                        }else {
                    Toast.makeText(getApplicationContext(),"debes ingresar todos los datos",Toast.LENGTH_LONG).show();

                    }
            }
        });

    }


}