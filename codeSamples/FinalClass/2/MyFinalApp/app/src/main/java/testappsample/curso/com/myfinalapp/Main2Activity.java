package testappsample.curso.com.myfinalapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class Main2Activity extends AppCompatActivity {

    private TextView message, year;
    Intent intentParams;
    String customMessage;
    int customYear;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        intentParams = getIntent();
        //customMessage = intentParams.getStringExtra("customMesage", null);
        customMessage = intentParams.getStringExtra("customMessage");
        customYear = intentParams.getIntExtra("currentYear",0);

        message = (TextView) findViewById(R.id.mymessage);
        year = (TextView) findViewById(R.id.year);

        message.setText(customMessage);
        year.setText(String.valueOf(customYear));
    }
}
