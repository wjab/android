package testappsample.curso.com.myfinalapp;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.AppCompatButton;
import android.view.View;
import android.widget.EditText;

import testappsample.curso.com.myfinalapp.DataBase.DataBaseManager;

public class MainActivity extends AppCompatActivity {

    private AppCompatButton button;
    private EditText textOne, textTwo;
    Intent intent;
    Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        context = this;

        textOne = (EditText) findViewById(R.id.textOne);
        textTwo = (EditText) findViewById(R.id.textTwo);
        button = (AppCompatButton) findViewById(R.id.myButton);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent = new Intent(context, Main2Activity.class);
                intent.putExtra("one", textOne.getText());
                intent.putExtra("two", textTwo.getText());
                startActivity(intent);
            }
        });

    }
}
