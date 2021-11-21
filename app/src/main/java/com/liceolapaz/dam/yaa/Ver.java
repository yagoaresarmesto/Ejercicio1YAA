package com.liceolapaz.dam.yaa;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class Ver extends AppCompatActivity {

    EditText txtCorreo, txtContraseña, txtIdioma, txtEdad, txtNombre;
    Button btnGuardar;
    Button btnEditar;
    Button btnEliminar;

    Usuarios usuario;
    int id = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ver);

        txtCorreo = findViewById(R.id.txt_email);
        txtContraseña = findViewById(R.id.txt_password);
        txtIdioma = findViewById(R.id.txt_language);
        txtEdad = findViewById(R.id.txt_age);
        txtNombre = findViewById(R.id.txt_name);
        btnGuardar = findViewById(R.id.btn_guardar2);
        btnEditar = findViewById(R.id.btn_editar);
        btnEliminar = findViewById(R.id.btn_eliminar);

        if (savedInstanceState == null) {
            Bundle extras = getIntent().getExtras();
            if (extras == null) {
                id = Integer.parseInt(null);
            } else {
                id = extras.getInt("ID");
            }
        } else {
            id = (int) savedInstanceState.getSerializable("ID");
        }

        final DbUsuarios dbUsuarios = new DbUsuarios(Ver.this);
        usuario = dbUsuarios.verUsuario(id);

        if (usuario != null) {
            txtCorreo.setText(usuario.getEmail());
            txtContraseña.setText(usuario.getContraseña());
            txtIdioma.setText(usuario.getIdioma());
            txtEdad.setText(usuario.getEdad());
            txtNombre.setText(usuario.getNombre());
            btnGuardar.setVisibility(View.INVISIBLE);

            txtCorreo.setInputType(InputType.TYPE_NULL);
            txtContraseña.setInputType(InputType.TYPE_NULL);
            txtIdioma.setInputType(InputType.TYPE_NULL);
            txtEdad.setInputType(InputType.TYPE_NULL);
            txtNombre.setInputType(InputType.TYPE_NULL);
        }
        btnEditar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Ver.this, Editar.class);
                intent.putExtra("ID", id);
                startActivity(intent);
            }
        });
        btnEliminar.setOnClickListener((new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(Ver.this);
                builder.setMessage("¿Desea eliminar este usuario?")
                        .setPositiveButton("SI", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {

                                if (dbUsuarios.eliminarUsuario(id)) {
                                    volverlista();
                                }
                            }
                        })
                        .setNegativeButton("NO", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {

                            }
                        }).show();
            }
        }));
    }

    private void volverlista() {
        Intent intent = new Intent(this, Mostrar.class);
        startActivity(intent);
    }
}
