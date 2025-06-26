package com.example.applicationsql.Conexion;
import android.app.Person;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.content.Context;

import java.util.ArrayList;

public class PersonasDB extends SQLiteOpenHelper{
    private static final String personasDB = "personas.db";
    private static final int DATABASE_VERSION = 1;
    
    public static final String tablaPersonas = "personas";
    public static final String id = "id";
    public static final String nombres = "nombres";
    public static final String apellidos = "apellidos";
    public static final String edad = "edad";
    public static final String correo = "correo";
    public static final String direccion = "direccion";


    public static final String CreateTablaPersonas = "CREATE TABLE " + tablaPersonas + "(" +
            "id INTEGER PRIMARY KEY AUTOINCREMENT, " +
            nombres + "TEXT, " +
            apellidos  + "TEXT, " +
            edad + "INTEGER, " +
            correo + "TEXT, " +
            direccion + "TEXT)";


   // public static final DROPTablePersonas = "DROP TABLE IF EXISTS" + tablaPersonas;
   public PersonasDB(Context context) {
       super(context, personasDB, null, DATABASE_VERSION);
   }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CreateTablaPersonas);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + tablaPersonas);
        onCreate(db);
    }






   // public static final String SelectPersonas = "SELECT * FROM " +tablaPersonas;




}
