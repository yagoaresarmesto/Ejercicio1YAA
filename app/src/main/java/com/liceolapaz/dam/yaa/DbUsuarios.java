package com.liceolapaz.dam.yaa;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import androidx.annotation.Nullable;

import java.util.ArrayList;

public class DbUsuarios extends DbHelper {

    Context context;


    public DbUsuarios(@Nullable Context context) {
        super(context);
        this.context = context;
    }

    public long insertarUsuarios(String email, String contraseña, String idioma, int edad, String nombre) {

        long insertar = 0;
        try {
            DbHelper dbHelper = new DbHelper(context);
            SQLiteDatabase db = dbHelper.getWritableDatabase();


            ContentValues values = new ContentValues();
            values.put("email", email);
            values.put("contraseña", contraseña);
            values.put("idioma", idioma);
            values.put("edad", edad);
            values.put("nombre", nombre);

            insertar = db.insert(TABLE_USUARIOS, null, values);
        } catch (Exception ex) {
            ex.toString();
        }
        return insertar;
    }

    public ArrayList<Usuarios> mostarUsuarios() {
        DbHelper dbHelper = new DbHelper(context);
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        ArrayList<Usuarios> listaUsuarios = new ArrayList<>();
        Usuarios usuario = null;
        Cursor cursorUsuarios = null;

        cursorUsuarios = db.rawQuery("SELECT * FROM usuarios ", null);

        if (cursorUsuarios.moveToFirst()) {

            do {
                usuario = new Usuarios();
                usuario.setEmail(cursorUsuarios.getString(0));
                usuario.setContraseña(cursorUsuarios.getString(1));
                usuario.setIdioma(cursorUsuarios.getString(2));
                usuario.setEdad(cursorUsuarios.getString(3));
                usuario.setNombre(cursorUsuarios.getString(4));
                listaUsuarios.add(usuario);
            } while (cursorUsuarios.moveToNext());
        }
        cursorUsuarios.close();

        return listaUsuarios;

    }
}