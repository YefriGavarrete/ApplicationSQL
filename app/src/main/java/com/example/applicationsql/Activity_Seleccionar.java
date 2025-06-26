package com.example.applicationsql;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

import com.example.applicationsql.Conexion.Personas;
import com.example.applicationsql.Conexion.PersonasCRUD;

import java.util.ArrayList;
import java.util.List;

public class Activity_Seleccionar extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        PersonasCRUD personasCRUD;
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_seleccionar);

        ListView listView = findViewById(R.id.listView);
        personasCRUD = new PersonasCRUD(this);

        List<Personas> personasList = personasCRUD.obtenerPersonas();
        ArrayList<String> personasString = new ArrayList<>();

        for (Personas p : personasList) {
            personasString.add(p.getId() + ": " + p.getNombres() + " " + p.getApellidos());
        }

        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, personasString);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener((parent, view, position, id) -> {
            int personaId = personasList.get(position).getId();
            Intent intent = new Intent(this, Activity_Actualizar.class);
            intent.putExtra("persona_id", personaId);
            startActivity(intent);
        });
    }
}