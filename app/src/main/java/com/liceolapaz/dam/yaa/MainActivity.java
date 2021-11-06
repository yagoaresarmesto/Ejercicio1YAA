package com.liceolapaz.dam.yaa;



import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends Activity {

    //Declaración de variables
    EditText usuario, contraseña;
    Button btn_login;
    int contador = 3; //Contador de intentos

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Relacionar con los id de los campos y botón
        usuario = findViewById(R.id.usuario);
        contraseña = findViewById(R.id.contraseña);
        btn_login = findViewById(R.id.btn_login);

        //Cuando pulsas Login
        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (usuario.getText().toString().equals("admin") && contraseña.getText().toString().equals("liceo")) { //Cuando se cumplan estas condiciones
                    //Paso a la siguiente Activity
                    Intent intent = new Intent(MainActivity.this, Mostrar.class );
                    startActivity(intent);

                } else {
                    contador--;
                    Toast.makeText(MainActivity.this, "Usuario y/o contraseña incorrectos, te queda " +contador+ " intentos", Toast.LENGTH_LONG).show(); //Cuando no se cumplan

                    if (contador == 0) { //Cuando el contador llege a 0:
                        System.exit(0);//Se sale de la aplicación
                    }

                }

            }
        });

    }

}
