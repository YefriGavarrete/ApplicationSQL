package com.example.applicationsql;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

import com.example.applicationsql.Conexion.Personas;
import com.example.applicationsql.Conexion.PersonasDB;
import com.example.applicationsql.Conexion.sqliteConexion;

import java.util.ArrayList;
import java.util.List;

public class Activity_Seleccionar extends AppCompatActivity {

    sqliteConexion conexion;
    ListView listaPersonas;
    ArrayList<Personas> lista;
    ArrayList<String> arregloLista;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_seleccionar);
        conexion = new sqliteConexion(this, PersonasDB.NameDB, null, 1);
        listaPersonas = findViewById(R.id.listViewPersonas);

        ObtenerInfo();
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this,
                android.R.layout.simple_list_item_1,
                arregloLista);
        listaPersonas.setAdapter(adapter);

        listaPersonas.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Personas persona = lista.get(position);
                Intent intent = new Intent(Activity_Seleccionar.this, Activity_Actualizar.class);
                intent.putExtra("persona", persona.getId());
                startActivity(intent);
            }

        });
    }
    private void ObtenerInfo() {
        SQLiteDatabase db = conexion.getReadableDatabase();
        Personas persona = null;
        lista = new ArrayList<>();

        Cursor cursor = db.rawQuery(PersonasDB.SelectPersonas, null);
        while (cursor.moveToNext()) {
            persona = new Personas();
            persona.setId(cursor.getInt(0));
            persona.setNombres(cursor.getString(1));
            persona.setApellidos(cursor.getString(2));
            persona.setEdad(cursor.getInt(3));
            persona.setCorreo(cursor.getString(4));
            persona.setDireccion(cursor.getString(5));
            lista.add(persona);
        }
        cursor.close();
        FillData();
    }

    private void FillData() {
        arregloLista = new ArrayList<>();
        for (Personas p : lista) {
            arregloLista.add(p.getId() + " | " + p.getNombres() + " " + p.getApellidos() + " " + p.getEdad() + " "+ p.getCorreo() + " " + p.getDireccion());
        }
    }


}