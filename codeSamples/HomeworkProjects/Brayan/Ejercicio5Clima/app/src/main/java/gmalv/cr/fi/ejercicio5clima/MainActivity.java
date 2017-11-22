package gmalv.cr.fi.ejercicio5clima;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Typeface;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextClock;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;

import org.json.JSONArray;
import org.json.JSONObject;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import gmalv.cr.fi.controllers.ServiceController;
import gmalv.cr.fi.database.DatabaseManager;
import gmalv.cr.fi.model.Clima;
import gmalv.cr.fi.utils.GeneralFunctions;

public class MainActivity extends AppCompatActivity implements Response.Listener<JSONObject>, Response.ErrorListener, LocationListener {

    TextView lblClima, lblDescripcionClima, lblIconoClima, lblTemperatura, lblMinMax, lblHumedad, lblViento, lblNivelMar, lblCiudad;
    TextClock tcFecha, tcHora;
    protected LocationManager locationManager;
    ServiceController serviceController;
    protected Double latitude = 10.0;
    protected Double longitude = -84.0;
    protected boolean busyService;
    protected Date date;
    GeneralFunctions generalFunctions;
    Typeface weatherFont;
    Button btnHistorico, btnActualizar;
    Clima clima;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        DatabaseManager.init(this);
        serviceController = new ServiceController();

        if (ActivityCompat.checkSelfPermission(MainActivity.this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(MainActivity.this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION},123);
        }
        else{
            initLocationManager();
            upDateWeather(null);
        }

        weatherFont = Typeface.createFromAsset(this.getAssets(), "fonts/weather.ttf");

        lblClima = (TextView) findViewById(R.id.lblClima);
        lblDescripcionClima = (TextView) findViewById(R.id.lblDescripcionClima);
        lblIconoClima = (TextView) findViewById(R.id.lblIconoClima);
        lblTemperatura = (TextView)findViewById(R.id.lblTemperatura);
        lblMinMax = (TextView)findViewById(R.id.lblTempMinMax);
        lblHumedad = (TextView)findViewById(R.id.lblHumedadInfo);
        lblViento = (TextView)findViewById(R.id.lblVientoInfo);
        lblNivelMar = (TextView)findViewById(R.id.lblNivelMarInfo);
        lblCiudad = (TextView)findViewById(R.id.lblCiudad);

        tcFecha = (TextClock)findViewById(R.id.tcHora);
        tcHora = (TextClock)findViewById(R.id.tcHora);

        btnHistorico = (Button) findViewById(R.id.btnHistorico);
        btnActualizar = (Button) findViewById(R.id.btnActualizar);

        btnHistorico.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                try{
                    Intent intent = new Intent(getApplicationContext(), HistoricoClima.class);
                    startActivity(intent);
                }

                catch (Exception e){
                    Toast toast = Toast.makeText(getApplicationContext(), e.getMessage(),Toast.LENGTH_SHORT);
                    toast.show();
                }
            }
        });

        btnActualizar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                upDateWeather(null);
            }
        });
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String permissions[], int[] grantResults) {
        switch (requestCode) {
            case 123: {
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    initLocationManager();
                    upDateWeather(null);
                }
                else
                {
                    Toast.makeText(getApplicationContext(),"Permisos de ubicación no aceptados", Toast.LENGTH_LONG).show();
                }
                return;
            }
        }
    }

    @Override
    public void onLocationChanged(Location location) {

        upDateWeather(location);
    }

    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {

    }

    @Override
    public void onProviderEnabled(String provider) {

    }

    @Override
    public void onProviderDisabled(String provider) {

    }

    @Override
    public void onErrorResponse(VolleyError error) {
        Toast.makeText(getBaseContext(), "VolleyError " + error.toString(), Toast.LENGTH_SHORT).show();
        busyService = false;
    }

    @Override
    public void onResponse(JSONObject response) {

        JSONArray jsonArray;
        JSONObject jsonObject;
        generalFunctions = new GeneralFunctions();

        try
        {
            clima = new Clima();

            jsonArray = response.getJSONArray("weather");
            jsonObject = jsonArray.getJSONObject(0);

            clima.setWeatherDescription(jsonObject.getString("description"));
            clima.setWeatherMain(jsonObject.getString("main"));

            DateFormat dateFormat = new SimpleDateFormat(getText(R.string.fulltime).toString());
            date = new Date();
            clima.setDate(dateFormat.format(date));

            jsonObject = response.getJSONObject("main");
            clima.setTemperature(generalFunctions.ConvertToCelsiusDegrees(jsonObject.getDouble("temp")));
            clima.setMaxTemperature(generalFunctions.ConvertToCelsiusDegrees(jsonObject.getDouble("temp_max")));
            clima.setMinTemperature(generalFunctions.ConvertToCelsiusDegrees(jsonObject.getDouble("temp_min")));
            clima.setHumidity(generalFunctions.ConvertToPercentage(jsonObject.getDouble("humidity")));

            jsonObject = response.getJSONObject("wind");
            clima.setWindSpeed(generalFunctions.ConvertToSpeed(jsonObject.getDouble("speed")));

            jsonObject = response.getJSONObject("sys");
            clima.setCountry(jsonObject.getString("country"));
            clima.setCity(response.getString("name"));

            DatabaseManager.getInstance().addWeather(clima);

            lblDescripcionClima.setText(clima.getWeatherDescription());
            lblClima.setText(clima.getWeatherMain());
            lblIconoClima.setText(GetIcon(clima.getWeatherMain()));
            lblIconoClima.setTypeface(weatherFont);
            lblTemperatura.setText(String.valueOf(clima.getTemperature()));
            lblMinMax.setText(String.valueOf(clima.getMinTemperature() + " - " + clima.getMaxTemperature()));
            lblHumedad.setText(String.valueOf(clima.getHumidity()) );
            lblNivelMar.setText(String.valueOf(clima.getSeaLevel()) );
            lblViento.setText(String.valueOf(clima.getWindSpeed()) );
            lblCiudad.setText(clima.getCity() + ", " + clima.getCountry());
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

    public void initLocationManager(){
        if (ActivityCompat.checkSelfPermission(MainActivity.this, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
            locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
            locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 10000, 0, this);
        }
    }

    public void upDateWeather(Location location){

        if(location != null){
            latitude = location.getLatitude();
            longitude = location.getLongitude();
        }

        String uri = "http://api.openweathermap.org/data/2.5/weather?lat=" + latitude.toString() + "&lon=" + longitude.toString() + "&appid=e6ac45f980a98a86771e44a949a290ad"; // "http://api.openweathermap.org/data/2.5/weather?lat="+ latitude +"&lon=" + longitude;

        if(!busyService) {
            serviceController.jsonObjectRequest(uri, Request.Method.GET, null, this, this);
        }
    }
}
