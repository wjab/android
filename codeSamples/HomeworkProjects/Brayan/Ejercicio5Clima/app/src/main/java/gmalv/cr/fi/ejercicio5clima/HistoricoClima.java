package gmalv.cr.fi.ejercicio5clima;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import gmalv.cr.fi.database.DatabaseManager;
import gmalv.cr.fi.model.Clima;

public class HistoricoClima extends AppCompatActivity {

    Context context = this;
    Button btnVerClima;
    List<Clima> climas;
    ArrayList<Clima> climaList = new ArrayList();
    ListView lvClima;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_historico_clima);

        lvClima = (ListView) findViewById(R.id.lvClima);
        btnVerClima = (Button) findViewById(R.id.btnVerClima);

        DatabaseManager.init(context);
        climas = DatabaseManager.getInstance().getAllWeatherLists();

        if(climas != null) {
            for (Clima clima: climas) {
                climaList.add(clima);
            }
            AdapterItem adapter = new AdapterItem(this, climaList);
            lvClima.setAdapter(adapter);
        }

        btnVerClima.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                try{
                    Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                    startActivity(intent);
                }

                catch (Exception e){
                    Toast toast = Toast.makeText(getApplicationContext(), e.getMessage(),Toast.LENGTH_SHORT);
                    toast.show();
                }
            }
        });
    }
}
