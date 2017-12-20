package testappsample.curso.com.myfinalapp.Model;

import com.j256.ormlite.field.DatabaseField;

import java.io.Serializable;

/**
 * Created by Administrador on 20/12/2017.
 */

public class Data implements Serializable {

    @DatabaseField
    public String message;

    @DatabaseField
    public String year;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }
}
