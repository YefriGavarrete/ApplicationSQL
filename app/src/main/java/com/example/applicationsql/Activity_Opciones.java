package com.example.applicationsql;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class Activity_Opciones extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_opciones);
        Button btnInsertar, btnActualizar, btnEliminar, btnSeleccionar;

        btnInsertar = (Button) findViewById(R.id.btnInsertar);
        btnActualizar= (Button) findViewById(R.id.btnActualizar);
        btnEliminar = (Button) findViewById(R.id.btnEliminar);
        btnSeleccionar = (Button) findViewById(R.id.btnSeleccionar);


        btnInsertar.setOnClickListener(v -> startActivity(new Intent(this, MainActivity.class)));
        btnActualizar.setOnClickListener(v -> startActivity(new Intent(this, Activity_Actualizar.class)));
        btnEliminar.setOnClickListener(v -> startActivity(new Intent(this, Activity_Actualizar.class)));
        btnSeleccionar.setOnClickListener(v -> startActivity(new Intent(this, Activity_Seleccionar.class)));
    }
}