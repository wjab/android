package gmalv.cr.fi.ejercicio1;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    TextView lblResultado;
    EditText txtNombre, txtApellido;
    Button btnMensaje;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        lblResultado = (TextView) findViewById(R.id.lblResultado);
        txtNombre = (EditText) findViewById(R.id.txtNombre);
        txtApellido = (EditText) findViewById(R.id.txtApellido);
        btnMensaje = (Button) findViewById(R.id.btnMensaje);
        lblResultado.setText("");

        btnMensaje.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                lblResultado.setText("Bienvenido(a) " + txtNombre.getText() + " " + txtApellido.getText() + "!");
            }
        });
    }
}
