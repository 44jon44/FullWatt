package app;

import static java.lang.Thread.sleep;

import android.os.Bundle;
import android.util.Log;
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
import com.android.volley.toolbox.Volley;
import com.main.fullwat.R;

import org.json.JSONArray;
import org.json.JSONException;

import java.util.ArrayList;

public class Tiras extends AppCompatActivity implements AdapterView.OnItemSelectedListener {
    RequestQueue requestQueue;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        int m = 0;

        super.onCreate(savedInstanceState);
        setContentView(R.layout.tiraslayout);

        Spinner spinnerT = findViewById(R.id.tension);
        spinnerT.setOnItemSelectedListener(this);

        Spinner spinnerTe = findViewById(R.id.temperatura);
        spinnerTe.setOnItemSelectedListener(this);

        Spinner spinnerP = findViewById(R.id.potenciaMetro);
        spinnerP.setOnItemSelectedListener(this);

        Spinner spinnerLu = findViewById(R.id.lumenesMetro);
        spinnerLu.setOnItemSelectedListener(this);

        Spinner spinnerLe = findViewById(R.id.ledsMetro);
        spinnerLe.setOnItemSelectedListener(this);

        Spinner spinnerIp = findViewById(R.id.ip);
        spinnerIp.setOnItemSelectedListener(this);


        requestQueue = Volley.newRequestQueue(this);
        ArrayList<String> models = new ArrayList<>();
        models = readModel();


        try {
            sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        ArrayAdapter<String> spinnerArrayAdapter = new ArrayAdapter<String>(getApplicationContext(),
                android.R.layout.simple_spinner_item, models);
        spinnerArrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        Spinner spinnerM = findViewById(R.id.modelo);
        spinnerM.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int pos, long arg) {
                int id = parent.getId();
                System.out.println("Pajin1");

                switch (id) {
                    case R.id.modelo:
                        System.out.println("pajin");
                        break;
                    case R.id.temperatura:
                        break;
                    default:
                        System.out.println("PAJIN");
                        break;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        spinnerM.setAdapter(spinnerArrayAdapter);
        spinnerArrayAdapter.notifyDataSetChanged();

/*
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.planets_array, android.R.layout.simple_spinner_item);
        // Specify the layout to use when the list of choices appears
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerM.setOnItemSelectedListener(this);
        spinnerM.setAdapter(adapter);
*/
    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }

    private ArrayList readModel() {
        String URL = "http://192.168.224.86/fullwat_app_db/fetchModel.php";
        ArrayList<String> respuesta = new ArrayList<String>();
        JsonArrayRequest request = new JsonArrayRequest(Request.Method.GET, URL, new JSONArray(), new Response.Listener<JSONArray>() {

            @Override
            public void onResponse(JSONArray response) {

                System.out.println(response.length());
                System.out.println(response.toString());
                for (int i = 0; i < 1; i++) {
                    try {
                        System.out.println(response.getJSONObject(i).get("Value"));
                        respuesta.add(response.getJSONObject(i).get("Value").toString());

                    } catch (JSONException e) {
                        e.printStackTrace();
                    }

                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d("error", error.toString());
            }
        });
        requestQueue.add(request);

        return respuesta;
    }


}