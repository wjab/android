package mysampleapp.curso.com.welcomeapp;

import java.io.Serializable;

/**
 * Created by Administrador on 24/10/2017.
 */

public class UserObject implements Serializable{

    private String user = "";
    private String password = "";
    private String name = "";
    private String lastName = "";

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
}
