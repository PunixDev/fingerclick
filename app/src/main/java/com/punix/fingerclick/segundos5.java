package com.punix.fingerclick;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class segundos5 extends AppCompatActivity {

    int sumatorio = 0;
    boolean tiempobajando = false;
    boolean finalizado = false;
    int segundosrecibidos;
    ImageView back;
    ImageView volver;
    TextView Segundos;
    int segundosiniciales;
    ImageButton botonpulsar;
    TextView Score;
    FirebaseFirestore db;
    Map<String, Object> user = new HashMap<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.segundos5);
        getSupportActionBar().hide();

        /**
         * parrafo que recupera inserta y recupera datos de la bbdd
         *
         */

         db = FirebaseFirestore.getInstance();


        back = (ImageView) findViewById(R.id.back);
        volver = (ImageView) findViewById(R.id.devuelta);
        back.setVisibility(View.INVISIBLE);
        volver.setVisibility(View.INVISIBLE);

        volver.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                segundosrecibidos = segundosiniciales;
                finalizado = false;
                sumatorio = 0;
                tiempobajando = false;
                Score.setText("Score " + sumatorio);
                back.setVisibility(View.INVISIBLE);
                volver.setVisibility(View.INVISIBLE);
            }
        });

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent myintent = new Intent(segundos5.this, MainActivity.class);
                startActivity(myintent);

            }
        });



        botonpulsar = (ImageButton) findViewById(R.id.botonaco);
        Score = (TextView) findViewById(R.id.Score);
        Segundos = (TextView) findViewById(R.id.segundosRest);


        Score.setText("Score  0");

            botonpulsar.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View v) {
                    if (!tiempobajando && !finalizado) {

                        new CountDownTimer(segundosrecibidos, 1000) {


                            public void onTick(long millisUntilFinished) {
                                actualizarhora((int)millisUntilFinished/1000);

                                tiempobajando = true;

                            }

                            public void onFinish() {
                                Toast.makeText(getApplicationContext(), "done!", Toast.LENGTH_LONG).show();
                                back.setVisibility(View.VISIBLE);
                                volver.setVisibility(View.VISIBLE);
                                tiempobajando = false;
                                finalizado = true;
                                db.collection("users")
                                        .add(user)
                                        .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                                            @Override
                                            public void onSuccess(DocumentReference documentReference) {
                                            }
                                        })
                                        .addOnFailureListener(new OnFailureListener() {
                                            @Override
                                            public void onFailure(@NonNull Exception e) {
                                            }
                                        });
                            }
                        }.start();
                    }


                    if (tiempobajando) {
                        sumatorio++;
                        Score.setText("Score " + sumatorio);

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
            int segundorecibidos2 = myIntent.getIntExtra("Segundos", 0);
            if (segundorecibidos2 != 0) {
                segundosrecibidos = segundorecibidos2;
            }
              segundosiniciales = segundorecibidos2;



    }

    public void actualizarhora(int segundos) {

        Segundos.setText("Segundos "+ String.valueOf(segundos));

    }
}


