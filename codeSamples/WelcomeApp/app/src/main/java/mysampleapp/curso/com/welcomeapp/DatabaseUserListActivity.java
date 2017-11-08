package mysampleapp.curso.com.welcomeapp;

import android.content.Intent;
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
import java.util.List;

import Database.DatabaseManager;
import model.User;

public class DatabaseUserListActivity extends AppCompatActivity {

    ListView list;
    CustomListAdapter adapter;
    ListUserObject listUser = new ListUserObject();
    List<User> userList;
    TextView searchuserName;
    Gson gson;

    ArrayList<UserObject> arrayList = new ArrayList<UserObject>();
    UserObject userObjectItem;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_database_user_list);
        gson = new Gson();

        userList = DatabaseManager.getInstance().getAllUsers();


        if(userList != null) {
            for (User item: userList) {
                userObjectItem = new UserObject();
                userObjectItem.setName(item.name);
                userObjectItem.setLastName(item.lastName);
                userObjectItem.setUser(item.user);
                userObjectItem.setPassword(item.password);
                arrayList.add(userObjectItem);
            }

            listUser.setUserObjectsList(arrayList);
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
