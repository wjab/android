package gmalv.cr.fi.ejercicio2calc;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    Button btnUno, btnDos, btnTres, btnCuatro, btnCinco, btnSeis, btnSiete, btnOcho, btnNueve, btnCero, btnSuma, btnBorrar, btnMultiplica, btnDivide, btnResta, btnResult;
    TextView pantalla;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        pantalla = (TextView) findViewById(R.id.lblPantalla);
        pantalla.setText("0");

        btnUno = (Button) findViewById(R.id.btn1);
        btnDos = (Button) findViewById(R.id.btn2);
        btnTres = (Button) findViewById(R.id.btn3);
        btnCuatro = (Button) findViewById(R.id.btn4);
        btnCinco = (Button) findViewById(R.id.btn5);
        btnSeis = (Button) findViewById(R.id.btn6);
        btnSiete = (Button) findViewById(R.id.btn7);
        btnOcho = (Button) findViewById(R.id.btn8);
        btnNueve = (Button) findViewById(R.id.btn9);
        btnCero = (Button) findViewById(R.id.btn0);
        btnSuma = (Button) findViewById(R.id.btnSum);
        btnMultiplica = (Button) findViewById(R.id.btnMult);
        btnResta = (Button) findViewById(R.id.btnResta);
        btnDivide = (Button) findViewById(R.id.btnDiv);
        btnBorrar= (Button) findViewById(R.id.btnC);
        btnResult= (Button) findViewById(R.id.btnResult);

        btnUno.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                if(pantalla.getText().equals("0")){
                    pantalla.setText("1");
                }
                else{
                    pantalla.append("1");
                }
            }
        });

        btnDos.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                if(pantalla.getText().equals("0")){
                    pantalla.setText("2");
                }
                else{
                    pantalla.append("2");
                }
            }
        });

        btnTres.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                if(pantalla.getText().equals("0")){
                    pantalla.setText("3");
                }
                else{
                    pantalla.append("3");
                }
            }
        });

        btnCuatro.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                if(pantalla.getText().equals("0")){
                    pantalla.setText("4");
                }
                else{
                    pantalla.append("4");
                }
            }
        });

        btnCinco.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                if(pantalla.getText().equals("0")){
                    pantalla.setText("5");
                }
                else{
                    pantalla.append("5");
                }
            }
        });

        btnSeis.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                if(pantalla.getText().equals("0")){
                    pantalla.setText("6");
                }
                else{
                    pantalla.append("6");
                }
            }
        });

        btnSiete.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                if(pantalla.getText().equals("0")){
                    pantalla.setText("7");
                }
                else{
                    pantalla.append("7");
                }
            }
        });

        btnOcho.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                if(pantalla.getText().equals("0")){
                    pantalla.setText("8");
                }
                else{
                    pantalla.append("8");
                }
            }
        });

        btnNueve.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                if(pantalla.getText().equals("0")){
                    pantalla.setText("9");
                }
                else{
                    pantalla.append("9");
                }
            }
        });

        btnCero.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                if(pantalla.getText().equals("0")){
                    pantalla.setText("0");
                }
                else{
                    pantalla.append("0");
                }
            }
        });

        btnSuma.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                String ultimoCaracter = pantalla.getText().toString();
                ultimoCaracter = ultimoCaracter.charAt(pantalla.getText().toString().length() - 1)+"";
                if (!ultimoCaracter.equals("-") && !ultimoCaracter.equals("*") && !ultimoCaracter.equals("/") && !ultimoCaracter.equals("+")){
                    pantalla.append("+");
                }
            }
        });

        btnMultiplica.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                String ultimoCaracter = pantalla.getText().toString();
                ultimoCaracter = ultimoCaracter.charAt(pantalla.getText().toString().length() - 1)+"";
                if (!ultimoCaracter.equals("-") && !ultimoCaracter.equals("*") && !ultimoCaracter.equals("/") && !ultimoCaracter.equals("+")){
                    pantalla.append("*");
                }
            }
        });

        btnDivide.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                String ultimoCaracter = pantalla.getText().toString();
                ultimoCaracter = ultimoCaracter.charAt(pantalla.getText().toString().length() - 1)+"";
                if (!ultimoCaracter.equals("-") && !ultimoCaracter.equals("*") && !ultimoCaracter.equals("/") && !ultimoCaracter.equals("+")){
                    pantalla.append("/");
                }
            }
        });

        btnResta.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                String ultimoCaracter = pantalla.getText().toString();
                ultimoCaracter = ultimoCaracter.charAt(pantalla.getText().toString().length() - 1)+"";
                if (!ultimoCaracter.equals("-") && !ultimoCaracter.equals("*") && !ultimoCaracter.equals("/") && !ultimoCaracter.equals("+")){
                    pantalla.append("-");
                }
            }
        });

        btnBorrar.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                pantalla.clearComposingText();
                pantalla.setText("0");

                btnCero.setEnabled(true);
                btnUno.setEnabled(true);
                btnDos.setEnabled(true);
                btnTres.setEnabled(true);
                btnCuatro.setEnabled(true);
                btnCinco.setEnabled(true);
                btnSeis.setEnabled(true);
                btnSiete.setEnabled(true);
                btnOcho.setEnabled(true);
                btnNueve.setEnabled(true);
                btnDivide.setEnabled(true);
                btnSuma.setEnabled(true);
                btnResta.setEnabled(true);
                btnMultiplica.setEnabled(true);
            }
        });

        btnResult.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                String calculo = pantalla.getText().toString();
                boolean exitoso = false;

                try {
                    if (calculo.contains("+")) {
                        String[] numeros = calculo.split("\\+");
                        if (numeros.length == 2) {
                            int primerNumero = Integer.parseInt(numeros[0]);
                            int segundoNumero = Integer.parseInt(numeros[1]);
                            long total = primerNumero + segundoNumero;
                            pantalla.setText(total + "");
                            exitoso = true;
                        }
                    } else if (calculo.contains("-")) {
                        String[] numeros = calculo.split("-");
                        if (numeros.length == 2) {
                            int primerNumero = Integer.parseInt(numeros[0]);
                            int segundoNumero = Integer.parseInt(numeros[1]);
                            long total = primerNumero - segundoNumero;
                            pantalla.setText(total + "");
                            exitoso = true;
                        }
                    } else if (calculo.contains("*")) {
                        String[] numeros = calculo.split("\\*");
                        if (numeros.length == 2) {
                            int primerNumero = Integer.parseInt(numeros[0]);
                            int segundoNumero = Integer.parseInt(numeros[1]);
                            long total = primerNumero * segundoNumero;
                            pantalla.setText(total + "");
                            exitoso = true;
                        }
                    } else if (calculo.contains("/")) {
                        String[] numeros = calculo.split("/");
                        if (numeros.length == 2) {
                            Long primerNumero = Long.parseLong(numeros[0]);
                            Long segundoNumero = Long.parseLong(numeros[1]);
                            Long total = primerNumero / segundoNumero;
                            pantalla.setText(total + "");
                            exitoso = true;
                        }
                    }
                }
                catch (Exception e){}

                if(exitoso){
                    btnCero.setEnabled(false);
                    btnUno.setEnabled(false);
                    btnDos.setEnabled(false);
                    btnTres.setEnabled(false);
                    btnCuatro.setEnabled(false);
                    btnCinco.setEnabled(false);
                    btnSeis.setEnabled(false);
                    btnSiete.setEnabled(false);
                    btnOcho.setEnabled(false);
                    btnNueve.setEnabled(false);
                    btnDivide.setEnabled(false);
                    btnSuma.setEnabled(false);
                    btnResta.setEnabled(false);
                    btnMultiplica.setEnabled(false);
                }
            }
        });
    }
}
