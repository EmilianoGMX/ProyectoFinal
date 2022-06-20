package com.example.proyectofinal;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.zxing.integration.android.IntentResult;
import com.google.zxing.integration.android.IntentIntegrator;

public class LectorQR extends AppCompatActivity
{
    //Texto que muestra el resultado
    //Depuración
    private TextView txtResultado;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lector_qr);

        //Referencia de elementos de diseño
        Button btnEscanear = (Button) findViewById(R.id.btnEscanear);
        txtResultado = (TextView) findViewById(R.id.txtResultado);

        btnEscanear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
                IntentIntegrator intento = new IntentIntegrator(LectorQR.this);         //Intento para la integración del lector QR al activity
                intento.setDesiredBarcodeFormats(IntentIntegrator.ALL_CODE_TYPES);           //Se aceptan todos los tipos de códigos
                intento.setPrompt("Scan");                                                   //Mensaje
                intento.setCameraId(0);                                                      //Se selecciona la camara
                intento.setBeepEnabled(true);                                                //Sonido al registrar código
                intento.setBarcodeImageEnabled(false);                                       //Habilitar el guardado de la imagén
                intento.initiateScan();                                                      //Se inicia el scanner

            }
        });
    }

    //Se manda a llamar este método una vez es leído un código QR
    protected void onActivityResult(int requestCode, int resultCode, Intent data)
    {
        IntentResult resultado = IntentIntegrator.parseActivityResult(requestCode, resultCode, data);   //R

        if(resultado != null)
        {
            if(resultado.getContents() == null)
            {
                MostrarToast("No hay información");
            }else{
                txtResultado.setText(resultado.getContents());
                String respuesta = resultado.getContents();
                String[] resultadoSplit = respuesta.split(",");

                String producto = "";
                String descuento = "";
                String color = "";

                for(int i = 0; i<resultadoSplit.length; i++)
                {
                    String[] variableValor = resultadoSplit[i].split(":");
                    switch (variableValor[0])
                    {
                        case "Producto":
                            producto = variableValor[1];
                            break;

                        case "Descuento":
                            descuento = variableValor[1];
                            break;

                        case "Color":
                            color = variableValor[1];
                            break;
                    }
                }

                SharedPreferences preferencias = getSharedPreferences("cupones", Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = preferencias.edit();

                int cantidad = preferencias.getInt("cantidad", 0);

                editor.putInt("cantidad", cantidad+3);

                editor.putString("Producto"+cantidad++, producto);
                editor.putString("Descuento"+cantidad++, descuento);
                editor.putString("Color"+cantidad++, color);

                editor.commit();

                System.out.println("Producto: " + producto);
                System.out.println("Descuento: " + descuento);
                System.out.println("Color: " + color);

                MostrarToast("Cupón guardado exitosamente");

            }
        }else{
            super.onActivityResult(requestCode, resultCode, data);
        }
    }

    //Método para mostrar toast
    //Depuracion
    private void MostrarToast(String texto)
    {
        Context context = getApplicationContext();
        CharSequence text = texto;
        int duration = Toast.LENGTH_LONG;

        Toast toast = Toast.makeText(context, text, duration);
        toast.show();
    }

}