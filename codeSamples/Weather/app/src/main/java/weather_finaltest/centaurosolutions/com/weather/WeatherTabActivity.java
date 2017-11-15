package weather_finaltest.centaurosolutions.com.weather;

import android.app.Activity;
import android.content.Context;
import android.graphics.Typeface;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.TextClock;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;

import org.json.JSONArray;
import org.json.JSONObject;

import java.lang.annotation.ElementType;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import controllers.ServiceController;
import database.DatabaseManager;
import model.WeatherObject;
import utils.GeneralFunctions;

public class WeatherTabActivity extends Activity implements Response.Listener<JSONObject>, Response.ErrorListener, LocationListener
{
    protected LocationManager locationManager;
    protected LocationListener locationListener;
    protected Context context;
    protected boolean gps_enabled,network_enabled;
    protected Double latitude,longitude;
    protected Date date;
    protected boolean busyService;

    TextView tvCityCountry, tvWeatherIcon, tvWeatherDesc, tvWeatherMain, tvTemperature, tvMinMax, tvHumidity, tvWindSpeed, tvSeaLevel;
    TextClock tcDate, tcHour;
    ServiceController serviceController;
    List<WeatherObject> weatherObjectList;
    boolean rowExist;
    GeneralFunctions generalFunctions;
    Typeface weatherFont;


    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_weather_tab);

        weatherFont = Typeface.createFromAsset(this.getAssets(), "fonts/weather.ttf");

        DatabaseManager.init(this);
        busyService = false;

        // GPS Service
        locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 10000, 0, this);

        tvCityCountry = (TextView)findViewById(R.id.w_tv_citycountry);
        tvWeatherIcon = (TextView)findViewById(R.id.w_tv_icon);
        tvWeatherDesc = (TextView)findViewById(R.id.w_tv_weatherDesc);
        tvWeatherMain = (TextView)findViewById(R.id.w_tv_weatherMain);
        tvTemperature = (TextView)findViewById(R.id.w_tv_temp);
        tvMinMax = (TextView)findViewById(R.id.w_tv_tempMinMax);
        tvHumidity = (TextView)findViewById(R.id.w_tv_humidity);
        tvWindSpeed = (TextView)findViewById(R.id.w_tv_windspeed);
        tvSeaLevel = (TextView)findViewById(R.id.w_tv_sealevel);

        tcDate = (TextClock)findViewById(R.id.w_tv_currentdate);
        tcHour = (TextClock)findViewById(R.id.w_tc_hour);

        serviceController = new ServiceController();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_weather_tab, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {
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

    /* ------------------------------ GPS methods implementation ------------------------------------- */
    @Override
    public void onLocationChanged(Location location)
    {
        latitude = location.getLatitude();
        longitude = location.getLongitude();

        String uri = "http://api.openweathermap.org/data/2.5/weather?lat=" + latitude.toString() + "&lon=" + longitude.toString() + "&appid=e6ac45f980a98a86771e44a949a290ad"; // "http://api.openweathermap.org/data/2.5/weather?lat="+ latitude +"&lon=" + longitude;

        if(!busyService) {
            serviceController.jsonObjectRequest(uri, Request.Method.GET, null, this, this);
        }
    }

    @Override
    public void onProviderDisabled(String provider)
    {
        //Toast.makeText(getBaseContext(), "Latitude disable", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onProviderEnabled(String provider)
    {
        //Toast.makeText(getBaseContext(), "Latitude enable", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onStatusChanged(String provider, int status, Bundle extras)
    {
        //Toast.makeText(getBaseContext(), "onStatusChanged " + String.valueOf(status), Toast.LENGTH_SHORT).show();
    }

    /* ----------------------------------------------------------------------------------------------- */

    /* Web services Request response methods */
    @Override
    public void onResponse(JSONObject response)
    {
        JSONArray jsonArray;
        JSONObject jsonObject;
        generalFunctions = new GeneralFunctions();

        try
        {
            WeatherObject weather = new WeatherObject();

            jsonArray = response.getJSONArray("weather");
            jsonObject = jsonArray.getJSONObject(0);
            weather.WeatherDescription = jsonObject.getString("description");
            weather.WeatherMain = jsonObject.getString("main");

            jsonObject = response.getJSONObject("main");
            weather.Temperature = generalFunctions.ConvertToCelsiusDegrees(jsonObject.getDouble("temp")) ;
            weather.MaxTemperature = generalFunctions.ConvertToCelsiusDegrees(jsonObject.getDouble("temp_max"));
            weather.MinTemperature = generalFunctions.ConvertToCelsiusDegrees(jsonObject.getDouble("temp_min"));
            weather.Humidity = generalFunctions.ConvertToPercentage(jsonObject.getDouble("humidity"));
            //weather.SeaLevel = jsonObject.getDouble("sea_level");

            DateFormat dateFormat = new SimpleDateFormat(getText(R.string.fulltime).toString());
            date = new Date();
            weather.Date = dateFormat.format(date);

            jsonObject = response.getJSONObject("wind");
            weather.WindSpeed = generalFunctions.ConvertToSpeed(jsonObject.getDouble("speed"));

            jsonObject = response.getJSONObject("sys");
            weather.Country = jsonObject.getString("country");
            weather.Sunrise = jsonObject.getLong("sunrise");
            weather.Sunset = jsonObject.getLong("sunset");
            weather.City = response.getString("name");

            tvWeatherDesc.setText(weather.WeatherDescription);
            tvWeatherMain.setText(weather.WeatherMain);
            tvTemperature.setText(String.valueOf(weather.Temperature));
            tvMinMax.setText(String.valueOf(weather.MinTemperature) + " / " + String.valueOf(weather.MaxTemperature));
            tvHumidity.setText(String.valueOf(weather.Humidity) );
            tvSeaLevel.setText(String.valueOf(weather.SeaLevel) );
            tvWindSpeed.setText(String.valueOf(weather.WindSpeed) );
            tvCityCountry.setText(weather.City + ", " + weather.Country);

            tvWeatherIcon.setText(GetIcon(weather.WeatherMain));
            tvWeatherIcon.setTypeface(weatherFont);

            weatherObjectList = DatabaseManager.getInstance().getAllWeatherLists();

            if(weatherObjectList.size() == 0 || weatherObjectList == null)
            {
                DatabaseManager.getInstance().addWeather(weather);
            }
            else
            {
                for (WeatherObject wo : weatherObjectList)
                {
                    if (wo.City.equals(weather.City) && wo.Date.equals(weather.Date))
                    {
                        rowExist = true;
                        break;
                    }
                    else
                    {
                        rowExist = false;
                    }
                }

                if (rowExist)
                {
                    DatabaseManager.getInstance().updateWeather(weather);
                }
                else
                {
                    DatabaseManager.getInstance().addWeather(weather);
                }
            }
        }
        catch (Exception ex)
        {
            Toast.makeText(getBaseContext(), "Response exception " + ex.getMessage().toString(), Toast.LENGTH_SHORT).show();
        }
        finally
        {
            busyService = false;
        }
    }

    public String GetIcon(String weatherMain)
    {
        String icon;
        switch (weatherMain)
        {
            case "Rain":
            {
                icon = getString(R.string.weather_rainy);
            }break;

            case "Fog":
            {
                icon = getString(R.string.weather_foggy);
            }break;

            case "Cloud":
            {
                icon = getString(R.string.weather_cloudy);
            }break;

            case "Snow":
            {
                icon = getString(R.string.weather_snowy);
            }break;

            case "Drizzle":
            {
                icon = getString(R.string.weather_drizzle);
            }break;

            case "Thunder":
            {
                icon = getString(R.string.weather_thunder);
            }break;

            default:
            {
                icon = getString(R.string.weather_sunny);
            }break;
        }

        return icon;
    }

    @Override
    public void onErrorResponse(VolleyError error)
    {
        Toast.makeText(getBaseContext(), "VolleyError " + error.toString(), Toast.LENGTH_SHORT).show();
        busyService = false;
    }
}
