package com.finder.movie.ejercicio4sharepreference;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Bryan on 1/11/17.
 */

public class ListaUsuario implements Serializable{

    private ArrayList<Usuario> listaUsuario;

    public ArrayList<Usuario> getListaUsuario() {
        return listaUsuario;
    }

    public void setListaUsuario(ArrayList<Usuario> listaUsuario) {
        this.listaUsuario = listaUsuario;
    }
}
