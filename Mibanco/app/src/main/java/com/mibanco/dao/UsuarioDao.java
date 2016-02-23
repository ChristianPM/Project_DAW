package com.mibanco.dao;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.mibanco.bean.Usuario;
import com.mibanco.db.DbHelper;

/**
 * Created by usuario on 22/02/2016.
 */
public class UsuarioDao {

    private DbHelper _dbHelper;

    public UsuarioDao(Context c) {
        _dbHelper = new DbHelper(c);
    }

    public void insertar(String  codigo, String nombre) throws DAOExcepcion {
        Log.i("UsuarioDAO", "insert()");
        SQLiteDatabase db = _dbHelper.getWritableDatabase();
        try {
            String[] args = new String[]{codigo, nombre};
            db.execSQL("INSERT INTO usuario(codigo, nombre) VALUES(?,?)", args);
            Log.i("CuentaDAO", "Se insertó");
        } catch (Exception e) {
            throw new DAOExcepcion("UsuarioDAO: Error al insertar: " + e.getMessage());
        } finally {
            if (db != null) {
                db.close();
            }
        }
    }

    public Usuario obtener() throws DAOExcepcion {
        Log.i("CuentaDAO", "obtener()");
        SQLiteDatabase db = _dbHelper.getReadableDatabase();
        Usuario modelo = new Usuario();
        try {
            Cursor c = db.rawQuery("select codigo,nombre from usuario", null);
            if (c.getCount() > 0) {
                c.moveToFirst();
                do {
                    String codigo = c.getString(c.getColumnIndex("codigo"));
                    String correo = c.getString(c.getColumnIndex("nombre"));

                    modelo.setCodigo(codigo);
                    modelo.setNombre(correo);

                } while (c.moveToNext());
            }
            c.close();
        } catch (Exception e) {
            throw new DAOExcepcion("CuentaDAO: Error al obtener: " + e.getMessage());
        } finally {
            if (db != null) {
                db.close();
            }
        }
        return modelo;
    }

    public void eliminar(String id) throws DAOExcepcion {
        Log.i("UsuarioDAO", "eliminar()");
        SQLiteDatabase db = _dbHelper.getWritableDatabase();

        try {
            String[] args = new String[]{String.valueOf(id)};
            db.execSQL("DELETE FROM usuario WHERE codigo=?", args);
        } catch (Exception e) {
            throw new DAOExcepcion("UsuarioDAO: Error al eliminar: " + e.getMessage());
        } finally {
            if (db != null) {
                db.close();
            }
        }
    }

    public void eliminarTodos() throws DAOExcepcion {
        Log.i("CuentaDAO", "eliminarTodos()");
        SQLiteDatabase db = _dbHelper.getWritableDatabase();

        try {
            db.execSQL("DELETE FROM usuario");
        } catch (Exception e) {
            throw new DAOExcepcion("usuarioDAO: Error al eliminar todos: " + e.getMessage());
        } finally {
            if (db != null) {
                db.close();
            }
        }
    }


}
