package co.edu.javeriana.myapplication_php;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.StrictMode;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    private Button guardar, consultar;
    private EditText nombre,telefono,direccion;
    private TextView info;
    private String text = "http://192.168.155.175/prueba/create.php"; // 10.0.2.2 VM
    private RequestQueue requestQueue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        nombre = (EditText) findViewById(R.id.editTextName);
        telefono = (EditText) findViewById(R.id.editTextPhone);
        direccion = (EditText) findViewById(R.id.editTextAddress);

        info = (TextView) findViewById(R.id.textViewInfo);

        guardar = (Button) findViewById(R.id.buttonEnviar);
        consultar = (Button) findViewById(R.id.buttonConsultar);

        requestQueue = Volley.newRequestQueue(getApplicationContext());
        consultar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplication(),Main2Activity.class));
            }
        });

        guardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                StringRequest stringRequest = new StringRequest(Request.Method.POST,
                        text, new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                        info.setText("Se guardo correctamente");
                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        info.setText("Error guardando");
                    }
                }){
                    @Override
                    protected Map<String, String> getParams() throws AuthFailureError {
                        Map<String,String> parameters = new HashMap<String, String>();
                        parameters.put("nombre",nombre.getText().toString());
                        parameters.put("telefono",telefono.getText().toString());
                        parameters.put("direccion",direccion.getText().toString());

                        return parameters;
                    }
                };
                requestQueue.add(stringRequest);
            }
        });

    }

}
