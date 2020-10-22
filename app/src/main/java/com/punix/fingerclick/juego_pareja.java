package com.punix.fingerclick;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.InterstitialAd;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class juego_pareja extends AppCompatActivity {

    int sumatorio1 = 0;
    int sumatorio2 = 0;
    boolean tiempobajando = false;
    boolean finalizado = false;
    int segundosrecibidos;
    ImageView backP;
    ImageView volverP;
    TextView Segundos1;
    TextView Segundos2;
    int segundosiniciales;
    ImageButton botonpulsar1;
    ImageButton botonpulsar2;
    TextView Score1;
    TextView Score2;
    boolean Pulsador1;
    boolean Pulsador2;
    CountDownTimer yourCountDownTimer;
    String Hasganado;
    String PalabraSegundos;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_juego_pareja);
        getSupportActionBar().hide();

        backP = (ImageView) findViewById(R.id.backP);
        volverP = (ImageView) findViewById(R.id.devueltaP);
        botonpulsar1 = (ImageButton) findViewById(R.id.botonaco1);
        botonpulsar2 = (ImageButton) findViewById(R.id.botonaco2);
        Score1 = (TextView) findViewById(R.id.Recod1);
        Score2 = (TextView) findViewById(R.id.Record2);
        Segundos1 = (TextView) findViewById(R.id.segundosRest1);
        Segundos2 = (TextView) findViewById(R.id.segundosRest2);
        Pulsador1 = false;
        Pulsador2 = false;
        PalabraSegundos = getString(R.string.Segundos);
        Hasganado = getString(R.string.Hasganado);
        Score1.setText(String.valueOf(sumatorio1));
        Score2.setText(String.valueOf(sumatorio2));


        volverP.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                segundosrecibidos = segundosiniciales;
                finalizado = false;
                sumatorio1 = 0;
                sumatorio2 = 0;
                tiempobajando = false;
                Score1.setText(String.valueOf(sumatorio1));
                Score2.setText(String.valueOf(sumatorio2));
                yourCountDownTimer.cancel();
                Pulsador1 = false;
                Pulsador2 = false;
                actualizarhora(0);
            }
        });

        backP.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent myintent = new Intent(juego_pareja.this, PantallaInicial.class);
                startActivity(myintent);
                if (tiempobajando) {
                    yourCountDownTimer.cancel();
                }

            }
        });




        botonpulsar1.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Pulsador1 = true;

                if (!tiempobajando && !finalizado) {
                    sumatorio1++;
                    Score1.setText(String.valueOf(sumatorio1));
                    if (!Pulsador2) {

                        yourCountDownTimer = new CountDownTimer(25000, 1000) {


                            public void onTick(long millisUntilFinished) {
                                actualizarhora((int) millisUntilFinished / 1000);

                                tiempobajando = true;

                            }

                            public void onFinish() {
                                backP.setVisibility(View.VISIBLE);
                                volverP.setVisibility(View.VISIBLE);
                                tiempobajando = false;
                                finalizado = true;
                                if (sumatorio1>sumatorio2){
                                    Toast Ganador1 = Toast.makeText(getApplicationContext(), Hasganado, Toast.LENGTH_LONG);
                                    Ganador1.setGravity(Gravity.BOTTOM, 0, 0);
                                    Ganador1.setDuration(Toast.LENGTH_LONG);
                                    Ganador1.show();
                                }else{

                                    Toast Ganador2 = Toast.makeText(getApplicationContext(), Hasganado , Toast.LENGTH_LONG);
                                    View toastView = Ganador2.getView();
                                    toastView.setRotation(180);
                                    Ganador2.setGravity(Gravity.TOP, 0, 0);
                                    Ganador2.setDuration(Toast.LENGTH_LONG);
                                    Ganador2.show();
                                }
                            }
                        }.start();
                    }
                }


                if (tiempobajando) {
                    sumatorio1++;
                    Score1.setText(String.valueOf(sumatorio1));

                }
            }

        });

        botonpulsar2.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Pulsador2 = true;
                if (!tiempobajando && !finalizado) {
                    sumatorio2++;
                    Score2.setText(String.valueOf(sumatorio2));
                    if (!Pulsador1) {

                        yourCountDownTimer = new CountDownTimer(25000, 1000) {


                            public void onTick(long millisUntilFinished) {
                                actualizarhora((int) millisUntilFinished / 1000);
                                tiempobajando = true;

                            }

                            public void onFinish() {
                                backP.setVisibility(View.VISIBLE);
                                volverP.setVisibility(View.VISIBLE);
                                tiempobajando = false;
                                finalizado = true;
                                if (sumatorio1>sumatorio2) {

                                    Toast Ganador1 = Toast.makeText(getApplicationContext(), Hasganado, Toast.LENGTH_LONG);
                                    Ganador1.setGravity(Gravity.BOTTOM, 0, 0);
                                    Ganador1.setDuration(Toast.LENGTH_LONG);
                                    Ganador1.show();
                                }else{

                                    Toast Ganador2 = Toast.makeText(getApplicationContext(), Hasganado , Toast.LENGTH_LONG);
                                    View toastView = Ganador2.getView();
                                    toastView.setRotation(180);
                                    Ganador2.setGravity(Gravity.TOP, 0, 0);
                                    Ganador2.setDuration(Toast.LENGTH_LONG);
                                    Ganador2.show();
                                }
                            }
                        }.start();
                    }
                }


                if (tiempobajando) {
                    sumatorio2++;
                    Score2.setRotation(180);
                    Score2.setText(String.valueOf(sumatorio2));

                }
            }

        });


    }


    public void actualizarhora(int segundos) {

        Segundos1.setRotation(180);
        Segundos1.setText(PalabraSegundos+ " "+ String.valueOf(segundos));
        Segundos2.setText(PalabraSegundos+ " "+ String.valueOf(segundos));


    }

}
