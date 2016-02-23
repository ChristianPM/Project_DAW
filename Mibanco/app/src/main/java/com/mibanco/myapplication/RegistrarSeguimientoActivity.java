package com.mibanco.myapplication;

import android.content.Intent;
<<<<<<< HEAD
import android.os.AsyncTask;
=======
>>>>>>> origin/master
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
<<<<<<< HEAD
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.mibanco.global.ParamGlobal;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class RegistrarSeguimientoActivity extends AppCompatActivity {
    Button btnregistrarseguimiento;
    Spinner combo;
    ObtenerWebService hiloconexion;

    EditText etDocumento;
    String   strCondicion;
    EditText  etDescripcion;;
    String strDocumento ;
    String strUsuario ;

    String strDescripcion;


=======
import android.view.View;

public class RegistrarSeguimientoActivity extends AppCompatActivity {
>>>>>>> origin/master

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registrar_seguimiento);
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
<<<<<<< HEAD

        final String[] cursos = new String[] { "1-No desea prestamo",    "2-Para el proximo mes", "3-Ya obtuvo prestamo" };

        ArrayAdapter<String> adaptador = new ArrayAdapter<String>(this,    android.R.layout.simple_spinner_item  , cursos);
        adaptador.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        combo = (Spinner) findViewById(R.id.spinnerSeguimiento);
        combo.setAdapter(adaptador);



    }

        public void registrar( View v){

            etDocumento = (EditText) findViewById(R.id.editText2);
            etDescripcion  = (EditText) findViewById(R.id.editText5);


              strDocumento = etDocumento.getText().toString();
              strUsuario = ParamGlobal.CODIGO_USUARIO_SESION;
              strCondicion = combo.getSelectedItem().toString().substring(0, 1);
              strDescripcion = etDescripcion.getText().toString();

            System.out.println("documento---> " + strDocumento);
            System.out.println("usuario---> " + strUsuario);
            System.out.println("condicion---> " + strCondicion);
            System.out.println("descripcion---> " + strDescripcion);

            hiloconexion = new ObtenerWebService();
            String INSERT = ParamGlobal.URL_WEB_SERVICE + ParamGlobal.PAGE_SEGUIMIENTO;
            hiloconexion.execute(INSERT, "3", strDocumento, strUsuario, strCondicion, strDescripcion);   // Parámetros que recibe doInBack


        }




    public void VolverAListaClientesActivity(View v) {

        Intent act = new Intent(this, ListaClientesActivity.class);
        startActivity(act);

    }


public class ObtenerWebService extends AsyncTask<String, Void, String> {

    @Override
    protected String doInBackground(String... params) {

        String cadena = params[0];
        URL url = null; // Url de donde queremos obtener información
        String devuelve = "";


        if (params[1] == "3") {    // insert

            try {
                HttpURLConnection urlConn;

                DataOutputStream printout;
                DataInputStream input;
                url = new URL(cadena);
                urlConn = (HttpURLConnection) url.openConnection();
                urlConn.setDoInput(true);
                urlConn.setDoOutput(true);
                urlConn.setUseCaches(false);
                urlConn.setRequestProperty("Content-Type", "application/json");
                urlConn.setRequestProperty("Accept", "application/json");
                urlConn.connect();
                //Creo el Objeto JSON
                JSONObject jsonParam = new JSONObject();

                System.out.println("documento AsyncTask---> " + params[2]);
                System.out.println("usuario AsyncTask---> " + params[3]);
                System.out.println("condicion AsyncTask---> " + params[4]);
                System.out.println("descripcion AsyncTask---> " + params[5]);

                jsonParam.put("documento", params[2]);
                jsonParam.put("usuario", params[3]);
                jsonParam.put("condicion", params[4]);
                jsonParam.put("descripcion", params[5]);

                // Envio los parámetros post.
                OutputStream os = urlConn.getOutputStream();
                BufferedWriter writer = new BufferedWriter(
                        new OutputStreamWriter(os, "UTF-8"));
                writer.write(jsonParam.toString());
                writer.flush();
                writer.close();

                int respuesta = urlConn.getResponseCode();


                StringBuilder result = new StringBuilder();

                if (respuesta == HttpURLConnection.HTTP_OK) {

                    String line;
                    BufferedReader br = new BufferedReader(new InputStreamReader(urlConn.getInputStream()));
                    while ((line = br.readLine()) != null) {
                        result.append(line);
                        Log.i("mi  error", "[" + result + "]");
                        //response+=line;
                    }
                    Log.i("mi  error", "[" + result + "]");
                    //Creamos un objeto JSONObject para poder acceder a los atributos (campos) del objeto.
                    JSONObject respuestaJSON = new JSONObject(result.toString());   //Creo un JSONObject a partir del StringBuilder pasado a cadena
                    //Accedemos al vector de resultados

                    String resultJSON = respuestaJSON.getString("estado");   // estado es el nombre del campo en el JSON

                    if (resultJSON == "1") {      // hay un alumno que mostrar
                        devuelve = "Solicitud insertado correctamente";


                    } else if (resultJSON == "2") {
                        devuelve = "Solicitud no pudo insertarse";
                    }

                }

            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            } catch (JSONException e) {
                e.printStackTrace();
            }

            return devuelve;


        }


        return null;
    }
    @Override
    protected void onCancelled(String s) {
        super.onCancelled(s);
    }

    @Override
    protected void onPostExecute(String s) {

        //super.onPostExecute(s);

        Toast toast = Toast.makeText(getApplicationContext(), s , Toast.LENGTH_SHORT);
        toast.setGravity(Gravity.CENTER_VERTICAL, 0, 0);
        toast.show();

    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
    }

    @Override
    protected void onProgressUpdate(Void... values) {
        super.onProgressUpdate(values);
    }


}
}
=======
    }
    public void VolverAListaClientesActivity(View v) {

        Intent act = new Intent(this, ListaClientesActivity.class);

        startActivity(act);

    }
    //VolverAListaClientesActivity
}
>>>>>>> origin/master
