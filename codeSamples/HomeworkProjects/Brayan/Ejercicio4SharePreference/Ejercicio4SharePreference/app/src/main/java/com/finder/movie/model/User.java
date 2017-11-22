package com.finder.movie.model;

import com.j256.ormlite.field.DatabaseField;

/**
 * Created by Administrador on 7/11/2017.
 */

public class User {

    @DatabaseField
    public String nombre;

    @DatabaseField
    public String telefono;

    @DatabaseField
    public String email;

    @DatabaseField
    public String direccion;

    @DatabaseField
    public String usuario;

    @DatabaseField
    public String contrasena;

    //public int edad;
}
