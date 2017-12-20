package testappsample.curso.com.myfinalapp;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.AppCompatButton;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;

import java.util.ArrayList;

import testappsample.curso.com.myfinalapp.DataBase.DataBaseManager;
import testappsample.curso.com.myfinalapp.Model.Data;
import testappsample.curso.com.myfinalapp.Model.ListDataObject;

public class Main2Activity extends AppCompatActivity {

    private TextView message, year;
    private AppCompatButton button, btnSavePref;
    Intent intentParams;
    String customMessage, customYear;

    private ListDataObject tempListDataObject;

    Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

       context = this;

        intentParams = getIntent();
        customMessage = intentParams.getStringExtra("one");
        customYear = intentParams.getStringExtra("two");

        message = (TextView) findViewById(R.id.mymessage);
        year = (TextView) findViewById(R.id.year);
        button = (AppCompatButton) findViewById(R.id.btnSave);
        btnSavePref = (AppCompatButton) findViewById(R.id.btnSavePreferences);

       // DataBaseManager.getInstance().getAllData();

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    Data data = new Data();
                    data.message = message.getText().toString();
                    data.year = year.getText().toString();

                    DataBaseManager.getInstance().addData(data);

                    Toast.makeText(context, getString(R.string.database_created), Toast.LENGTH_SHORT).show();
                }
                catch (Exception ex)
                {
                    Log.e(ex.getMessage(), getString(R.string.database_not_created));
                    Toast.makeText(context, getString(R.string.database_not_created), Toast.LENGTH_SHORT).show();
                }
            }
        });

        btnSavePref.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences preferences = getSharedPreferences("MyPreferences", Context.MODE_PRIVATE);
                //SharedPreferences prefs = getSharedPreferences("MyPreferences", MODE_PRIVATE);
                String stringListUser = preferences.getString("ListData", null);
                Gson gson = new Gson();

                ArrayList<Data> DataListTemporal = new  ArrayList<Data>();

                if(stringListUser != null) {
                    tempListDataObject = gson.fromJson(stringListUser, ListDataObject.class);
                    DataListTemporal = tempListDataObject.getUserObjectsList();
                }
                else {
                    tempListDataObject = new ListDataObject();
                }

                SharedPreferences.Editor editor = preferences.edit();

                Data data = new Data();
                data.message = message.getText().toString();
                data.year = year.getText().toString();

                DataListTemporal.add(data);

                tempListDataObject.setUserObjectsList(DataListTemporal);
                String stringJson = gson.toJson(tempListDataObject);

                editor.putString("ListData", stringJson);
                editor.commit();
                Toast.makeText(context, "Data registrada", Toast.LENGTH_SHORT).show();
            }
        });

        message.setText(customMessage);
        year.setText( customYear );
    }
}
