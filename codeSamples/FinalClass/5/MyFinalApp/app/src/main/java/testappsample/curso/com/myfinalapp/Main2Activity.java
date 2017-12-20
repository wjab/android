package testappsample.curso.com.myfinalapp;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.AppCompatButton;
import android.view.View;
import android.widget.Toast;

public class Main2Activity extends AppCompatActivity {

    private AppCompatButton button;

    Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        context = this;

        button = (AppCompatButton) findViewById(R.id.mymessage);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context, "MI MENSAJE", Toast.LENGTH_LONG).show();
            }
        });
    }
}
