package app;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.main.fullwat.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class Tiras extends AppCompatActivity {

    ArrayList<String> models = new ArrayList<>();
    ArrayList<String> tensions = new ArrayList<>();
    ArrayList<String> tempCols = new ArrayList<>();
    ArrayList<String> potenciaM = new ArrayList<>();
    ArrayList<String> lumenM = new ArrayList<>();
    ArrayList<String> ledsM = new ArrayList<>();
    ArrayList<String> ips = new ArrayList<>();

    ArrayAdapter<String> modelAdapter;
    ArrayAdapter<String> tensionAdapter;
    ArrayAdapter<String> tempColsAdapter;
    ArrayAdapter<String> potenciaMAdapter;
    ArrayAdapter<String> lumenMAdapter;
    ArrayAdapter<String> ledsMAdapter;
    ArrayAdapter<String> ipAdapter;

    RequestQueue requestQueueM;
    RequestQueue requestQueueTen;
    RequestQueue requestQueueTemp;
    RequestQueue requestQueueP;
    RequestQueue requestQueueLu;
    RequestQueue requestQueueLe;
    RequestQueue requestQueueIP;

    Spinner spinnerM;
    Spinner spinnerT;
    Spinner spinnerTe;
    Spinner spinnerP;
    Spinner spinnerLu;
    Spinner spinnerLe;
    Spinner spinnerIp;

    String URLModelos = "http://192.168.224.86/fullwat_app_db/fetchModels.php";
    String URLTensions = "http://192.168.224.86/fullwat_app_db/fetchTensions.php";
    String URLPotencias = "http://192.168.224.86/fullwat_app_db/fetchPotencias.php";
    String URLLumens = "http://192.168.224.86/fullwat_app_db/fetchLumens.php";
    String URLLeds = "http://192.168.224.86/fullwat_app_db/fetchLedsM.php";
    String URLIPs = "http://192.168.224.86/fullwat_app_db/fetchIPs.php";
    String URLTempCol = "http://localhost/fullwat_app_db/fetchTempCol.php?filter=";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tiraslayout);

        Spinner spinnerM = findViewById(R.id.modelo);
        Spinner spinnerT = findViewById(R.id.tension);
        Spinner spinnerTe = findViewById(R.id.temperatura);
        Spinner spinnerP = findViewById(R.id.potenciaMetro);
        Spinner spinnerLu = findViewById(R.id.lumenesMetro);
        Spinner spinnerLe = findViewById(R.id.ledsMetro);
        Spinner spinnerIp = findViewById(R.id.ip);

        requestQueueM = Volley.newRequestQueue(this);
        requestQueueIP = Volley.newRequestQueue(this);
        requestQueueLe = Volley.newRequestQueue(this);
        requestQueueTemp = Volley.newRequestQueue(this);
        requestQueueTen = Volley.newRequestQueue(this);
        requestQueueP = Volley.newRequestQueue(this);
        requestQueueLu = Volley.newRequestQueue(this);


        //
        //Modelos
        //

        JsonObjectRequest requestM = new JsonObjectRequest(Request.Method.GET, URLModelos, null, new Response.Listener<JSONObject>() {

            @Override
            public void onResponse(JSONObject response) {


                System.out.println(response.toString());
                System.out.println("PajinM0");
                models.add(0, "Modelo de la tira*Obligatorio*");
                try {
                    JSONArray jsonArray = response.getJSONArray("models");
                    for (int i = 0; i < jsonArray.length(); i++) {
                        JSONObject jsonObject = jsonArray.getJSONObject(i);
                        String id = jsonObject.optString("ID");
                        String attribute_id = jsonObject.optString("Attribute_ID");
                        String filter = jsonObject.optString("Filter");

                        String value = jsonObject.optString("Value");

                        models.add(filter);
                        modelAdapter = new ArrayAdapter<>(Tiras.this, android.R.layout.simple_spinner_item, models);
                        modelAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                        spinnerM.setAdapter(modelAdapter);

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
        requestQueueM.add(requestM);
        spinnerM.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                String tempCol = adapterView.getItemAtPosition(i).toString();
                System.out.println("PajinM");
                models.remove(0);
                models.add(0, "Modelo de la tira");
                if (spinnerM.getItemAtPosition(i).toString().equalsIgnoreCase("Monocolor_Colores_y_Blancos_Especiales") || spinnerM.getItemAtPosition(i).toString().equalsIgnoreCase("RGB y RGBW") || spinnerM.getItemAtPosition(i).toString().equalsIgnoreCase("Sector Alimentación") || spinnerM.getItemAtPosition(i).toString().equalsIgnoreCase("Crecimiento plantas")) {
                    lumenM.add(0, "No se puede realizar este filtro");

                }
                cargarTempCol(tempCol);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        //
        //TENSION
        //

        JsonObjectRequest requestT = new JsonObjectRequest(Request.Method.GET, URLTensions, null, new Response.Listener<JSONObject>() {

            @Override
            public void onResponse(JSONObject response) {

                System.out.println(response.length());
                System.out.println(response.toString());
                tensions.add(0, "Tension*Opcional*");
                try {
                    JSONArray jsonArray = response.getJSONArray("tensions");
                    for (int i = 0; i < jsonArray.length(); i++) {
                        JSONObject jsonObject = jsonArray.getJSONObject(i);
                        String id = jsonObject.optString("ID");
                        String attribute_id = jsonObject.optString("Attribute_ID");
                        String filter = jsonObject.optString("Filter");

                        String value = jsonObject.optString("Value");

                        tensions.add(value);
                        tensionAdapter = new ArrayAdapter<>(Tiras.this, android.R.layout.simple_spinner_item, tensions);
                        tensionAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                        spinnerT.setAdapter(tensionAdapter);

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
        requestQueueTen.add(requestT);
        spinnerM.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                System.out.println("PajinTension");
                String tempCol = adapterView.getItemAtPosition(i).toString();
                tensions.remove(0);
                tensions.add(0, "Tension");

            }

            @Override
                public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        //
        //TEMPERATURA COLOR
        //


        //
        //POTENCIA/M
        //
        potenciaM.add("Potencia/M *Opcional*");
        potenciaM.add("<5");
        potenciaM.add("6-10");
        potenciaM.add("11-15");
        potenciaM.add("16-20");
        potenciaM.add("21-25");

        potenciaMAdapter = new ArrayAdapter<>(Tiras.this, android.R.layout.simple_spinner_item, potenciaM);
        potenciaMAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        spinnerP.setAdapter(potenciaMAdapter);
        spinnerP.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                System.out.println("PajinPotencia");
                potenciaM.remove(0);
                potenciaM.add(0, "Potencia/M");
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        //
        //lUMENES/M
        //

        JsonObjectRequest requestLuM = new JsonObjectRequest(Request.Method.GET, URLLumens, null, new Response.Listener<JSONObject>() {

            @Override
            public void onResponse(JSONObject response) {


                lumenM.add(0, "Lum/M *Opcional*");
                try {
                    JSONArray jsonArray = response.getJSONArray("lumens");
                    for (int i = 0; i < jsonArray.length(); i++) {
                        JSONObject jsonObject = jsonArray.getJSONObject(i);
                        //String id=jsonObject.optString("ID");
                        //String attribute_id=jsonObject.optString("Attribute_ID");
                        //String filter=jsonObject.optString("Filter");
                        String value = jsonObject.getString("Value");

                        lumenM.add(value);
                        lumenMAdapter = new ArrayAdapter<>(Tiras.this, android.R.layout.simple_spinner_item, lumenM);
                        lumenMAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

                        spinnerLu.setAdapter(lumenMAdapter);
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
        requestQueueLu.add(requestLuM);
        spinnerLu.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                lumenM.remove(0);
                lumenM.add(0, "Lumen/M");
                System.out.println("PajinLum");
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        //
        //LEDS/M
        //

        JsonObjectRequest requestLedsM = new JsonObjectRequest(Request.Method.GET, URLLeds, null, new Response.Listener<JSONObject>() {

            @Override
            public void onResponse(JSONObject response) {


                ledsM.add(0, "Leds/M *Opcional*");
                try {
                    JSONArray jsonArray = response.getJSONArray("ledsM");
                    for (int i = 0; i < jsonArray.length(); i++) {
                        JSONObject jsonObject = jsonArray.getJSONObject(i);
                        //String id=jsonObject.optString("ID");
                        //String attribute_id=jsonObject.optString("Attribute_ID");
                        //String filter=jsonObject.optString("Filter");
                        String value = jsonObject.getString("Value");

                        ledsM.add(value);
                        ledsMAdapter = new ArrayAdapter<>(Tiras.this, android.R.layout.simple_spinner_item, ledsM);
                        ledsMAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

                        spinnerLe.setAdapter(ledsMAdapter);
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
        requestQueueLe.add(requestLedsM);
        spinnerLe.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                ledsM.remove(0);
                ledsM.add(0, "Leds/M");
                System.out.println("PajinLed");
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        //
        //IP
        //


        JsonObjectRequest requestIP = new JsonObjectRequest(Request.Method.GET, URLIPs, null, new Response.Listener<JSONObject>() {

            @Override
            public void onResponse(JSONObject response) {

                System.out.println(response.length());
                System.out.println(response.toString());
                ips.add(0, "Indice de estanqueidad *Opcional*");
                try {
                    JSONArray jsonArray = response.getJSONArray("ips");
                    for (int i = 0; i < jsonArray.length(); i++) {
                        JSONObject jsonObject = jsonArray.getJSONObject(i);
                        //String id=jsonObject.optString("ID");
                        //String attribute_id=jsonObject.optString("Attribute_ID");
                        //String filter=jsonObject.optString("Filter");
                        String value3 = jsonObject.getString("Value");

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
                ips.remove(0);
                ips.add(0, "Indice de estanqueidad");
                System.out.println("PajinIP");
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });


    }

    private void cargarTempCol(String tempCol) {
        URLTempCol = URLTempCol.concat(tempCol);
        JsonObjectRequest requestTemp = new JsonObjectRequest(Request.Method.GET, URLTempCol, null, new Response.Listener<JSONObject>() {

            @Override
            public void onResponse(JSONObject response) {


                tempCols.add(0, "Temperatura de color *Obligatorio*");
                try {
                    JSONArray jsonArray = response.getJSONArray("tempCols");
                    for (int i = 0; i < jsonArray.length(); i++) {
                        JSONObject jsonObject = jsonArray.getJSONObject(i);
                        //String id=jsonObject.optString("ID");
                        //String attribute_id=jsonObject.optString("Attribute_ID");
                        //String filter=jsonObject.optString("Filter");
                        String value = jsonObject.getString("Value");

                        tempCols.add(value);
                        tempColsAdapter = new ArrayAdapter<>(Tiras.this, android.R.layout.simple_spinner_item, tempCols);
                        tempColsAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

                        spinnerTe.setAdapter(tempColsAdapter);
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
        requestQueueTemp.add(requestTemp);
        spinnerTe.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                tempCols.remove(0);
                tempCols.add(0, "Lumen/M");
                System.out.println("PajinTemp");

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
    }


}