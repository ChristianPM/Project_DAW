package com.mibanco.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.mibanco.bean.ManipulationDLLBean;
import com.mibanco.global.ParamGlobal;

/**
 * Created by usuario on 31/01/2016.
 */
public class DbHelper extends SQLiteOpenHelper {

    ManipulationDLLBean manipulation = new ManipulationDLLBean();

    public DbHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    public DbHelper(Context context) {
        // null porque se va a usar el SQLiteCursor
        super(context, ParamGlobal.BD_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        String sql="";
        sql = manipulation.getStanmentCreateUsuario() ;
        db.execSQL(sql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {


        db.execSQL(manipulation.getStanmentDropUsuario());
        onCreate(db);
    }



}
