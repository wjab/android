package testappsample.curso.com.myfinalapp;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
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

import testappsample.curso.com.myfinalapp.Controller.ServiceController;

public class MainActivity extends AppCompatActivity implements Response.Listener<JSONObject>, Response.ErrorListener, LocationListener {

    protected LocationManager locationManager;
    ServiceController serviceController;
    protected Double latitude,longitude;
    protected boolean busyService;


    private TextView tv_country, tv_city;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        serviceController = new ServiceController();

        tv_country = (TextView) findViewById(R.id.country);
        tv_city = (TextView) findViewById(R.id.city);

        // GPS Service
        locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }

        locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 10000, 0, this);

        tv_country = (TextView) findViewById(R.id.country);
        tv_city = (TextView) findViewById(R.id.city);


        /*
        Ejemplo de un request (GET). Pueden pegar el url en el browser para ver el resultado de los campos que deben de capturar

        http://api.openweathermap.org/data/2.5/weather?lat=15&lon=12&appid=e6ac45f980a98a86771e44a949a290ad


         */
    }

    @Override
    public void onLocationChanged(Location location) {

        latitude = location.getLatitude();
        longitude = location.getLongitude();

        LoadWeatherData(latitude, longitude);
    }

    public void RefreshLocation(View view)
    {
        LoadWeatherData(15, 12);
    }

    public void LoadWeatherData(double latitude, double longitude)
    {
        String uri = "http://api.openweathermap.org/data/2.5/weather?lat=" + latitude + "&lon=" + longitude + "&appid=e6ac45f980a98a86771e44a949a290ad";

        if(!busyService) {
            serviceController.jsonObjectRequest(uri, Request.Method.GET, null, this, this);
        }
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
        JSONObject jsonObject;
        //JSONArray jsonArray;

        try
        {
            //jsonArray = response.getJSONObject("sys");
            jsonObject = response.getJSONObject("sys");
            tv_country.setText( jsonObject.getString("country") );
            tv_city.setText(response.getString("name"));
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
}
