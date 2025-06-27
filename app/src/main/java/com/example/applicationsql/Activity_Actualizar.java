package com.example.applicationsql;

import static com.example.applicationsql.Conexion.PersonasDB.nombre;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

import com.example.applicationsql.Conexion.Personas;

import com.example.applicationsql.Conexion.PersonasDB;
import com.example.applicationsql.Conexion.sqliteConexion;

import java.util.List;

public class Activity_Actualizar extends AppCompatActivity {
    EditText nombres, apellidos, edad, correo, direccion;

    Button btnActualizar, btnEliminar;
    PersonasDB personasDB;
    int idPersona;
    sqliteConexion conexion;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_actualizar);
        nombres = (EditText) findViewById(R.id.nombre);
        apellidos = (EditText) findViewById(R.id.apellidos);
        edad = (EditText) findViewById(R.id.edad);
        correo = (EditText) findViewById(R.id.correo);
        direccion = (EditText) findViewById(R.id.direccion);
        btnActualizar = (Button) findViewById(R.id.btnSalvar);
        btnEliminar = (Button) findViewById(R.id.btnEliminarP);

        conexion = new sqliteConexion(this, PersonasDB.NameDB, null, 1);
        idPersona = getIntent().getIntExtra("id", -1);

        if (idPersona != -1) {
            cargarDatosPersona(idPersona);
        }

//        idPersona = getIntent().getIntExtra("persona", -1);
//        conexion = new sqliteConexion(this, PersonasDB.NameDB, null, 1);
//        idPersona = getIntent().getIntExtra("id", -1);
//        nombres.setText(getIntent().getStringExtra("nombres"));
//        apellidos.setText(getIntent().getStringExtra("apellidos"));
//        edad.setText(String.valueOf(getIntent().getIntExtra("edad", 0)));
//        correo.setText(getIntent().getStringExtra("correo"));
//        direccion.setText(getIntent().getStringExtra("direccion"));

        btnActualizar.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {

                    SQLiteDatabase db = conexion.getWritableDatabase();
                    ContentValues values = new ContentValues();
                    values.put(nombre, nombres.getText().toString());
                    values.put(PersonasDB.apellidos, apellidos.getText().toString());
                    values.put(PersonasDB.edad, Integer.parseInt(edad.getText().toString()));
                    values.put(PersonasDB.correo, correo.getText().toString());
                    values.put(PersonasDB.direccion, direccion.getText().toString());
                    db.update(PersonasDB.tablaPersonas, values, "id = ?", new String[]{String.valueOf(idPersona)});
                    Toast.makeText(Activity_Actualizar.this, "Persona actualizada", Toast.LENGTH_SHORT).show();
                    finish();
            }
        });

        btnEliminar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SQLiteDatabase db = conexion.getWritableDatabase();
                db.delete(PersonasDB.tablaPersonas, "id = ?", new String[]{String.valueOf(idPersona)});
                Toast.makeText(Activity_Actualizar.this, "Persona eliminada", Toast.LENGTH_SHORT).show();
                finish();
            }
        });
        }

        private void cargarDatosPersona(int id) {
            SQLiteDatabase db = conexion.getReadableDatabase();
            Cursor cursor = db.rawQuery("SELECT * FROM " + PersonasDB.tablaPersonas + " WHERE id = " + id, null);
            if (cursor.moveToFirst()) {
                nombres.setText(cursor.getString(1));
                apellidos.setText(cursor.getString(2));
                edad.setText(String.valueOf(cursor.getInt(3)));
                correo.setText(cursor.getString(4));
                direccion.setText(cursor.getString(5));
            }
            cursor.close();
    }
}