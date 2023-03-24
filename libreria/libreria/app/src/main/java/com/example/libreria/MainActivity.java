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

public class MainActivity extends AppCompatActivity {
DataBase DB=new DataBase(this,"User",null,1);
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        EditText name=findViewById(R.id.name);
        EditText email=findViewById(R.id.email);
        EditText password=findViewById(R.id.password);
        EditText ident=findViewById(R.id.identificador);
        Button login=findViewById(R.id.login);
        Button register=findViewById(R.id.register);
        Button lista=findViewById(R.id.listar1);


        lista.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),listar1.class));
            }
        });
login.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {
        if(!name.getText().toString().isEmpty()&&!email.getText().toString().isEmpty()&&!password.getText().toString().isEmpty()&&!ident.getText().toString().isEmpty()){
            SQLiteDatabase DBlogin=DB.getReadableDatabase();
            String query2="SELECT nombre,email,idUser,password,status FROM User WHERE email='"+email.getText().toString()+"'AND password='"+password.getText().toString()+"'";
            Cursor Clogin=DBlogin.rawQuery(query2,null);
            if (Clogin.moveToFirst()){
                Intent iHOME=new Intent(getApplicationContext(),HOME.class);
                iHOME.putExtra("Eident",ident.getText().toString());
                startActivity(iHOME);
            }else {
                Toast.makeText(getApplicationContext(),"INGRESASTE LOS DATOS MAL O LA CUENTA NO EXISTE",Toast.LENGTH_SHORT).show();
            }

        }else{
            Toast.makeText(getApplicationContext(),"DEBES INGRESAR TODOS LOS DATOS",Toast.LENGTH_LONG).show();
        }
    }
});

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!name.getText().toString().isEmpty()&&!email.getText().toString().isEmpty()&&!password.getText().toString().isEmpty()&&!ident.getText().toString().isEmpty()){
                    SQLiteDatabase DBregister2=DB.getReadableDatabase();
                    String query="SELECT nombre,email,idUser FROM User WHERE email='"+email.getText().toString()+"'";
                    Cursor Cregister=DBregister2.rawQuery(query,null);
                   if(!Cregister.moveToFirst()){
                       SQLiteDatabase DBregister=DB.getWritableDatabase();
                       ContentValues CVregister=new ContentValues();
                       CVregister.put("idUser",ident.getText().toString());
                       CVregister.put("nombre",name.getText().toString());
                       CVregister.put("email",email.getText().toString());
                       CVregister.put("password",password.getText().toString());
                       CVregister.put("status",0);
                       DBregister.insert("User",null,CVregister);
                       DBregister.close();
                       Toast.makeText(getApplicationContext(),"usuario guardado exitosamente",Toast.LENGTH_SHORT).show();
                   }else{
                       Toast.makeText(getApplicationContext(),"YA HAY UNA CUENTA CON ESE CORREO",Toast.LENGTH_SHORT).show();
                   }
                }
                else{
                    Toast.makeText(getApplicationContext(),"DEBES INGRESAR TODOS LOS DATOS PARA REGISTRARTE EN LA LIBRERIA",Toast.LENGTH_LONG).show();
                }
            }
        });




    }
}