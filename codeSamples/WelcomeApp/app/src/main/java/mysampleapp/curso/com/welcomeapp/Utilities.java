package mysampleapp.curso.com.welcomeapp;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.widget.Toast;

import com.google.gson.Gson;

import org.json.JSONObject;

import java.util.ArrayList;

import static android.content.Context.MODE_PRIVATE;

/**
 * Created by Administrador on 24/10/2017.
 */

public class Utilities {

    public void ShowSuccessDialog(String mensaje, final Context context)
    {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle("Welcome");
        builder.setMessage(mensaje);
        builder.setIcon(R.drawable.okicon);
        builder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                Toast.makeText(context, "mi mensaje", Toast.LENGTH_SHORT).show();
            }
        });
        builder.show();
    }



}
