package com.mibanco.myapplication;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

<<<<<<< HEAD
import com.mibanco.bean.Usuario;
import com.mibanco.dao.UsuarioDao;

=======
>>>>>>> origin/master
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class BusquedaActivity extends AppCompatActivity {

    String IP = "http://upcmoviles2016.esy.es";
    // Rutas de los Web Services
    String GET_BY_ID = IP + "/obtener_cliente_x_direccion_o_correo.php";
    ObtenerWebService hiloconexion;
    EditText nombre;
    EditText distrito;
    TextView resultado;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_busqueda);
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
        nombre = (EditText)findViewById(R.id.eNombre );
=======
        nombre = (EditText)findViewById(R.id.eNombre);
>>>>>>> origin/master
        distrito = (EditText)findViewById(R.id.eDistrito);
        resultado = (TextView)findViewById(R.id.resultado);


    }
    public void pasaraListaClientesActivity(View v) {
        Intent act = new Intent(this, ListaClientesActivity.class);
        startActivity(act);
    }

    public void VolverAInicioActivity(View v) {
        Intent act = new Intent(this, InicioActivity.class);
        startActivity(act);
    }

    public void Buscar(View v) {
        hiloconexion = new ObtenerWebService();
<<<<<<< HEAD
        UsuarioDao dao = new UsuarioDao(getBaseContext());
        Usuario beanUsuario;
        String usuario="";
        try {
            beanUsuario = dao.obtener();
            usuario = beanUsuario.getCodigo().toString();
        }catch (Exception e){
            e.printStackTrace();
        }


        String cadenallamada = GET_BY_ID + "?nombre=" + nombre.getText().toString()+"&distrito=" +distrito.getText().toString()+ "&usuario="+usuario;
=======
        String cadenallamada = GET_BY_ID + "?nombre=" + nombre.getText().toString()+"&distrito=" +distrito.getText().toString();
>>>>>>> origin/master
        hiloconexion.execute(cadenallamada,"2");   // Parámetros que recibe doInBackground
    }

    public class ObtenerWebService extends AsyncTask<String,Void,String> {

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
<<<<<<< HEAD
                        System.out.println("resultado busqueda" + resultJSON);
                        if (resultJSON=="1"){      // hay un alumno que mostrar
                            JSONArray alumnosJSON = respuestaJSON.getJSONArray("clientes");   // estado es el nombre del campo en el JSON
                            for(int i=0;i<alumnosJSON.length();i++) {
                                devuelve = devuelve + alumnosJSON.getJSONObject(i).getString("DOCUMENTO") + " " +
                                        alumnosJSON.getJSONObject(i).getString("NOMBRE") + " " +
                                        alumnosJSON.getJSONObject(i).getString("DISTRITO") + " " +
                                        alumnosJSON.getJSONObject(i).getString("DIRECCION") + "\n";
=======
                        if (resultJSON=="1"){      // hay un alumno que mostrar
                            JSONArray alumnosJSON = respuestaJSON.getJSONArray("clientes");   // estado es el nombre del campo en el JSON
                            for(int i=0;i<alumnosJSON.length();i++) {
                                devuelve = devuelve + alumnosJSON.getJSONObject(i).getString("codigo") + " " +
                                        alumnosJSON.getJSONObject(i).getString("nombre") + " " +
                                        alumnosJSON.getJSONObject(i).getString("apellido_paterno") + " " +
                                        alumnosJSON.getJSONObject(i).getString("distrito") + "\n";
>>>>>>> origin/master
                             /*   devuelve = devuelve + respuestaJSON.getJSONObject("clientes").getString("codigo") + " " +
                                        respuestaJSON.getJSONObject("clientes").getString("nombre") + " " +
                                        respuestaJSON.getJSONObject("clientes").getString("apellido_paterno");*/
                            }
                        }
                        else if (resultJSON=="2"){
                            devuelve = "No hay clientes";
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
           resultado.setText(s);
            //super.onPostExecute(s);
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
