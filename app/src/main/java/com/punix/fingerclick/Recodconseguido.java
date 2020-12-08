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
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.play.core.review.ReviewInfo;
import com.google.android.play.core.review.ReviewManager;
import com.google.android.play.core.review.ReviewManagerFactory;
import com.google.android.play.core.tasks.OnCompleteListener;
import com.google.android.play.core.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class Recodconseguido extends AppCompatActivity {

    int segundosrecibidos;
    int segundosiniciales;
    int segundosrecibidosR;
    int segundosrecibidosT;
    int segundosinicialesR;
    ImageButton botonpulsar;
    FirebaseFirestore db;
    Map<String, String> Personas = new HashMap<>();
    EditText nombre;
    Button BotonEnviar;
    ImageView Premio;
    private  ReviewManager manager;
    private ReviewInfo reviewInfo;

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
        Review();



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
                }else if(segundosrecibidosT > 0){

                    Personas.put("Trampa", String.valueOf(nombre.getText()));
                    actualizarbbdd();
                    Intent myintent3 = new Intent(Recodconseguido.this, JuegoTrampa.class);
                    startActivity(myintent3);

                }else {
                    Personas.put("Random", String.valueOf(nombre.getText()));
                    actualizarbbdd();
                    Intent myintent2 = new Intent(Recodconseguido.this, JuegoRandom.class);
                    startActivity(myintent2);

                    /**********************************************************
                     * parrafo pedir review
                     ************************************************/
                    if(reviewInfo !=null){
                        Task<Void> flow = manager.launchReviewFlow(Recodconseguido.this, reviewInfo);
                        flow.addOnCompleteListener(new OnCompleteListener<Void>() {
                            @Override
                            public void onComplete(@NonNull Task<Void> task) {
                            }
                        });

                    }else{
                        onBackPressed();
                    }
                    /**********************************************************
                     * parrafo pedir review
                     ************************************************/

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
        int recordTrampa = myIntent.getIntExtra("RecordTrampa", 0);
        if (recordTrampa != 0) {
            segundosrecibidosT = recordTrampa;
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
        }else if(segundosrecibidosT > 0){
            db.collection("Personas").document(String.valueOf("Trampa")).set(Personas);
        }else{
            db.collection("Personas").document(String.valueOf("Random")).set(Personas);
        }
    }
    public void onBackPressed() {
        Intent myintent = new Intent(Recodconseguido.this, PantallaInicial.class);
        startActivity(myintent);
    }

    public void Review() {
        manager = ReviewManagerFactory.create(this);
        Task<ReviewInfo> request = manager.requestReviewFlow();
        request.addOnCompleteListener(new OnCompleteListener<ReviewInfo>() {
            @Override
            public void onComplete(@NonNull Task<ReviewInfo> task) {
                if (task.isSuccessful()) {
                    // We can get the ReviewInfo object
                    reviewInfo = task.getResult();
                } else {
                    // There was some problem, continue regardless of the result.
                }
            }
        });
    }


}
