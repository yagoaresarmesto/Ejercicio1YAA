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

        long id = 0;
        try {
            DbHelper dbHelper = new DbHelper(context);
            SQLiteDatabase db = dbHelper.getWritableDatabase();


            ContentValues values = new ContentValues();
            values.put("email", email);
            values.put("contraseña", contraseña);
            values.put("idioma", idioma);
            values.put("edad", edad);
            values.put("nombre", nombre);

            id = db.insert(TABLE_USUARIOS, null, values);
        } catch (Exception ex) {
            ex.toString();
        }
        return id;
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
                usuario.setId(cursorUsuarios.getInt(0));
                usuario.setEmail(cursorUsuarios.getString(1));
                usuario.setContraseña(cursorUsuarios.getString(2));
                usuario.setIdioma(cursorUsuarios.getString(3));
                usuario.setEdad(cursorUsuarios.getString(4));
                usuario.setNombre(cursorUsuarios.getString(5));
                listaUsuarios.add(usuario);
            } while (cursorUsuarios.moveToNext());
        }
        cursorUsuarios.close();

        return listaUsuarios;

    }


    public Usuarios verUsuario(int id) {

        DbHelper dbHelper = new DbHelper(context);
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        Usuarios usuario = null;
        Cursor cursorUsuarios;

        cursorUsuarios = db.rawQuery("SELECT * FROM " + TABLE_USUARIOS + " WHERE id = " + id, null);

        if (cursorUsuarios.moveToFirst()) {
            usuario = new Usuarios();
            usuario.setId(cursorUsuarios.getInt(0));
            usuario.setEmail(cursorUsuarios.getString(1));
            usuario.setContraseña(cursorUsuarios.getString(2));
            usuario.setIdioma(cursorUsuarios.getString(3));
            usuario.setEdad(cursorUsuarios.getString(4));
            usuario.setNombre(cursorUsuarios.getString(5));

        }
        cursorUsuarios.close();

        return usuario;

    }

    public boolean editarUsuario(int id, String email, String contraseña, String idioma, int edad, String nombre) {

        boolean correcto = false;

        DbHelper dbHelper = new DbHelper(context);
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        try {
            db.execSQL("UPDATE " + TABLE_USUARIOS + " SET email = '" + email + "', contraseña = '" + contraseña + "', idioma = '" + idioma + "', edad = '" + edad + "', nombre = '" + nombre + "' WHERE id='" + id + "' ");
            correcto = true;

        } catch (Exception ex) {
            ex.toString();
            correcto = false;
        } finally {
            db.close();
        }
        return correcto;
    }

    public boolean eliminarUsuario(int id) {

        boolean correcto = false;

        DbHelper dbHelper = new DbHelper(context);
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        try {
            db.execSQL("DELETE FROM " + TABLE_USUARIOS + " WHERE id = '" + id + "'");
            correcto = true;
        } catch (Exception ex) {
            ex.toString();
            correcto = false;
        } finally {
            db.close();
        }

        return correcto;
    }
}