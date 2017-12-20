package gmalv.cr.fi.database;

import android.content.Context;

import java.sql.SQLException;
import java.util.List;

import gmalv.cr.fi.model.Clima;

public class DatabaseManager {

    private static DatabaseManager instance;
    private DatabaseHelper helper;

    public static void init(Context context){
        if (instance == null) {
            instance = new DatabaseManager(context);
        }
    }

    public static DatabaseManager getInstance() {
        return instance;
    }

    private DatabaseManager(Context context) {
        helper = new DatabaseHelper(context);
    }

    private DatabaseHelper getHelper() {
        return helper;
    }

    public List<Clima> getAllWeatherLists() {
        List<Clima> WeatherLists = null;
        try {
            WeatherLists = getHelper().getUserListDao().queryForAll();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return WeatherLists;
    }

    public void addWeather(Clima clima) {
        try {
            getHelper().getUserListDao().create(clima);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void updateWeather(Clima clima) {
        try {
            getHelper().getUserListDao().update(clima);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
