package com.liceolapaz.dam.yaa;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DbHelper extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NOMBRE = "datos.db";
    private static final String TABLE_USUARIOS = "usuarios";

    public DbHelper(@Nullable Context context) {
        super(context, DATABASE_NOMBRE, null, DATABASE_VERSION);
    }

    @Override //Método creación de la tabla
    public void onCreate(SQLiteDatabase BaseDeDatos) {
        BaseDeDatos.execSQL("CREATE TABLE " + TABLE_USUARIOS +
                "(email text PRIMARY KEY," +
                " contraseña TEXT," +
                " idioma TEXT," +
                " edad INT," +
                " nombre TEXT)");


    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE " + TABLE_USUARIOS);
        onCreate(sqLiteDatabase);
    }
}
