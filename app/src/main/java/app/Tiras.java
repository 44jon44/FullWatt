package app;

import static java.lang.Thread.sleep;

import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.main.fullwat.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class Tiras extends AppCompatActivity  {

    ArrayList<String> models = new ArrayList<>();
    ArrayList<String> ips = new ArrayList<>();
    ArrayAdapter<String> modelAdapter;
    ArrayAdapter<String> ipAdapter;
    RequestQueue requestQueueM;
    RequestQueue requestQueueIP;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tiraslayout);
        requestQueueM = Volley.newRequestQueue(this);
        requestQueueIP = Volley.newRequestQueue(this);
        String URLModelos = "http://192.168.224.86/fullwat_app_db/fetchModels.php";
        String URLTensions = "http://192.168.224.86/fullwat_app_db/fetchTensions.php";
        String URLPotencias = "http://192.168.224.86/fullwat_app_db/fetchPotencias.php";
        String URLLumens = "http://192.168.224.86/fullwat_app_db/fetchLumens.php";
        String URLLeds = "http://192.168.224.86/fullwat_app_db/fetchLeds.php";
        String URLIPs = "http://192.168.224.86/fullwat_app_db/fetchIPs.php";

        Spinner spinnerM = findViewById(R.id.modelo);

        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, URLModelos, null, new Response.Listener<JSONObject>() {

            @Override
            public void onResponse(JSONObject response) {

                System.out.println(response.length());
                System.out.println(response.toString());

                try {
                    JSONArray jsonArray = response.getJSONArray("models");
                    for (int i = 0; i < jsonArray.length(); i++) {
                        JSONObject jsonObject=jsonArray.getJSONObject(i);
                        String id=jsonObject.optString("ID");
                        String attribute_id=jsonObject.optString("Attribute_ID");
                        String filter=jsonObject.optString("Filter");
                        String tempCol=filter;
                        String value=jsonObject.optString("Value");
                        models.add(filter);
                        modelAdapter = new ArrayAdapter<>(Tiras.this, android.R.layout.simple_spinner_item, models);
                        modelAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                        spinnerM.setAdapter(modelAdapter);
                        spinnerM.setPrompt("pajinprmt");
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d("error", error.toString());
            }
        });
        requestQueueM.add(request);
        spinnerM.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                System.out.println("Pajin");
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });


        Spinner spinnerT = findViewById(R.id.tension);


        Spinner spinnerTe = findViewById(R.id.temperatura);


        Spinner spinnerP = findViewById(R.id.potenciaMetro);


        Spinner spinnerLu = findViewById(R.id.lumenesMetro);


        Spinner spinnerLe = findViewById(R.id.ledsMetro);


        Spinner spinnerIp = findViewById(R.id.ip);
        JsonObjectRequest requestIP = new JsonObjectRequest(Request.Method.GET, URLIPs, null, new Response.Listener<JSONObject>() {

            @Override
            public void onResponse(JSONObject response) {

                System.out.println(response.length());
                System.out.println(response.toString());

                try {
                    JSONArray jsonArray = response.getJSONArray("ips");
                    for (int i = 0; i < jsonArray.length(); i++) {
                        JSONObject jsonObject=jsonArray.getJSONObject(i);
                        //String id=jsonObject.optString("ID");
                        //String attribute_id=jsonObject.optString("Attribute_ID");
                        //String filter=jsonObject.optString("Filter");
                        String value3=jsonObject.getString("Value");
                        ips.add(value3);
                        ipAdapter = new ArrayAdapter<>(Tiras.this, android.R.layout.simple_spinner_item, ips);
                        ipAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

                        spinnerIp.setAdapter(ipAdapter);
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d("error", error.toString());
            }
        });
        requestQueueIP.add(requestIP);
        spinnerIp.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                System.out.println("pajinIP");

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });


    }



}