package com.finder.movie.ejercicio4sharepreference;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    EditText usuario, contrasena;
    TextView registrarse;
    Button iniciarSesion;
    Usuario usuarioActual;
    ListaUsuario listaUsuariosEncapsulado;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        usuario = (EditText) findViewById(R.id.txtUsuario);
        contrasena = (EditText) findViewById(R.id.txtCotrasena);
        registrarse = (TextView) findViewById(R.id.lblRegistrarse);
        iniciarSesion = (Button) findViewById(R.id.btnIniciarSesion);

        iniciarSesion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                try{
                    if(usuario.getText() != null && contrasena.getText() != null && autenticacion(usuario.getText().toString(), contrasena.getText().toString())){
                        Intent intent = new Intent(getApplicationContext(), InfoActivity.class);
                        intent.putExtra("USUARIO_ACTUAL", usuarioActual);
                        intent.putExtra("LISTA_USUARIOS", listaUsuariosEncapsulado);
                        startActivity(intent);
                    }
                    else{
                        Toast toast = Toast.makeText(getApplicationContext(), getString(R.string.mensaje_autenticacion_fallido),Toast.LENGTH_SHORT);
                        toast.show();
                    }
                }

                catch (Exception e){
                    Toast toast = Toast.makeText(getApplicationContext(), e.getMessage(),Toast.LENGTH_SHORT);
                    toast.show();
                }
            }
        });


        registrarse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), RegistroActivity.class);
                startActivity(intent);
            }
        });
    }

    public boolean autenticacion(String usuarioCredencia, String contrasenaCredencial){

        SharedPreferences preferences = getSharedPreferences("MyPreferences", Context.MODE_PRIVATE);
        String listaUsuariosString = preferences.getString("ListaUsuarios", null);
        Gson gson = new Gson();



        if (listaUsuariosString != null) {
            listaUsuariosEncapsulado = gson.fromJson(listaUsuariosString, ListaUsuario.class);

            if(listaUsuariosEncapsulado != null
                    && listaUsuariosEncapsulado.getListaUsuario() != null
                    && listaUsuariosEncapsulado.getListaUsuario().size() > 0){
                for (Usuario usuario:listaUsuariosEncapsulado.getListaUsuario()) {
                    if(usuario.getUsuario().equals(usuarioCredencia) && usuario.getContrasena().equals(contrasenaCredencial)){
                        usuarioActual = usuario;
                        return true;
                    }
                }
            }
        }

        return  false;
    }
}
