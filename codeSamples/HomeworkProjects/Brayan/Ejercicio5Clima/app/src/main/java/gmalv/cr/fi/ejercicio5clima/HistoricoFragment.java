package gmalv.cr.fi.ejercicio5clima;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

import gmalv.cr.fi.database.DatabaseManager;
import gmalv.cr.fi.model.Clima;

public class HistoricoFragment extends Fragment {

    List<Clima> climas;
    ArrayList<Clima> climaList = new ArrayList();
    ListView lvClima;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_historico, container, false);

        lvClima = (ListView) view.findViewById(R.id.lvClima);

        climas = DatabaseManager.getInstance().getAllWeatherLists();

        if(climas != null) {
            for (Clima clima: climas) {
                climaList.add(clima);
            }
            AdapterItem adapter = new AdapterItem(getActivity(), climaList);
            lvClima.setAdapter(adapter);
        }
        return view;
    }
}
