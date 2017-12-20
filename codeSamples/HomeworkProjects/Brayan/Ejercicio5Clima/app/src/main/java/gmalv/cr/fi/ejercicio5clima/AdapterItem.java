package gmalv.cr.fi.ejercicio5clima;

import android.app.Activity;
import android.content.Context;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

import gmalv.cr.fi.model.Clima;


public class AdapterItem extends BaseAdapter {

    protected Activity activity;
    protected ArrayList<Clima> items;
    Typeface weatherFont;
    Context context;

    public AdapterItem (Activity activity, ArrayList<Clima> items) {
        this.activity = activity;
        this.items = items;
        context = this.activity;
    }

    @Override
    public int getCount() {
        return items.size();
    }

    public void clear() {
        items.clear();
    }

    public void addAll(ArrayList<Clima> usuarios) {
        for (int i = 0; i < usuarios.size(); i++) {
            items.add(usuarios.get(i));
        }
    }

    @Override
    public Object getItem(int arg0) {
        return items.get(arg0);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View v = convertView;

        weatherFont = Typeface.createFromAsset(context.getAssets(), "fonts/weather.ttf");

        if (convertView == null) {
            LayoutInflater inf = (LayoutInflater) activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            v = inf.inflate(R.layout.list_item, null);
        }

        Clima clima = items.get(position);

        TextView title = (TextView) v.findViewById(R.id.category);
        title.setText(clima.getCity() + " - " + clima.getCountry());

        TextView description = (TextView) v.findViewById(R.id.texto);
        description.setText(clima.getTemperature() + " " + clima.getWeatherDescription()+ " " + clima.getDate());

        TextView icon = (TextView) v.findViewById(R.id.lblIconoClima);
        icon.setText(GetIcon(clima.getWeatherMain()));
        icon.setTypeface(weatherFont);

        return v;
    }

    public String GetIcon(String weatherMain)
    {
        String icon;
        switch (weatherMain)
        {
            case "Rain":
            {
                icon = context.getString(R.string.weather_rainy);
            }break;

            case "Fog":
            {
                icon = context.getString(R.string.weather_foggy);
            }break;

            case "Cloud":
            {
                icon = context.getString(R.string.weather_cloudy);
            }break;

            case "Snow":
            {
                icon = context.getString(R.string.weather_snowy);
            }break;

            case "Drizzle":
            {
                icon = context.getString(R.string.weather_drizzle);
            }break;

            case "Thunder":
            {
                icon = context.getString(R.string.weather_thunder);
            }break;

            default:
            {
                icon = context.getString(R.string.weather_sunny);
            }break;
        }

        return icon;
    }
}