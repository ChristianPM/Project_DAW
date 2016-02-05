package com.mibanco.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;

public class ListaClientesActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_clientes);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
        String[] from = new String[] { "Nombre", "Direccion"};
        int[] to = new int[] { R.id.nombre, R.id.direccion };

        ArrayList<String[]> lista = new ArrayList<String[]>();

        String[] evento1 = { "JUAN PEREZ", "AV. PERU  156", "1" };


        lista.add(evento1);


        ArrayList<HashMap<String, String>> eventos = new ArrayList<HashMap<String, String>>();

        for (String[] evento : lista) {
            HashMap<String, String> datosEvento = new HashMap<String, String>();

            datosEvento.put("Nombre", evento[0]);
            datosEvento.put("Direccion", evento[1]);
            datosEvento.put("id", evento[2]);

            eventos.add(datosEvento);
        }

        SimpleAdapter listadoAdapter = new SimpleAdapter(this, eventos, R.layout.simple_adapter_fila, from, to);

        ListView lv = (ListView) findViewById(R.id.lista1);
        lv.setAdapter(listadoAdapter);


    }
    public void llamarARegistrarSeguimientoActivity(View v) {

        Intent act = new Intent(this, RegistrarSeguimientoActivity.class);

        startActivity(act);

    }




}


