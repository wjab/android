package testappsample.curso.com.myfinalapp.DataBase;

import android.content.Context;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.j256.ormlite.android.apptools.OrmLiteSqliteOpenHelper;
import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;

import java.util.ArrayList;
import java.util.List;

import testappsample.curso.com.myfinalapp.Model.Data;

/**
 * Created by Administrador on 20/12/2017.
 */

public class DataBaseHelper extends OrmLiteSqliteOpenHelper {

    // name of the database file for your application -- change to something appropriate for your app
    private static final String DATABASE_NAME = "Data.sqlite";

    // any time you make changes to your database objects, you may have to increase the database version
    private static final int DATABASE_VERSION = 1;

    // the DAO object we use to access the SimpleData table
    private Dao<Data, Integer> wishListDao = null;
    private Dao<Data, Integer> wishListDataDao = null;

    public DataBaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase database, ConnectionSource connectionSource) {
        try {
            TableUtils.createTable(connectionSource, Data.class);
            //TableUtils.createTable(connectionSource, UserObject.class);
        } catch (SQLException e) {
            Log.e(DataBaseHelper.class.getName(), "Can't create database", e);
            throw new RuntimeException(e);
        } catch (java.sql.SQLException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, ConnectionSource connectionSource, int oldVersion, int newVersion) {
        try {
            List<String> allSql = new ArrayList<String>();
            switch (oldVersion) {
                case 2: {
                    //allSql.add("alter table User add column `edad` INTEGER");
                    //allSql.add("alter table AdData add column `new_col2` VARCHAR");
                }
                break;
                case 3: {
                    //allSql.add("alter table User add column `address` VARCHAR");
                }
                break;
            }
            for (String sql : allSql) {
                db.execSQL(sql);
            }
        } catch (SQLException e) {
            Log.e(DataBaseHelper.class.getName(), "exception during onUpgrade", e);
            throw new RuntimeException(e);
        }

    }

    public Dao<Data, Integer> getDataListDao() {
        if (null == wishListDao) {
            try {
                wishListDao = getDao(Data.class);
            } catch (java.sql.SQLException e) {
                e.printStackTrace();
            }
        }
        return wishListDao;
    }

    public Dao<Data, Integer> getUserObjectListDao() {
        if (null == wishListDataDao) {
            try {
                wishListDataDao = getDao(Data.class);
            } catch (java.sql.SQLException e) {
                e.printStackTrace();
            }
        }
        return wishListDataDao;
    }
}
