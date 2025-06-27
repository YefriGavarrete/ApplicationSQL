package com.example.applicationsql.Conexion;
import android.app.Person;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.content.Context;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

public class PersonasDB{
    public static final String NameDB = "PersonasDB";
    public static final String tablaPersonas = "personas";

    // Campos de la tabla
    public static final String id = "id";
    public static final String nombre = "nombre";
    public static final String apellidos = "apellidos";
    public static final String edad = "edad";
    public static final String correo = "correo";
    public static final String direccion = "direccion";

    // DDL CREATE
    public static final String CreateTablePersonas = "CREATE TABLE " + tablaPersonas + " ( " +
            "id INTEGER PRIMARY KEY AUTOINCREMENT, " +
            "nombre TEXT, " +
            "apellidos TEXT, " +
            "edad INTEGER, " +
            "correo TEXT, " +
            "direccion TEXT )";

    // DDL DROP
    public static final String DropTablePersonas = "DROP TABLE IF EXISTS " + tablaPersonas;

    // SELECT
    public static final String SelectPersonas = "SELECT * FROM " + tablaPersonas;

    // DDL DROP


}


   // public static final DROPTablePersonas = "DROP TABLE IF EXISTS" + tablaPersonas;



