package gmalv.cr.fi.model;

import com.j256.ormlite.field.DatabaseField;

import java.io.Serializable;
import java.sql.Date;
import java.text.DecimalFormat;
import java.text.NumberFormat;

/**
 * Created by Administrador on 9/10/2015.
 */

public class Clima implements Serializable
{
    public Clima()
    {

    }

    @DatabaseField
    private String Icon;

    @DatabaseField
    private String Temperature;

    @DatabaseField
    private String MinTemperature;

    @DatabaseField
    private String MaxTemperature;

    @DatabaseField
    private String Humidity;

    @DatabaseField
    private String WindSpeed;

    @DatabaseField
    private double SeaLevel;

    @DatabaseField
    private String City;

    @DatabaseField
    private String Country;

    @DatabaseField
    private String WeatherDescription;

    @DatabaseField
    private String WeatherMain;

    @DatabaseField
    private String Date;

    public String getIcon() {
        return Icon;
    }

    public void setIcon(String icon) {
        Icon = icon;
    }

    public String getTemperature() {
        return Temperature;
    }

    public void setTemperature(String temperature) {
        Temperature = temperature;
    }

    public String getMinTemperature() {
        return MinTemperature;
    }

    public void setMinTemperature(String minTemperature) {
        MinTemperature = minTemperature;
    }

    public String getMaxTemperature() {
        return MaxTemperature;
    }

    public void setMaxTemperature(String maxTemperature) {
        MaxTemperature = maxTemperature;
    }

    public String getHumidity() {
        return Humidity;
    }

    public void setHumidity(String humidity) {
        Humidity = humidity;
    }

    public String getWindSpeed() {
        return WindSpeed;
    }

    public void setWindSpeed(String windSpeed) {
        WindSpeed = windSpeed;
    }

    public double getSeaLevel() {
        return SeaLevel;
    }

    public void setSeaLevel(double seaLevel) {
        SeaLevel = seaLevel;
    }

    public String getCity() {
        return City;
    }

    public void setCity(String city) {
        City = city;
    }

    public String getCountry() {
        return Country;
    }

    public void setCountry(String country) {
        Country = country;
    }

    public String getWeatherDescription() {
        return WeatherDescription;
    }

    public void setWeatherDescription(String weatherDescription) {
        WeatherDescription = weatherDescription;
    }

    public String getWeatherMain() {
        return WeatherMain;
    }

    public void setWeatherMain(String weatherMain) {
        WeatherMain = weatherMain;
    }

    public String getDate() {
        return Date;
    }

    public void setDate(String date) {
        Date = date;
    }
}
