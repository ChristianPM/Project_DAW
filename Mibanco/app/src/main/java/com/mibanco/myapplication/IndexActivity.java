package com.mibanco.myapplication;

import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.Toast;

import com.mibanco.dao.DAOExcepcion;
import com.mibanco.dao.UsuarioDao;
import com.mibanco.global.ParamGlobal;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class IndexActivity extends AppCompatActivity {
    String GET_BY_ID = ParamGlobal.URL_WEB_SERVICE + ParamGlobal.PAGE_LOGIN;
    ObtenerWebServiceLogin hiloconexion;

    EditText etUsuario;
    EditText etClave ;
    String codigo;
    String nombre;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_index);
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


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_index, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }










    public void loguear(View v){

        hiloconexion = new ObtenerWebServiceLogin(this);
        EditText etUsuario = (EditText) findViewById(R.id.txtUsuarioLog);
        EditText etClave = (EditText) findViewById(R.id.txtClaveLog);

        System.out.println("usuario : "  + etUsuario.getText().toString());
        System.out.println("clave : " + etClave.getText().toString());

        UsuarioDao dao = new UsuarioDao(getBaseContext());
        try {
            dao.eliminarTodos();
            dao.insertar(etUsuario.getText().toString(), etClave.getText().toString());
            System.out.print("se registro el usuario " + etUsuario.getText().toString());

        } catch (DAOExcepcion e) {
            Log.i("EscrituraBDActivity", "====> " + e.getMessage());
        }


        String cadenallamada = GET_BY_ID + "?codigo=" + etUsuario.getText().toString()+"&clave=" +etClave.getText().toString();
        hiloconexion.execute(cadenallamada, "2");   // Parámetros que recibe doInBackground


    }



    public class ObtenerWebServiceLogin extends AsyncTask<String,Void,String> {

        private final Activity activity;

        public ObtenerWebServiceLogin(Activity activity) {
            this.activity = activity;
        }


        @Override
        protected String doInBackground(String... params) {

            String cadena = params[0];
            URL url = null; // Url de donde queremos obtener información
            String devuelve ="";
            if(params[1]=="2"){    // consulta por id

                try {
                    url = new URL(cadena);
                    Log.i("url", cadena);
                    HttpURLConnection connection = (HttpURLConnection) url.openConnection(); //Abrir la conexión
                    connection.setRequestProperty("User-Agent", "Mozilla/5.0" +
                            " (Linux; Android 1.5; es-ES) Ejemplo HTTP");
                    int respuesta = connection.getResponseCode();
                    StringBuilder result = new StringBuilder();
                    if (respuesta == HttpURLConnection.HTTP_OK){
                        InputStream in = new BufferedInputStream(connection.getInputStream());  // preparo la cadena de entrada
                        BufferedReader reader = new BufferedReader(new InputStreamReader(in));  // la introduzco en un BufferedReader
                        String line;
                        while ((line = reader.readLine()) != null) {
                            result.append(line);        // Paso toda la entrada al StringBuilder
                            Log.i("mi  error en linea", line);
                            Log.i("mi  error", "[" + result + "]");
                        }
                        //Creamos un objeto JSONObject para poder acceder a los atributos (campos) del objeto.
                        JSONObject respuestaJSON = new JSONObject(result.toString());   //Creo un JSONObject a partir del StringBuilder pasado a cadena
                        //Accedemos al vector de resultados

                        String resultJSON = respuestaJSON.getString("estado");   // estado es el nombre del campo en el JSON
                        System.out.print("ESTADO index: " + resultJSON);

                        if (resultJSON=="1"){      // hay un alumno que mostrar
                            JSONArray usuarioJSON = respuestaJSON.getJSONArray("usuario");   // estado es el nombre del campo en el JSON

                            for(int i=0;i<usuarioJSON.length();i++) {
                                devuelve = devuelve + usuarioJSON.getJSONObject(i).getString("NOMBRE") ;
                            }

                        }
                        else if (resultJSON=="2"){
                            devuelve = "Usuario o contraseña incorrecta";
                        }
                        System.out.print("retorno index : " + devuelve);
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

            Intent i = new Intent(getApplicationContext(), InicioActivity.class);
            startActivity(i);
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

