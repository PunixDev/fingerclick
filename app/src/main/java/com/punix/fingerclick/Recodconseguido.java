package com.punix.fingerclick;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class Recodconseguido extends AppCompatActivity {

    int segundosrecibidos;
    int segundosiniciales;
    int segundosrecibidosR;
    int segundosinicialesR;
    ImageButton botonpulsar;
    FirebaseFirestore db;
    Map<String, String> Personas = new HashMap<>();
    EditText nombre;
    Button BotonEnviar;
    ImageView Premio;

    /*******************************************************************************************************
     * EMPIEZA EL METODO ON CREATE
     * @param savedInstanceState
     ********************************************************************************************************/

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.recodconseguido);
        getSupportActionBar().hide();

        nombre = (EditText) findViewById(R.id.editTextTextPersonName);
        BotonEnviar = (Button) findViewById(R.id.buttonEnviar);
        Premio = (ImageView) findViewById(R.id.conseguido);



        /**
         * parrafo que define la bbdd
         */
        db = FirebaseFirestore.getInstance();


        BotonEnviar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (segundosrecibidos/1000 > 0){
                    Personas.put(segundosrecibidos/1000 + " segundos", String.valueOf(nombre.getText()));
                    actualizarbbdd();
                    Intent myintent = new Intent(Recodconseguido.this, MainActivity.class);
                    startActivity(myintent);

                }else {

                    Personas.put("Random", String.valueOf(nombre.getText()));
                    actualizarbbdd();
                    Intent myintent2 = new Intent(Recodconseguido.this, JuegoRandom.class);
                    startActivity(myintent2);
                }



            }
        });

    }



    // TODO: Add onResume() here:
    @Override
    protected void onResume() {
        super.onResume();
        Log.d("segundero", "onResume() called");

      Intent myIntent = getIntent();
        int recordRandom = myIntent.getIntExtra("RecordRandom", 0);
        if (recordRandom != 0) {
            segundosrecibidosR = recordRandom;
        }
        int segundorecibidos2 = myIntent.getIntExtra("Ganador", 0);
        if (segundorecibidos2 != 0) {
            segundosrecibidos = segundorecibidos2;
        }
        segundosiniciales = segundorecibidos2;
        segundosinicialesR = segundorecibidos2;

    }

    public  void actualizarbbdd(){
        if (segundosiniciales/1000 > 0) {
            db.collection("Personas").document(String.valueOf(segundosiniciales / 1000)).set(Personas);
        }else {
            db.collection("Personas").document(String.valueOf("Random")).set(Personas);
        }
    }


}
