package com.finder.movie.Database;

import android.content.Context;

import com.finder.movie.model.User;

import java.sql.SQLException;
import java.util.List;


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

    public List<User> getAllUsers() {
        List<User> UserList = null;
        try {
            UserList = getHelper().getUserListDao().queryForAll();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return UserList;
    }

    public void addUser(User user) {
        try {
            getHelper().getUserListDao().create(user);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void updateWeather(User user) {
        try {
            getHelper().getUserListDao().update(user);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
