package com.example.applicationsql;

import android.os.Bundle;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

import com.example.applicationsql.Conexion.Personas;
import com.example.applicationsql.Conexion.PersonasCRUD;
import com.example.applicationsql.Conexion.PersonasDB;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    EditText nombres, apellidos, edad, correo, direccion;
    private PersonasDB personasDB;
    private List<Personas> personasList;
    private PersonasCRUD personasCRUD;
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
        personasCRUD = new PersonasCRUD(this);
        findViewById(R.id.btnActualizarP).setOnClickListener(v -> {
            String nombre = nombres.getText().toString();
            String apellido = apellidos.getText().toString();
            String edadStr = edad.getText().toString();
            String correoP = correo.getText().toString();
            String direc = direccion.getText().toString();

            if (!nombre.isEmpty() && !apellido.isEmpty() && !edadStr.isEmpty() && !correoP.isEmpty() && !direc.isEmpty()) {
                int edad = Integer.parseInt(edadStr);
                Personas persona = new Personas(0, nombre, apellido, edad, correoP, direc);
                personasCRUD.insertarPersonas(persona);
                Toast.makeText(this, "Persona guardada", Toast.LENGTH_SHORT).show();
                finish();
            } else {
                Toast.makeText(this, "Llene todos los campos", Toast.LENGTH_SHORT).show();
            }
        });


//            if (nombres.getText().toString().isEmpty() || apellidos.getText().toString().isEmpty())
//                Toast.makeText(this, "Nombre y Apellidos son requeridos", Toast.LENGTH_SHORT).show();
//            else {
//                Personas p = new Personas(0,
//                        nombres.getText().toString(),
//                        apellidos.getText().toString(),
//                        Integer.parseInt(edad.getText().toString()),
//                        correo.getText().toString(),
//                        direccion.getText().toString());
//                personasCRUD.insertarPersonas(p);
//                Toast.makeText(this, "Persona guardada", Toast.LENGTH_SHORT).show();
//
//            }
//        });

//        View btnMostrar = findViewById(R.id.btnMostrar);
//        View btnEliminar = findViewById(R.id.btnEliminar);
//        View btnActualizar = findViewById(R.id.btnActualizar);
//
//        if (btnMostrar != null) {
//            btnMostrar.setOnClickListener(v -> mostrarPersonas());
//        }
//        if (btnEliminar != null) {
//            btnEliminar.setOnClickListener(v -> {
//                // Eliminar por ID (puedes hacer un diálogo para pedir el ID)
//                // Aquí como ejemplo eliminamos el primero de la lista si existe
//                List<Persona> lista = personasDAO.obtenerPersonas();
//                if (!lista.isEmpty()) {
//                    personasDAO.eliminarPersona(lista.get(0).getId());
//                    Toast.makeText(this, "Eliminado el primer registro", Toast.LENGTH_SHORT).show();
//                } else {
//                    Toast.makeText(this, "No hay registros para eliminar", Toast.LENGTH_SHORT).show();
//                }
//            });
//        }
//        if (btnActualizar != null) {
//            btnActualizar.setOnClickListener(v -> {
//                // Lanzar la actividad de actualización. Puedes pedir el ID a actualizar.
//                List<Persona> lista = personasDAO.obtenerPersonas();
//                if (!lista.isEmpty()) {
//                    Intent intent = new Intent(this, ActualizarPersonaActivity.class);
//                    intent.putExtra("id", lista.get(0).getId()); // Aquí se actualiza el primero como ejemplo
//                    startActivity(intent);
//                } else {
//                    Toast.makeText(this, "No hay registros para actualizar", Toast.LENGTH_SHORT).show();
//                }
//            });
//        }
//
//        // RecyclerView para mostrar la lista (puedes tenerlo en otro layout)
//        recyclerView = findViewById(R.id.recyclerViewPersonas);
//        if (recyclerView != null) {
//            recyclerView.setLayoutManager(new LinearLayoutManager(this));
//            mostrarPersonas();
//        }
//    }

//    private void limpiarCampo() {
//        nombres.setText("");
//        apellidos.setText("");
//        edad.setText("");
//        correo.setText("");
//        direccion.setText("");
 }


}