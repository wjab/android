package mysampleapp.curso.com.welcomeapp;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    // Definicion de las variables de los controles

    EditText et_name, et_lastName;
    Button btn_welcome, btn_add_new, btn_list_all, btn_welcome_custom;
    String welcomeMessage;
    Toast toast;
    UserObject userObject;
    Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        context = this;

        et_name = (EditText) findViewById(R.id.et_name);
        et_lastName = (EditText) findViewById(R.id.et_lastName);

        btn_welcome = (Button) findViewById(R.id.btn_welcome);
        btn_add_new = (Button) findViewById(R.id.btn_add_new);
        btn_list_all = (Button) findViewById(R.id.btn_list_all);
        btn_welcome_custom = (Button) findViewById(R.id.btn_welcome_custom);

        userObject = new UserObject();

        btn_welcome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (et_name.getText() != null && et_lastName.getText() != null)
                {
                    welcomeMessage = String.format(getString(R.string.welcome_msg), et_name.getText(), et_lastName.getText() );
                    toast = Toast.makeText(getApplicationContext(), welcomeMessage, Toast.LENGTH_SHORT);
                    toast.show();
                }
            }
        });

        btn_welcome_custom.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                welcomeMessage = String.format(getString(R.string.welcome_msg), et_name.getText(), et_lastName.getText() );

                Utilities utilities = new Utilities();
                utilities.ShowSuccessDialog(welcomeMessage, context);
            }
        });

        btn_add_new.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent registerIntent = new Intent(getApplicationContext(), RegisterUser.class);
                startActivity(registerIntent);
                finish();
            }
        });

        btn_list_all.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                userObject.setName(et_name.getText().toString());
                userObject.setLastName(et_lastName.getText().toString());

                Intent intentShowData = new Intent(getApplicationContext(), activity_user_list.class);
                /*intentShowData.putExtra("user", userObject);
                intentShowData.putExtra("userId", 44);
                intentShowData.putExtra("name", "Minombre");*/
                startActivity(intentShowData);
                finish();
            }
        });
    }






}
