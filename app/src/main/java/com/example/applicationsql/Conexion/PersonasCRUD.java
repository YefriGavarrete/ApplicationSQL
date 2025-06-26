package com.example.applicationsql.Conexion;
import java.net.ConnectException;
import java.util.ArrayList;
import java.util.List;

import android.app.Person;
import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.content.Context;

public class PersonasCRUD {
    private PersonasDB personasD;

    public PersonasCRUD(Context context){
        personasD = new PersonasDB(context);
    }

    public long insertarPersonas(Personas persona){
        SQLiteDatabase db = personasD.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(PersonasDB.nombres,persona.getNombres());
        values.put(PersonasDB.apellidos,persona.getApellidos());
        values.put(PersonasDB.edad,persona.getEdad());
        values.put(PersonasDB.correo,persona.getCorreo());
        values.put(PersonasDB.direccion,persona.getDireccion());
        long id = db.insert(PersonasDB.tablaPersonas, null, values);
        db.close();
        return id;
    }

    public int actualizarPersona(Personas persona){
        SQLiteDatabase db = personasD.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(PersonasDB.nombres,persona.getNombres());
        values.put(PersonasDB.apellidos,persona.getApellidos());
        values.put(PersonasDB.edad,persona.getEdad());
        values.put(PersonasDB.correo,persona.getCorreo());
        values.put(PersonasDB.direccion,persona.getDireccion());
        int rows = db.update(PersonasDB.tablaPersonas, values,  PersonasDB.id + "=?", new String []{String.valueOf(persona.getId())});
        db.close();
        return rows;

    }

    public int eliminarPersonas(Personas persona){
        SQLiteDatabase db = personasD.getWritableDatabase();
        int rows = db.delete(PersonasDB.tablaPersonas,PersonasDB.id + "=?", new String []{String.valueOf(persona.getId())});
        db.close();
        return rows;

    }


    public List<Personas> obtenerPersonas(){
        List<Personas> lista = new ArrayList<>();
        SQLiteDatabase db = personasD.getWritableDatabase();
        Cursor cursor = db.query(PersonasDB.tablaPersonas, null, null, null, null, null, null);
        if (cursor.moveToFirst()){
            do {
                Personas p = new Personas(
                        cursor.getInt(cursor.getColumnIndexOrThrow(PersonasDB.id)),
                        cursor.getString(cursor.getColumnIndexOrThrow(PersonasDB.nombres)),
                        cursor.getString(cursor.getColumnIndexOrThrow(PersonasDB.apellidos)),
                        cursor.getInt(cursor.getColumnIndexOrThrow(PersonasDB.edad)),
                        cursor.getString(cursor.getColumnIndexOrThrow(PersonasDB.correo)),
                        cursor.getString(cursor.getColumnIndexOrThrow(PersonasDB.direccion))
                );
               lista.add(p);

            }
            while(cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return lista;
    }

    public Personas obtenerPersonaPorId(int idPersona) {
        SQLiteDatabase db = personasD.getReadableDatabase();
        Personas persona = null;
        Cursor cursor = db.query(PersonasDB.tablaPersonas, null, PersonasDB.id + "=?",
                new String[]{String.valueOf(idPersona)}, null, null, null);
        if (cursor.moveToFirst()) {
            persona = new Personas(
                    cursor.getInt(cursor.getColumnIndexOrThrow(PersonasDB.id)),
                    cursor.getString(cursor.getColumnIndexOrThrow(PersonasDB.nombres)),
                    cursor.getString(cursor.getColumnIndexOrThrow(PersonasDB.apellidos)),
                    cursor.getInt(cursor.getColumnIndexOrThrow(PersonasDB.edad)),
                    cursor.getString(cursor.getColumnIndexOrThrow(PersonasDB.correo)),
                    cursor.getString(cursor.getColumnIndexOrThrow(PersonasDB.direccion))
            );
        }
        cursor.close();
        db.close();
        return persona;
    }



}
