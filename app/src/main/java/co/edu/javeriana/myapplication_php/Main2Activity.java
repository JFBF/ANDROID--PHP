package co.edu.javeriana.myapplication_php;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class Main2Activity extends AppCompatActivity {

    private String text = "http://192.168.155.175/prueba/getAll.php";// 10.0.2.2 VM
    private ListView lista;
    private TextView info;
    private ArrayList<String> arreglo = new ArrayList<>();
    private ArrayAdapter<String>adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        info = (TextView) findViewById(R.id.textView);
        adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1,arreglo);
        lista = (ListView) findViewById(R.id.Lista);



        RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST,
                text, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try{
                    JSONArray personas = response.getJSONArray("personas");
                    for(int i = 0; i < personas.length(); i++){
                        JSONObject persona = personas.getJSONObject(i);
                        String valor ="Nombre: "+persona.getString("nombre")+"\n"+
                                "Teléfono: "+persona.getString("telefono")+"\n"+
                                "Dirección: "+persona.getString("direccion");
                        arreglo.add(valor);
                    }
                    lista.setAdapter(adapter);
                    info.setText("Éxito");
                }catch (JSONException e){
                    e.printStackTrace();
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                info.setText("error al cargar datos");
            }
        });

        requestQueue.add(jsonObjectRequest);

    }
}
