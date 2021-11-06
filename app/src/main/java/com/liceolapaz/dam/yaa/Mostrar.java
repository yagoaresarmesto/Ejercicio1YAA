package com.liceolapaz.dam.yaa;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class Mostrar extends AppCompatActivity {

    RecyclerView listaUsuarios;
    ArrayList<Usuarios> listaArrayUsuarios;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.mostrar);
        listaUsuarios = findViewById(R.id.listaUsuarios);
        listaUsuarios.setLayoutManager(new LinearLayoutManager(this));

        DbUsuarios dbUsuarios = new DbUsuarios(Mostrar.this);
        listaArrayUsuarios = new ArrayList<>();

        ListaUsuariosAdapter adapter = new ListaUsuariosAdapter(dbUsuarios.mostarUsuarios());
        listaUsuarios.setAdapter(adapter);
    }


    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_principal, menu);
        return true;
    }

    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {

            case R.id.menuNuevo:
                nuevoRegistro();
                ;
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void nuevoRegistro() {
        Intent intent = new Intent(Mostrar.this, Nuevo.class);
        startActivity(intent);
    }


}