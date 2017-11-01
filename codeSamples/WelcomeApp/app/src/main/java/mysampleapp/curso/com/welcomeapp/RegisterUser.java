package mysampleapp.curso.com.welcomeapp;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.gson.Gson;

import org.json.JSONObject;

import java.util.ArrayList;

public class RegisterUser extends AppCompatActivity {

    EditText et_name, et_lastName, et_user, et_password;
    Button btn_register;
    Context context;
    ListUserObject listUserEncapsulado;

    Intent getData;
    UserObject userObject;

    /*int entero;
    String nombre;*/

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_user);
        context = this;

        /*getData = getIntent();
        userObject = (UserObject)getData.getSerializableExtra("user");
        entero = getData.getIntExtra("userId", 0);
        nombre = getData.getStringExtra("name");*/

        et_name = (EditText) findViewById(R.id.et_name);
        et_lastName = (EditText) findViewById(R.id.et_lastName);
        et_user = (EditText) findViewById(R.id.et_user);
        et_password = (EditText) findViewById(R.id.et_password);

        btn_register = (Button) findViewById(R.id.btnRegister);

        /*et_name.setText(userObject.getName());
        et_lastName.setText(userObject.getLastName());*/


        btn_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences preferences = getSharedPreferences("MyPreferences", Context.MODE_PRIVATE);
                //SharedPreferences prefs = getSharedPreferences("MyPreferences", MODE_PRIVATE);
                String stringListUser = preferences.getString("ListUsers", null);
                Gson gson = new Gson();

                ArrayList<UserObject> UserListTemporal = new  ArrayList<UserObject>();

                if(stringListUser != null) {
                    listUserEncapsulado = gson.fromJson(stringListUser, ListUserObject.class);
                    UserListTemporal = listUserEncapsulado.getUserObjectsList();
                }
                else {
                    listUserEncapsulado = new ListUserObject();
                }

                SharedPreferences.Editor editor = preferences.edit();

                UserObject user = new UserObject();
                user.setName(et_name.getText().toString());
                user.setLastName(et_lastName.getText().toString());
                user.setPassword(et_password.getText().toString());
                user.setUser(et_user.getText().toString());

                UserListTemporal.add(user);

                listUserEncapsulado.setUserObjectsList(UserListTemporal);
                String stringJson = gson.toJson(listUserEncapsulado);

                editor.putString("ListUsers", stringJson);
                editor.commit();

                CleanFields();

                Toast.makeText(context, "Usuario registrado", Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void CleanFields(){
        et_name.setText("");
        et_lastName.setText("");
        et_user.setText("");
        et_password.setText("");
    }
}
