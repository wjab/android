package mysampleapp.curso.com.welcomeapp;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import com.google.gson.Gson;

import java.util.ArrayList;

public class activity_user_list extends AppCompatActivity {

    ListView list;
    CustomListAdapter adapter;
    ListUserObject listUser;
    TextView searchuserName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_list);

        SharedPreferences preferences = getSharedPreferences("MyPreferences", Context.MODE_PRIVATE);
        //SharedPreferences prefs = getSharedPreferences("MyPreferences", MODE_PRIVATE);
        String stringListUser = preferences.getString("ListUsers", null);
        Gson gson = new Gson();

        if(stringListUser != null) {
            listUser = gson.fromJson(stringListUser, ListUserObject.class);
        }
        else{
            listUser = new ListUserObject();
        }

        adapter = new CustomListAdapter(this, listUser);

        list = (ListView) findViewById(R.id.list);
        list.setAdapter(adapter);
        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                UserObject user = listUser.getUserObjectsList().get(position);
                Intent intentSuccess = new Intent(getApplicationContext(), detail.class);
                intentSuccess.putExtra("UserDetail", user);
                startActivity(intentSuccess);
            }
        });

        searchuserName = (TextView) findViewById(R.id.searchuserName);
        searchuserName.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                adapter.getFilter().filter(s);
            }
        });


    }
}
