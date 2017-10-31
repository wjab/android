package calculator.centaurosolutions.com.mycalculator;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends Activity {

    Button b_one, b_two, b_three, b_four, b_five, b_six,b_seven, b_eight, b_nine, b_zero, b_plus, b_equal, cleanText;
    TextView operation;
    float value = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        b_one = (Button)findViewById(R.id.number_1);
        b_two = (Button)findViewById(R.id.number_2);
        b_three = (Button)findViewById(R.id.number_3);
        b_four = (Button)findViewById(R.id.number_4);
        b_five = (Button)findViewById(R.id.number_5);
        b_six = (Button)findViewById(R.id.number_6);
        b_seven = (Button)findViewById(R.id.number_7);
        b_eight = (Button)findViewById(R.id.number_8);
        b_nine = (Button)findViewById(R.id.number_9);
        b_zero = (Button)findViewById(R.id.number_0);
        b_plus = (Button)findViewById(R.id.b_plus);
        cleanText = (Button)findViewById(R.id.b_clean);
        b_equal = (Button)findViewById(R.id.b_result);
        operation = (TextView)findViewById(R.id.tv_result);


        b_one.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                operation.setText(operation.getText() + getText(R.string.one).toString());
            }
        });

        b_two.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                operation.setText(operation.getText() + getText(R.string.two).toString());
            }
        });

        b_three.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                operation.setText(operation.getText() + getText(R.string.three).toString());
            }
        });

        b_four.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                operation.setText(operation.getText() + getText(R.string.four).toString());
            }
        });

        b_five.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                operation.setText(operation.getText() + getText(R.string.five).toString());
            }
        });

        b_six.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                operation.setText(operation.getText() + getText(R.string.six).toString());
            }
        });

        b_seven.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                operation.setText(operation.getText() + getText(R.string.seven).toString());
            }
        });

        b_eight.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                operation.setText(operation.getText() + getText(R.string.eight).toString());
            }
        });

        b_nine.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                operation.setText(operation.getText() + getText(R.string.nine).toString());
            }
        });

        b_zero.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                operation.setText(operation.getText() + getText(R.string.zero).toString());
            }
        });

        b_plus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    value = value + Float.parseFloat(operation.getText().toString());
                    operation.setText(getText(R.string.empty).toString());
                }
                catch (Exception ex)
                {}
            }
        });

        cleanText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                value = 0;
                operation.setText(getText(R.string.empty).toString());
            }
        });

        b_equal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    value = value + Float.parseFloat(operation.getText().toString());
                    operation.setText(Float.toString(value));
                    value = 0;
                }
                catch (Exception ex)
                {}
            }
        });

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
}
