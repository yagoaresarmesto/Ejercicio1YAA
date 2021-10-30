package com.liceolapaz.dam.yaa;


import android.app.Activity;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;


public class MostrarUsuarios extends Activity {

    //Declaración de variables

    Button btn_añadir;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mostrar_usuarios);

        //Relacionar con los id de los campos y botón

        btn_añadir= findViewById(R.id.btn_añadir);


        //Cuando pulsas Login
        btn_añadir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                DbHelper dbHelper = new DbHelper(MostrarUsuarios.this);
                SQLiteDatabase db= dbHelper.getWritableDatabase();
                if(db != null){
                   Toast.makeText(MostrarUsuarios.this, "Base de datos creada", Toast.LENGTH_LONG).show();
                }else{
                    Toast.makeText(MostrarUsuarios.this, "error", Toast.LENGTH_LONG).show();
                }



            }
        });

        }
}