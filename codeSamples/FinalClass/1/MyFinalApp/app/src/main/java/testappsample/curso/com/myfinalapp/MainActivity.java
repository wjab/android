package testappsample.curso.com.myfinalapp;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.v4.app.AppOpsManagerCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.AppCompatButton;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    private AppCompatButton button;
    Intent intent;
    Activity activity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        activity = this;

        button = (AppCompatButton) findViewById(R.id.myButton);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent = new Intent(activity, Main2Activity.class);
                startActivity(intent);
            }
        });

    }

    public MainActivity(AppCompatButton button, Intent intent) {
        this.button = button;
        this.intent = intent;
    }

    public MainActivity() {
    }
}
