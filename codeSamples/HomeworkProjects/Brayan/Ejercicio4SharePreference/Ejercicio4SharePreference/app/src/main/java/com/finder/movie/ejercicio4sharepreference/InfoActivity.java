package com.finder.movie.ejercicio4sharepreference;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.finder.movie.Database.DatabaseManager;
import com.finder.movie.model.User;

import java.util.ArrayList;
import java.util.List;

public class InfoActivity extends AppCompatActivity {

    TextView nombre,telefono, email, direccion, usuario, contrasena;
    ListView lvUsuarios;
    ListaUsuario listaUsuariosDB = new ListaUsuario();
    ListaUsuario listaUsuariosSharedPreference = new ListaUsuario();
    Context context;
    List<User> userList;
    Intent intent;
    Button btnListaSharedPreference, btnListaDataBase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info);
        context = this;
        DatabaseManager.init(context);
        intent = getIntent();

        nombre = (TextView) findViewById(R.id.lblNombre);
        telefono = (TextView) findViewById(R.id.lblTelefono);
        email = (TextView) findViewById(R.id.lblEmail);
        direccion = (TextView) findViewById(R.id.lblDireccion);
        usuario = (TextView) findViewById(R.id.lblUsuario);
        contrasena = (TextView) findViewById(R.id.lblContrasena);
        lvUsuarios = (ListView) findViewById(R.id.lvUsuario);
        btnListaSharedPreference = (Button) findViewById(R.id.btnListaSharedPreference);
        btnListaDataBase = (Button) findViewById(R.id.btnListaDataBase);

        listaUsuariosSharedPreference = (ListaUsuario) intent.getSerializableExtra("LISTA_USUARIOS");

        getUsuarioActual();
        getUsuariosSharedPreferences(lvUsuarios);

        btnListaSharedPreference.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                try{
                    getUsuariosSharedPreferences(lvUsuarios);
                    if(listaUsuariosSharedPreference != null && listaUsuariosSharedPreference.getListaUsuario() != null && listaUsuariosSharedPreference.getListaUsuario().size() > 0){
                        Toast toast = Toast.makeText(getApplicationContext(), getString(R.string.mensaje_lista_usuario_shared),Toast.LENGTH_LONG);
                        toast.show();
                    }
                    else{
                        Toast toast = Toast.makeText(getApplicationContext(), getString(R.string.mensaje_lista_usuario_vacia),Toast.LENGTH_LONG);
                        toast.show();
                    }
                }
                catch (Exception e){
                    Toast toast = Toast.makeText(getApplicationContext(), e.getMessage(),Toast.LENGTH_LONG);
                    toast.show();
                }
            }
        });

        btnListaDataBase.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                try{
                    getUsuariosDataBase(lvUsuarios);
                    if(listaUsuariosDB != null && listaUsuariosDB.getListaUsuario() != null && listaUsuariosDB.getListaUsuario().size() > 0){
                        Toast toast = Toast.makeText(getApplicationContext(), getString(R.string.mensaje_lista_usuario_db),Toast.LENGTH_LONG);
                        toast.show();
                    }
                    else{
                        Toast toast = Toast.makeText(getApplicationContext(), getString(R.string.mensaje_lista_usuario_vacia),Toast.LENGTH_LONG);
                        toast.show();
                    }
                }
                catch (Exception e){
                    Toast toast = Toast.makeText(getApplicationContext(), e.getMessage(),Toast.LENGTH_LONG);
                    toast.show();
                }
            }
        });
    }

    public void getUsuarioActual(){

        Usuario usuarioActual = (Usuario) intent.getSerializableExtra("USUARIO_ACTUAL");

        usuario.setText(usuarioActual.getUsuario());
        nombre.setText(usuarioActual.getNombre());
        telefono.setText(usuarioActual.getTelefono());
        email.setText(usuarioActual.getEmail());
        direccion.setText(usuarioActual.getDireccion());
        contrasena.setText(usuarioActual.getContrasena());
    }

    public void getUsuariosSharedPreferences(ListView lv){

        AdapterItem adapter = new AdapterItem(this, listaUsuariosSharedPreference.getListaUsuario());
        lv.setAdapter(adapter);
    }

    public void getUsuariosDataBase(ListView lv){

        userList = DatabaseManager.getInstance().getAllUsers();
        listaUsuariosDB.setListaUsuario(new ArrayList<Usuario>());
        Usuario usuario;
        if(userList != null) {
            for (User usuarioDB: userList) {
                usuario = new Usuario(usuarioDB.nombre, usuarioDB.telefono, usuarioDB.email, usuarioDB.direccion, usuarioDB.usuario, usuarioDB.contrasena);
                listaUsuariosDB.getListaUsuario().add(usuario);
            }
        }
        AdapterItem adapter = new AdapterItem(this, this.listaUsuariosDB.getListaUsuario());
        lv.setAdapter(adapter);
    }
}
