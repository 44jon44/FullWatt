package app;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import com.main.fullwat.R;

import Implementation.ItemImplementation;
import interfaces.ItemAVInterface;


public class MainActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Spinner spinner =  findViewById(R.id.spinner);
        spinner.setOnItemSelectedListener(this);

    // Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.idiomas_array, android.R.layout.simple_spinner_item);
    // Specify the layout to use when the list of choices appears
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
    // Apply the adapter to the spinner
        spinner.setAdapter(adapter);


    }

    public void onItemSelected(AdapterView<?> parent, View view,
                               int pos, long id) {
        // An item was selected. You can retrieve the selected item using
        pos=parent.getSelectedItemPosition();

        if (pos==1) {
            Toast.makeText(this, "1", Toast.LENGTH_LONG).show();
            Intent intent = new Intent(MainActivity.this, Tiras.class);
            intent.putExtra("idioma",1);
            startActivity(intent);
        }
        if (pos==2) {
            Toast.makeText(this, "2", Toast.LENGTH_LONG).show();
            Intent intent = new Intent(MainActivity.this,Tiras.class);
            intent.putExtra("idioma",2);
            startActivity(intent);
        }
        if (pos==3) {
            Toast.makeText(this, "3", Toast.LENGTH_LONG).show();
            Intent intent = new Intent(MainActivity.this,Tiras.class);
            intent.putExtra("idioma",3);
            startActivity(intent);
        }
    }
    public void onNothingSelected(AdapterView<?> parent) {
        // Another interface callback
    }
    public static ItemAVInterface cargarFiltros(){
        return new ItemImplementation();
    }






}