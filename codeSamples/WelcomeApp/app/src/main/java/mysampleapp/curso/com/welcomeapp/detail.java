package mysampleapp.curso.com.welcomeapp;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import org.w3c.dom.Text;

public class detail extends AppCompatActivity {

    TextView tv_name, tv_lastName, tv_user, tv_password;
    Button btn_go_back;
    Intent getData;
    UserObject userObject;
    Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        context = this;
        getData = getIntent();
        userObject = (UserObject)getData.getSerializableExtra("UserDetail");

        tv_name = (TextView) findViewById(R.id.et_name);
        tv_lastName = (TextView) findViewById(R.id.et_lastName);
        tv_user = (TextView) findViewById(R.id.et_user);
        tv_password = (TextView) findViewById(R.id.et_password);
        btn_go_back = (Button) findViewById(R.id.go_back);

        if (userObject != null) {
            tv_name.setText(userObject.getName());
            tv_lastName.setText(userObject.getLastName());
            tv_user.setText(userObject.getUser());
            tv_password.setText(userObject.getPassword());
        }

        btn_go_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentList = new Intent(context, activity_user_list.class);
                startActivity(intentList);
                finish();
            }
        });
    }
}
