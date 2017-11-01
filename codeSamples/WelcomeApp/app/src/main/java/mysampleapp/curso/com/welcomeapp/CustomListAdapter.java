package mysampleapp.curso.com.welcomeapp;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by Administrador on 31/10/2017.
 */

public class CustomListAdapter extends ArrayAdapter<UserObject> implements Filterable {

    TextView txtTitle, extratxt;
    ImageView imageView;

    public  Activity context;
    public ArrayList<UserObject> itemname;
    public ArrayList<UserObject> mStringFilterList;
    ValueFilter valueFilter;

    public CustomListAdapter(Activity context, ListUserObject itemname) {
        super(context, R.layout.mylist, itemname.getUserObjectsList());
        // TODO Auto-generated constructor stub

        this.context = context;
        this.itemname = itemname.getUserObjectsList();
        mStringFilterList = itemname.getUserObjectsList();
    }

   /*public void setArray(ListUserObject itemname){
        this.itemname = itemname.getUserObjectsList();
    }*/

    public View getView(int position, View view, ViewGroup parent) {
        LayoutInflater inflater=context.getLayoutInflater();
        View rowView=inflater.inflate(R.layout.mylist, null, true);

        txtTitle = (TextView) rowView.findViewById(R.id.item);
        imageView = (ImageView) rowView.findViewById(R.id.icon);
        extratxt = (TextView) rowView.findViewById(R.id.textView1);

        txtTitle.setText(itemname.get(position).getUser());
        imageView.setImageResource(R.drawable.businessman);
        extratxt.setText(itemname.get(position).getName()+" "+itemname.get(position).getLastName());
        return rowView;
    }

    @Override
    public int getCount() {
        return itemname.size();
    }

    @Override
    public UserObject getItem(int position) {
        return itemname.get(position);
    }

    @Override
    public long getItemId(int position) {
        return itemname.indexOf(getItem(position));
    }

    @Override
    public Filter getFilter() {
        if (valueFilter == null) {
            valueFilter = new ValueFilter();
        }
        return valueFilter;
    }

    private class ValueFilter extends Filter {
        @Override
        protected FilterResults performFiltering(CharSequence constraint) {
            FilterResults results = new FilterResults();

            if (constraint != null && constraint.length() > 0) {
                ArrayList<UserObject> filterList = new ArrayList<UserObject>();
                for (int i = 0; i < mStringFilterList.size(); i++) {
                    if ( (mStringFilterList.get(i).getUser().toUpperCase() )
                            .contains(constraint.toString().toUpperCase())) {

                        UserObject user = new UserObject();
                        user.setLastName(mStringFilterList.get(i).getLastName());
                        user.setName(mStringFilterList.get(i).getName());
                        user.setUser(mStringFilterList.get(i).getUser());
                        filterList.add(user);
                    }
                }
                results.count = filterList.size();
                results.values = filterList;
            } else {
                results.count = mStringFilterList.size();
                results.values = mStringFilterList;
            }
            return results;

        }

        @Override
        protected void publishResults(CharSequence constraint,
                                      FilterResults results) {
            itemname = (ArrayList<UserObject>) results.values;
            notifyDataSetChanged();
        }
    }

}
