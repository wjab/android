package gmalv.cr.fi.ejercicio5clima;

import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.widget.Button;

import gmalv.cr.fi.database.DatabaseManager;

public class MainActivity extends FragmentActivity {

    Button btnHistorico, btnActualizar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        DatabaseManager.init(this);

        btnHistorico = (Button) findViewById(R.id.btnHistorico);
        btnActualizar = (Button) findViewById(R.id.btnActualizar);

        btnHistorico.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                HistoricoFragment historicoFragment = new HistoricoFragment();
                historicoFragment.setArguments(getIntent().getExtras());
                FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
                transaction.replace(R.id.fragment_container, historicoFragment);
                //transaction.addToBackStack(null);
                transaction.commit();
            }
        });

        btnActualizar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                ClimaFragment climaFragment = new ClimaFragment();
                climaFragment.setArguments(getIntent().getExtras());
                FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
                transaction.replace(R.id.fragment_container, climaFragment);
                //transaction.addToBackStack(null);
                transaction.commit();
            }
        });

        if (findViewById(R.id.fragment_container) != null) {

            if (savedInstanceState != null) {
                return;
            }

            ClimaFragment climaFragment = new ClimaFragment();

            climaFragment.setArguments(getIntent().getExtras());

            getSupportFragmentManager().beginTransaction()
                    .add(R.id.fragment_container, climaFragment).commit();
        }
    }
}
