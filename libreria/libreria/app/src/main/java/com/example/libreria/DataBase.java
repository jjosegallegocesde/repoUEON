package com.example.libreria;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DataBase extends SQLiteOpenHelper {
    String TBLuser="CREATE TABLE User (idUser integer PRIMARY KEY,nombre text,email text,password text,status integer,FOREIGN KEY (idUser) REFERENCES Rent (idUser))";
    String TBLrent="CREATE TABLE Rent(idRent integer PRIMARY KEY,idUser integer,idBook integer,date text)";
    String TBLbook="CREATE TABLE Book(idBook integer PRIMARY KEY,nombre text,cost text,available integer,FOREIGN KEY (idBook) REFERENCES Rent(idBook))";

    public DataBase(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(TBLuser);
        db.execSQL(TBLrent);
        db.execSQL(TBLbook);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
db.execSQL("DROP TABLE User");
db.execSQL(TBLuser);
db.execSQL("DROP TABLE Rent");
db.execSQL(TBLrent);
db.execSQL("DROP TABLE Book");
db.execSQL(TBLbook);
    }
}
