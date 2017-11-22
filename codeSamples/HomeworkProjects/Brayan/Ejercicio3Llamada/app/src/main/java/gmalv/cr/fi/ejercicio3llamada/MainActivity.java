package gmalv.cr.fi.ejercicio3llamada;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    EditText numeroTel;
    Button llamar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        numeroTel = (EditText) findViewById(R.id.numeroTel);
        llamar = (Button) findViewById(R.id.llamar);
        llamar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                llamar();
            }
        });
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String permissions[], int[] grantResults) {
        switch (requestCode) {
            case 123: {
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    llamar();
                }
                else
                {
                    Toast.makeText(getApplicationContext(),"Permisos no aceptados", Toast.LENGTH_LONG).show();
                }
                return;
            }
        }
    }

    public void llamar(){
        String numero = numeroTel.getText().toString();
        try {
            String uri = "tel:" + numero;
            Intent callIntent = new Intent(Intent.ACTION_CALL);
            callIntent.setData(Uri.parse(uri));
            if (ActivityCompat.checkSelfPermission(MainActivity.this, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
                ActivityCompat.requestPermissions(MainActivity.this, new String[]{Manifest.permission.CALL_PHONE},123);
            }
            else{
                startActivity(callIntent);
            }
        }
        catch (Exception e){}
    }
}
