package com.finder.movie.ejercicio4sharepreference;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.finder.movie.Database.DatabaseManager;
import com.finder.movie.model.User;
import com.google.gson.Gson;

import java.util.ArrayList;

public class RegistroActivity extends AppCompatActivity {

    Button registrarSharedPreference, registrarDataBase;
    EditText nombre, telefono, email, direccion, usuario, contrasena;
    Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro);
        context = this;
        DatabaseManager.init(context);
        registrarSharedPreference = (Button) findViewById(R.id.btnRegistrarSharedPreference);
        registrarDataBase = (Button) findViewById(R.id.btnRegistrarDataBase);
        nombre = (EditText) findViewById(R.id.txtNombre);
        telefono = (EditText) findViewById(R.id.txtTelefono);
        email = (EditText) findViewById(R.id.txtEmail);
        direccion = (EditText) findViewById(R.id.txtDireccion);
        usuario = (EditText) findViewById(R.id.txtUsuario);
        contrasena = (EditText) findViewById(R.id.txtContrasena);

        registrarSharedPreference.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                try{
                    Usuario nuevoUsuario = new Usuario(nombre.getText().toString(), telefono.getText().toString(), email.getText().toString(), direccion.getText().toString(), usuario.getText().toString(), contrasena.getText().toString());
                    if(guardarUsuario(nuevoUsuario)){
                        Toast toast = Toast.makeText(getApplicationContext(), getString(R.string.mensaje_registro_exitoso),Toast.LENGTH_SHORT);
                        toast.show();
                        limpiarCampos();
                    }
                    else{
                        Toast toast = Toast.makeText(getApplicationContext(), getString(R.string.mensaje_registro_fallido),Toast.LENGTH_SHORT);
                        toast.show();
                    }
                }
                catch (Exception e){
                    Toast toast = Toast.makeText(getApplicationContext(), e.getMessage(),Toast.LENGTH_SHORT);
                    toast.show();
                }
            }
        });

        registrarDataBase.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                try {
                    User user = new User();
                    user.nombre = nombre.getText().toString();
                    user.telefono = telefono.getText().toString();
                    user.email = email.getText().toString();
                    user.direccion = direccion.getText().toString();
                    user.usuario = usuario.getText().toString();
                    user.contrasena = contrasena.getText().toString();

                    DatabaseManager.getInstance().addUser(user);
                    Toast.makeText(context, getString(R.string.mensaje_registro_exitoso), Toast.LENGTH_SHORT).show();
                    limpiarCampos();
                }
                catch (Exception ex)
                {
                    Log.e(ex.getMessage(), getString(R.string.mensaje_registro_fallido));
                    Toast.makeText(context, getString(R.string.mensaje_registro_fallido), Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    public void limpiarCampos(){
        nombre.setText("");
        telefono.setText("");
        email.setText("");
        direccion.setText("");
        usuario.setText("");
        contrasena.setText("");
    }

    public boolean guardarUsuario(Usuario usuario){

        SharedPreferences preferences = getSharedPreferences("MyPreferences", Context.MODE_PRIVATE);
        String listaUsuariosString = preferences.getString("ListaUsuarios", null);
        Gson gson = new Gson();

        ListaUsuario listaUsuariosEncapsulado;
        ArrayList<Usuario> listaUsuariosTemporal = new ArrayList<Usuario>();

        if (listaUsuariosString != null) {
            listaUsuariosEncapsulado = gson.fromJson(listaUsuariosString, ListaUsuario.class);
            listaUsuariosTemporal = listaUsuariosEncapsulado.getListaUsuario();
        } else {
            listaUsuariosEncapsulado = new ListaUsuario();
        }

        SharedPreferences.Editor editor = preferences.edit();
        listaUsuariosTemporal.add(usuario);
        listaUsuariosEncapsulado.setListaUsuario(listaUsuariosTemporal);
        String stringJason = gson.toJson(listaUsuariosEncapsulado, ListaUsuario.class);
        editor.putString("ListaUsuarios", stringJason);
        editor.commit();


        return true;
    }
}
