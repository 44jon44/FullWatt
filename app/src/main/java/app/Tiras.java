package app;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import androidx.appcompat.app.AppCompatActivity;

import com.main.fullwat.R;

import java.util.List;
import java.util.Observable;

public class Tiras extends AppCompatActivity  implements AdapterView.OnItemSelectedListener{


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tiraslayout);

        Spinner spinnerM =  findViewById(R.id.modelo);
        spinnerM.setOnItemSelectedListener(this);

        Spinner spinnerT =  findViewById(R.id.tension);
        spinnerT.setOnItemSelectedListener(this);

        Spinner spinnerTe =  findViewById(R.id.temperatura);
        spinnerTe.setOnItemSelectedListener(this);

        Spinner spinnerP =  findViewById(R.id.potenciaMetro);
        spinnerP.setOnItemSelectedListener(this);

        Spinner spinnerLu =  findViewById(R.id.lumenesMetro);
        spinnerLu.setOnItemSelectedListener(this);

        Spinner spinnerLe =  findViewById(R.id.ledsMetro);
        spinnerLe.setOnItemSelectedListener(this);

        Spinner spinnerIp =  findViewById(R.id.ip);
        spinnerIp.setOnItemSelectedListener(this);



        // Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter<CharSequence> adapterM = ArrayAdapter.createFromResource(this,
                R.array.planets_array, android.R.layout.simple_spinner_item);
        // Specify the layout to use when the list of choices appears
        adapterM.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // Apply the adapter to the spinner
        spinnerM.setAdapter(adapterM);
    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
        List<String> dd;
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }
}
