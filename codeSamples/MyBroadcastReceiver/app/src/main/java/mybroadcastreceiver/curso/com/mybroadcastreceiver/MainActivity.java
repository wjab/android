package mybroadcastreceiver.curso.com.mybroadcastreceiver;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements ConnectivityReceiver.ConnectivityReceiverListener {

    private Button btnCheck;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnCheck = (Button) findViewById(R.id.btn_check);

        checkConnection();

        btnCheck.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Chequeo manual de la conexion
                checkConnection();
            }
        });
    }

    private void checkConnection() {
        boolean isConnected = ConnectivityReceiver.isConnected();
        showConnectionStatus(isConnected);
    }

    public void showConnectionStatus(boolean isConnected)
    {
        if(isConnected) {
            Toast.makeText(getApplicationContext(), getString(R.string.conn_enabled), Toast.LENGTH_LONG).show();
        }
        else
        {
            Toast.makeText(getApplicationContext(), getString(R.string.conn_disabled), Toast.LENGTH_LONG).show();
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        MyApplication.getInstance().setConnectivityListener(this);
    }

    @Override
    public void onNetworkConnectionChange(boolean isConnected) {
        showConnectionStatus(isConnected);
    }
}
