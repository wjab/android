package weather_finaltest.centaurosolutions.com.weather;

import android.app.Activity;
import android.content.ContentResolver;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Typeface;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import java.util.ArrayList;

import database.DatabaseManager;
import model.WeatherObject;

public class WeatherHistoryTabActivity extends Activity {

    EditText et_filterWeather;
    ListView listViewWeather;
    ArrayList<WeatherObject> weatherObjectArrayList = new ArrayList<WeatherObject>();
    WeatherListAdapter adapter;
    Typeface weatherFont;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_weather_history_tab);

        weatherFont = Typeface.createFromAsset(this.getAssets(), "fonts/weather.ttf");

        et_filterWeather = (EditText)findViewById(R.id.wh_et_filter);
        listViewWeather = (ListView)findViewById(R.id.wh_lv_weather);

        weatherObjectArrayList = (ArrayList<WeatherObject>) DatabaseManager.getInstance().getAllWeatherLists();

        adapter = new WeatherListAdapter(this, weatherObjectArrayList);
        listViewWeather.setAdapter(adapter);

        et_filterWeather.addTextChangedListener(new TextWatcher() {
            @Override
            public void afterTextChanged(Editable s) {
                // TODO Auto-generated method stub
            }

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                // TODO Auto-generated method stub
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                adapter.getFilter().filter(s);
            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_weather_history_tab, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
