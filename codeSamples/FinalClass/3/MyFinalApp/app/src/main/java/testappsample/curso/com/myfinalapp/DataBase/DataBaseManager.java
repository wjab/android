package testappsample.curso.com.myfinalapp.DataBase;

import android.content.Context;

import java.sql.SQLException;
import java.util.List;

import testappsample.curso.com.myfinalapp.Model.Data;

/**
 * Created by Administrador on 20/12/2017.
 */

public class DataBaseManager {

    private static DataBaseManager instance;
    private DataBaseHelper helper;

    public static void init(Context context){
        if (instance == null) {
            instance = new DataBaseManager(context);
        }
    }

    public static DataBaseManager getInstance() {
        return instance;
    }

    private DataBaseManager(Context context) {
        helper = new DataBaseHelper(context);
    }

    private DataBaseHelper getHelper() {
        return helper;
    }

    public List<Data> getAllData() {
        List<Data> DataList = null;
        try {
            DataList = getHelper().getDataListDao().queryForAll();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return DataList;
    }

    public void addData(Data data) {
        try {
            getHelper().getDataListDao().create(data);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
