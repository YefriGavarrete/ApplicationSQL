package com.example.applicationsql;

import android.content.ContentValues;
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

public class MainActivity extends AppCompatActivity {

    EditText nombres, apellidos, edad, correo, direccion;
    Button btnSalvar;
    private PersonasDB personasDB;
    private List<Personas> personasList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        nombres = (EditText) findViewById(R.id.nombre);
        apellidos = (EditText) findViewById(R.id.apellidos);
        edad = (EditText) findViewById(R.id.edad);
        correo = (EditText) findViewById(R.id.correo);
        direccion = (EditText) findViewById(R.id.direccion);
        btnSalvar = (Button) findViewById(R.id.btnSalvar);


        btnSalvar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AgregarPersona();
            }
        });
    }
        private void AgregarPersona() {
            sqliteConexion conexion = new sqliteConexion(this, PersonasDB.NameDB, null, 1);
            SQLiteDatabase db = conexion.getWritableDatabase();

            ContentValues values = new ContentValues();
            values.put(PersonasDB.nombre, nombres.getText().toString());
            values.put(PersonasDB.apellidos, apellidos.getText().toString());
            values.put(PersonasDB.edad, Integer.parseInt(edad.getText().toString()));
            values.put(PersonasDB.correo, correo.getText().toString());
            values.put(PersonasDB.direccion, direccion.getText().toString());

            Long result = db.insert(PersonasDB.tablaPersonas, PersonasDB.id, values);
            Toast.makeText(getApplicationContext(), "Persona ingresada con ID: " + result, Toast.LENGTH_LONG).show();

            db.close();
            ClearFields();
            finish();
        }
        private void ClearFields () {
            nombres.setText("");
            apellidos.setText("");
            edad.setText("");
            correo.setText("");
            direccion.setText("");
        }
    }
