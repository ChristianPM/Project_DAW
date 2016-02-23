package com.mibanco.bean;

import com.mibanco.global.ParamGlobal;

/**
 * Created by usuario on 31/01/2016.
 */
public class ManipulationDLLBean {


    public ManipulationDLLBean(){

    }

    public String getNameDatabase(){
     return ParamGlobal.BD_NAME;
    }

    public String getStanmentCreateCliente(){
        return " ";
    }

    public String getStanmentCreateCampana(){
        return " ";
    }

    public String getStanmentCreateSeguimiento(){
        return " ";
    }

    public String getStanmentCreateSolicitud(){
        return " ";
    }

    public String getStanmentCreateUsuario(){
        return "CREATE TABLE IF NOT EXISTS usuario (codigo TEXT NOT NULL, nombre TEXT NOT NULL) ";
    }


    public String getStanmentDropCliente(){
        return " ";
    }

    public String getStanmentDropCampana(){
        return " ";
    }

    public String getStanmentDropSeguimiento(){
        return " ";
    }

    public String getStanmentDropSolicitud(){
        return " ";
    }

    public String getStanmentDropUsuario(){
        return "DROP TABLE IF EXISTS usuario ";
    }
}
