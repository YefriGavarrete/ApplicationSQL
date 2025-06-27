package com.example.applicationsql.Conexion;

import android.app.Person;
import android.database.sqlite.SQLiteOpenHelper;
import android.content.Context;


import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;


public class sqliteConexion extends SQLiteOpenHelper
{

    public sqliteConexion(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(PersonasDB.CreateTablePersonas);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(PersonasDB.DropTablePersonas);
        onCreate(db);
    }



}
