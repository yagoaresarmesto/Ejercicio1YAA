package com.liceolapaz.dam.yaa;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

public class Nuevo extends Activity {

    EditText txtEmail, txtContraseña, txtEdad, txtNombre;
    Spinner spinnerIdioma;
    Button btnGuardar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nuevo);

        txtEmail = findViewById(R.id.txt_correo);
        txtContraseña = findViewById(R.id.txt_contraseña);
        txtEdad = findViewById(R.id.txt_edad);
        txtNombre = findViewById(R.id.txt_nombre);
        btnGuardar = findViewById(R.id.btn_guardar);
        spinnerIdioma = (Spinner) findViewById(R.id.spinner_idioma);

        String[] opciones = {"Español", "Gallego", "Inglés"};
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, opciones);
        spinnerIdioma.setAdapter(adapter);

        btnGuardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DbUsuarios dbUsuarios = new DbUsuarios(Nuevo.this);
                long insertar = dbUsuarios.insertarUsuarios(txtEmail.getText().toString(), txtContraseña.getText().toString(),
                        spinnerIdioma.getSelectedItem().toString(), txtEdad.getText().length(), txtNombre.getText().toString());
                limpiarCampos();
                if (insertar > 0) {
                    Toast.makeText(Nuevo.this, "SE HA GUARDADO EL REGISTRO CORRECTAMENTE", Toast.LENGTH_SHORT).show();

                } else {
                    Toast.makeText(Nuevo.this, "ERROR AL GUARDAR EL REGISTRO", Toast.LENGTH_SHORT).show();
                }
            }
        });


    }

    //Método para limpiar campos

    private void limpiarCampos() {
        txtEmail.setText("");
        txtContraseña.setText("");
        txtEdad.setText("");
        txtNombre.setText("");
    }
}