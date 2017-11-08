package model;

import com.j256.ormlite.field.DatabaseField;

/**
 * Created by Administrador on 7/11/2017.
 */

public class User {

    @DatabaseField
    public String user;

    @DatabaseField
    public String password;

    @DatabaseField
    public String name;

    @DatabaseField
    public String lastName;

    //public int edad;
}
