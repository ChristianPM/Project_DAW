package com.mibanco.dao;

/**
 * Created by usuario on 22/02/2016.
 */
public class DAOExcepcion extends Exception {

    private static final long serialVersionUID = 1L;

    public DAOExcepcion() {
        super();
        // TODO Auto-generated constructor stub
    }

    public DAOExcepcion(String detailMessage, Throwable throwable) {
        super(detailMessage, throwable);
        // TODO Auto-generated constructor stub
    }

    public DAOExcepcion(String detailMessage) {
        super(detailMessage);
        // TODO Auto-generated constructor stub
    }

    public DAOExcepcion(Throwable throwable) {
        super(throwable);
        // TODO Auto-generated constructor stub
    }
}
