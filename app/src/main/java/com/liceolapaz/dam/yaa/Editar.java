package com.liceolapaz.dam.yaa;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class Editar extends AppCompatActivity {

    EditText txtCorreo, txtContraseña, txtIdioma, txtEdad, txtNombre;
    Button btnGuardar;
    Button btnEditar;
    boolean correcto = false;
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

        final DbUsuarios dbUsuarios = new DbUsuarios(Editar.this);
        usuario = dbUsuarios.verUsuario(id);

        if (usuario != null) {
            txtCorreo.setText(usuario.getEmail());
            txtContraseña.setText(usuario.getContraseña());
            txtIdioma.setText(usuario.getIdioma());
            txtEdad.setText(usuario.getEdad());
            txtNombre.setText(usuario.getNombre());


        }
        btnGuardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!txtCorreo.getText().toString().equals("") && !txtContraseña.getText().toString().equals("") && !txtIdioma.getText().toString().equals("")) {
                   correcto = dbUsuarios.editarUsuario(id,
                            txtCorreo.getText().toString(),
                            txtContraseña.getText().toString(),
                            txtIdioma.getText().toString(),
                            Integer.parseInt(txtEdad.getText().toString()),
                            txtNombre.getText().toString());

                    if (correcto) {
                        Toast.makeText(Editar.this, "Usuario modificado", Toast.LENGTH_SHORT).show();
                        irLista();
                    } else {
                        Toast.makeText(Editar.this, "Error al modificar", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(Editar.this, "Debe llenar todos los campos", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
    private void irLista() {
        Intent intent = new Intent(this, Mostrar.class);
        intent.putExtra("ID", id);
        startActivity(intent);
    }

}
