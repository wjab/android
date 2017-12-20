package testappsample.curso.com.testappsample;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    Button button_login;
    EditText et_user, et_password;
    TextView tv_login_fail;
    Context context;
    Intent intent;

    AnotherClass anotherClass;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        context = this;

        button_login = (Button) findViewById(R.id.login);
        et_user = (EditText) findViewById(R.id.user);
        et_password = (EditText) findViewById(R.id.password);
        tv_login_fail = (TextView) findViewById(R.id.fail);

        button_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(CheckLogin(et_user.getText().toString(), et_password.getText().toString()))
                {
                    tv_login_fail.setVisibility(View.INVISIBLE);
                    intent = new Intent(context, LoginSucessActivity.class);
                    startActivity(intent);
                }
                else
                {
                    tv_login_fail.setVisibility(View.VISIBLE);
                }
            }
        });

       //anotherClass = AnotherClass.getAnotherClassInstance(getApplicationContext());
    }

    public boolean CheckLogin(String user, String password)
    {
        boolean result = false;

        if (user.equals("walter") && password.equals("admin")  )
        {
            result = true;
        }

        return result;
    }
}
