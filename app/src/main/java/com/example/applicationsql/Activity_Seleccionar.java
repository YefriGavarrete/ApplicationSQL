package com.example.applicationsql;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.example.applicationsql.Conexion.Personas;
import com.example.applicationsql.Conexion.PersonasDB;
import com.example.applicationsql.Conexion.sqliteConexion;

import java.util.ArrayList;
import java.util.List;

public class Activity_Seleccionar extends AppCompatActivity {
    ActivityResultLauncher<Intent> actualizarLauncher;

    sqliteConexion conexion;
    ListView listaPersonas;
    ArrayList<Personas> lista;
    ArrayList<String> arregloLista;
    Personas persona;
    int posicionSelecionada = -1;
    Button btnActualizar, btnEliminar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_seleccionar);
        conexion = new sqliteConexion(this, PersonasDB.NameDB, null, 1);
        listaPersonas = findViewById(R.id.listViewPersonas);
        btnActualizar = findViewById(R.id.btnActualizar);
        btnEliminar = findViewById(R.id.btnEliminar);
        lista = new ArrayList<>();
        actualizarLauncher = registerForActivityResult(
                new ActivityResultContracts.StartActivityForResult(),
                result -> {
                    if (result.getResultCode() == RESULT_OK) {
                        Toast.makeText(this, "El usuario ha sido actualizado", Toast.LENGTH_SHORT).show();
                        ObtenerInfo();
                        ActualizarListaView();
                    }
                }
        );


        ObtenerInfo();
        ActualizarListaView();
        listaPersonas.setOnItemClickListener((parent, view, position, id) -> {
            posicionSelecionada = position;
            Toast.makeText(this, "Usuario selecionado", Toast.LENGTH_SHORT).show();
        });


        btnActualizar.setOnClickListener(v -> {
            if (posicionSelecionada == -1) {
                Toast.makeText(this, "Seleccione un usuario primero", Toast.LENGTH_SHORT).show();
                return;
            }

            Personas persona = lista.get(posicionSelecionada);
            Intent intent = new Intent(Activity_Seleccionar.this, Activity_Actualizar.class);
            intent.putExtra("id", persona.getId());
            actualizarLauncher.launch(intent);
        });


        btnEliminar.setOnClickListener(v ->{
            if(posicionSelecionada == -1){
                Toast.makeText(this, "Seleccione un usuario", Toast.LENGTH_SHORT).show();
            }
           Personas personas = lista.get(posicionSelecionada);
            new AlertDialog.Builder(Activity_Seleccionar.this)
                    .setTitle("Eliminar Usuario")
                    .setMessage("¿Está seguro que desea eliminar a " + personas.getNombres() + "?")
                    .setPositiveButton("Si", (dialog, which)->{
                        SQLiteDatabase db = conexion.getReadableDatabase();
                        db.delete(PersonasDB.tablaPersonas, "id = ?", new String[]{String.valueOf(persona.getId())});
                        Toast.makeText(this, "Usuario elimiando correctamente", Toast.LENGTH_SHORT).show();
                        posicionSelecionada = -1;
                        ObtenerInfo();
                        ActualizarListaView();
                    })
                    .setNegativeButton("NO", null)
                    .show();

        });
    }


        void ObtenerInfo () {
            SQLiteDatabase db = conexion.getReadableDatabase();
            lista.clear();
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
            arregloLista = new ArrayList<>();
            for (Personas p : lista) {
                arregloLista.add(p.getId() + " | " + p.getNombres() + " " + p.getApellidos() +
                        " | " + p.getEdad() + " años | " + p.getCorreo() + " | " + p.getDireccion());
            }
        }

        void ActualizarListaView () {
            ArrayAdapter<String> adapter = new ArrayAdapter<>(
                    this, android.R.layout.simple_list_item_1, arregloLista
            );
            listaPersonas.setAdapter(adapter);
        }


    }





