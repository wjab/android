package database;

import android.content.Context;

import java.sql.SQLException;
import java.util.List;

import model.WeatherObject;

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

    public List<WeatherObject> getAllWeatherLists() {
        List<WeatherObject> WeatherLists = null;
        try {
            WeatherLists = getHelper().getUserListDao().queryForAll();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return WeatherLists;
    }

    public void addWeather(WeatherObject weatherObject) {
        try {
            getHelper().getUserListDao().create(weatherObject);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void updateWeather(WeatherObject weatherObject) {
        try {
            getHelper().getUserListDao().update(weatherObject);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
