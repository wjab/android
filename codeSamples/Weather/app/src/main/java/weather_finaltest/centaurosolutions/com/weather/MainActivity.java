package weather_finaltest.centaurosolutions.com.weather;

import android.app.TabActivity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.Window;
import android.widget.TabHost;
import android.widget.TextView;

public class MainActivity extends TabActivity implements TabHost.OnTabChangeListener
{
    TabHost tabHost;
    TabHost.TabSpec spec;
    Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_main);

        tabHost = getTabHost();

        // Set TabChangeListener called when tab changed
        tabHost.setOnTabChangedListener(this);

        intent = new Intent(this, WeatherTabActivity.class);
        spec = tabHost.newTabSpec("First").setIndicator("Weather").setContent(intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP));
        tabHost.addTab(spec);

        intent = new Intent(this, WeatherHistoryTabActivity.class);
        spec = tabHost.newTabSpec("Second").setIndicator("Weather History").setContent(intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP));
        tabHost.addTab(spec);

        tabHost.getTabWidget().getChildAt(1).setBackgroundResource(R.color.defaultTabColor);
        tabHost.getTabWidget().getChildAt(1).setBackgroundResource(R.color.defaultTabColor);

        tabHost.getTabWidget().setCurrentTab(0);
        tabHost.getTabWidget().getChildAt(0).setBackgroundResource(R.color.selectedTab);

        for(int i=0;i<tabHost.getTabWidget().getChildCount();i++)
        {
            TextView tv = (TextView) tabHost.getTabWidget().getChildAt(i).findViewById(android.R.id.title);
            tv.setAllCaps(false);
            tv.setTextColor(Color.WHITE);
            tv.setTextSize(20);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
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

    @Override
    public void onTabChanged(String tabId) {
        for(int i=0;i<tabHost.getTabWidget().getChildCount();i++)
        {
            if(i==0)
            {
                tabHost.getTabWidget().getChildAt(i).setBackgroundResource(R.color.defaultTabColor);
            }
            else if(i==1)
            {
                tabHost.getTabWidget().getChildAt(i).setBackgroundResource(R.color.defaultTabColor);
            }

        }

        //Log.i("tabs", "CurrentTab: " + tabHost.getCurrentTab());

        if(tabHost.getCurrentTab()==0)
        {
            tabHost.getTabWidget().getChildAt(tabHost.getCurrentTab()).setBackgroundResource(R.color.selectedTab);
        }
        else if(tabHost.getCurrentTab()==1)
        {
            tabHost.getTabWidget().getChildAt(tabHost.getCurrentTab()).setBackgroundResource(R.color.selectedTab);
        }
        else if(tabHost.getCurrentTab()==2)
        {
            tabHost.getTabWidget().getChildAt(tabHost.getCurrentTab()).setBackgroundResource(R.color.selectedTab);
        }
    }



}
