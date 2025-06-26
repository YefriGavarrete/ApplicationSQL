package com.example.applicationsql;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.applicationsql.Conexion.Personas;
import com.example.applicationsql.Conexion.PersonasCRUD;
import com.example.applicationsql.Conexion.PersonasDB;

import java.util.ArrayList;
import java.util.List;

public class Activity_Actualizar extends AppCompatActivity {
    EditText nombres, apellidos, edad, correo, direccion;

    Button btnActualizar, btnEliminar;
    private PersonasDB personasDB;
    private int personaId;
    private List<Personas> personasList;
    private PersonasCRUD personasCRUD;


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
        btnActualizar = (Button) findViewById(R.id.btnActualizarP);
        btnEliminar = (Button) findViewById(R.id.btnEliminarP);

        personasCRUD = new PersonasCRUD(this);

        personaId = getIntent().getIntExtra("persona_id", -1);
        if (personaId != -1) {
            Personas persona = personasCRUD.obtenerPersonaPorId(personaId);
            if (persona != null) {
                nombres.setText(persona.getNombres());
                apellidos.setText(persona.getApellidos());
                edad.setText(String.valueOf(persona.getEdad()));
                correo.setText(persona.getCorreo());
                direccion.setText(persona.getDireccion());
            }
        }

        btnActualizar.setOnClickListener(v -> {
            String nombre = nombres.getText().toString();
            String apellidosP = apellidos.getText().toString();
            int edadP = Integer.parseInt(edad.getText().toString());
            String correoP = correo.getText().toString();
            String direccionP = direccion.getText().toString();
            Personas persona = new Personas(personaId, nombre, apellidosP, edadP, correoP, direccionP);
            personasCRUD.actualizarPersona(persona);
            Toast.makeText(this, "Persona actualizada", Toast.LENGTH_SHORT).show();
            finish();
        });

        btnEliminar.setOnClickListener(v -> {
            Personas persona = new Personas(personaId, "", "", 0, "", "");
            personasCRUD.eliminarPersonas(persona);
            Toast.makeText(this, "Persona eliminada", Toast.LENGTH_SHORT).show();
            finish();
        });

    }
}