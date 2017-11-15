package weather_finaltest.centaurosolutions.com.weather;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.TextView;

import java.util.ArrayList;

import model.WeatherObject;

/**
 * Created by cajon_000 on 12/09/2015.
 */
public class WeatherListAdapter extends ArrayAdapter<WeatherObject> implements Filterable
{
    // Private variables declaration
    public Activity _context;
    private ArrayList<WeatherObject> _arrayWeather, _filteredWeather;
    private ValueFilter valueFilter;

    public WeatherListAdapter(Activity activity, ArrayList<WeatherObject> weatherObjectList)
    {
        super(activity, R.layout.activity_weather_list, weatherObjectList);
        _context = activity;
        _arrayWeather = weatherObjectList;
        _filteredWeather = weatherObjectList;
    }

    public void setArray(ArrayList<WeatherObject> arrayContact)
    {
        this._arrayWeather = arrayContact;
    }

    @Override
    public int getCount() {
        return _arrayWeather.size();
    }

    @Override
    public WeatherObject getItem(int position) {
        return _arrayWeather.get(position);
    }

    @Override
    public long getItemId(int position) {
        return _arrayWeather.indexOf(getItem(position));
    }

    @Override
    public View getView(int position, View view, ViewGroup parent) {
        LayoutInflater inflater = _context.getLayoutInflater();
        View rowView = inflater.inflate(R.layout.activity_weather_list, null, true);

        TextView tvIcon = (TextView) rowView.findViewById(R.id.wl_icon);
        TextView tvCity = (TextView) rowView.findViewById(R.id.wl_city);
        TextView tvDate = (TextView) rowView.findViewById(R.id.wl_date);

        tvIcon.setText(_arrayWeather.get(position).WeatherMain);
        tvCity.setText(_arrayWeather.get(position).City);
        tvDate.setText(_arrayWeather.get(position).Date);

        return rowView;
    }

    @Override
    public Filter getFilter()
    {
        if (valueFilter == null)
        {
            valueFilter = new ValueFilter();
        }
        return valueFilter;
    }

    private class ValueFilter extends Filter
    {
        @Override
        protected FilterResults performFiltering(CharSequence constraint)
        {
            FilterResults results = new FilterResults();

            if (constraint != null && constraint.length() > 0) {
                ArrayList<WeatherObject> filterList = new ArrayList<WeatherObject>();
                for (int i = 0; i < _filteredWeather.size(); i++) {
                    if ((_filteredWeather.get(i).City.toUpperCase())
                            .contains(constraint.toString().toUpperCase())) {

                        WeatherObject newWeather = new WeatherObject();
                        newWeather.City =_filteredWeather.get(i).City;
                        filterList.add(newWeather);
                    }
                }
                results.count = filterList.size();
                results.values = filterList;
            }
            else
            {
                results.count = _filteredWeather.size();
                results.values = _filteredWeather;
            }
            return results;
        }

        @Override
        protected void publishResults(CharSequence constraint, FilterResults results)
        {
            _arrayWeather = (ArrayList<WeatherObject>) results.values;
            notifyDataSetChanged();
        }
    }
}
