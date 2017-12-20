package testappsample.curso.com.myfinalapp;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.AppCompatButton;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import testappsample.curso.com.myfinalapp.DataBase.DataBaseManager;
import testappsample.curso.com.myfinalapp.Model.Data;

public class Main2Activity extends AppCompatActivity {

    private TextView message, year;
    private AppCompatButton button;
    Intent intentParams;
    String customMessage, customYear;

    Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        context = this;

        intentParams = getIntent();
        Data data = (Data)intentParams.getSerializableExtra("data");

        /*customMessage = intentParams.getStringExtra("one");
        customYear = intentParams.getStringExtra("two");*/

        message = (TextView) findViewById(R.id.mymessage);
        year = (TextView) findViewById(R.id.year);
        button = (AppCompatButton) findViewById(R.id.btnSave);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    Data data = new Data();
                    data.message = message.getText().toString();
                    data.year = year.getText().toString();

                    DataBaseManager.getInstance().addData(data);
                    Toast.makeText(context, getString(R.string.database_created), Toast.LENGTH_SHORT).show();
                }
                catch (Exception ex)
                {
                    Log.e(ex.getMessage(), getString(R.string.database_not_created));
                    Toast.makeText(context, getString(R.string.database_not_created), Toast.LENGTH_SHORT).show();
                }
            }
        });

        message.setText(customMessage);
        year.setText( customYear );
    }
}
