package com.punix.fingerclick;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import androidx.gridlayout.widget.GridLayout;
import android.os.CountDownTimer;
import android.util.Log;
import android.view.ContextThemeWrapper;
import android.view.View;
import android.widget.Button;
import android.widget.Chronometer;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.InterstitialAd;
import com.google.firebase.firestore.FirebaseFirestore;

public class JuegoRandom extends AppCompatActivity {


    boolean Hayjuego;
    int contador;
    int numeroRandom;
    int contadorvisible;
    boolean finJuego = false;
    boolean primeravez = true;
    ImageView Random1;
    ImageView Random2;
    ImageView Random3;
    ImageView Random4;
    ImageView Random5;
    ImageView Random6;
    ImageView Random7;
    ImageView Random8;
    ImageView Random9;
    ImageView Random10;
    ImageView Random11;
    ImageView Random12;
    ImageView Random13;
    ImageView Random14;
    ImageView Random15;
    ImageView Random16;
    ImageView Random17;
    ImageView Random18;
    GridLayout gridLayout;
    ImageButton boton;
    CountDownTimer EsteCountDownTimer;
    CountDownTimer AumentarVelocidad;
    CountDownTimer segundero;
    public boolean [] Imaggensi = {};
    int tiempoinicial;
    ImageView backR;
    ImageView volverR;
    TextView Segundos;
    String PalabraSegundos;
    int segundos = 0;
    int contadoranuncio;

    FirebaseFirestore db;
    private InterstitialAd mInterstitialAd;
    Button anuncio;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_juego_random);
        getSupportActionBar().hide();

        /**
         * parrafo que define la bbdd
         */
        db = FirebaseFirestore.getInstance();

        /**
         *
         */
        mInterstitialAd = new InterstitialAd(this);
        //mInterstitialAd.setAdUnitId("ca-app-pub-9708491916754108/3448421733");
        mInterstitialAd.setAdUnitId("ca-app-pub-3940256099942544/1033173712");
        mInterstitialAd.loadAd(new AdRequest.Builder().build());
        contadoranuncio = 0;
        anuncio = (Button) findViewById(R.id.buttonanuncio);
        anuncio.setVisibility(View.INVISIBLE);
        anuncio.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mInterstitialAd.isLoaded()) {
                    mInterstitialAd.show();
                } else {
                    Log.d("TAG", "The interstitial wasn't loaded yet.");
                }
            }
        });



        Random1 = (ImageView) findViewById(R.id.Random1);
        Random2 = (ImageView) findViewById(R.id.Random2);
        Random3 = (ImageView) findViewById(R.id.Random3);
        Random4 = (ImageView) findViewById(R.id.Random4);
        Random5 = (ImageView) findViewById(R.id.Random5);
        Random6 = (ImageView) findViewById(R.id.Random6);
        Random7 = (ImageView) findViewById(R.id.Random7);
        Random8 = (ImageView) findViewById(R.id.Random8);
        Random9 = (ImageView) findViewById(R.id.Random9);
        Random10 = (ImageView) findViewById(R.id.Random10);
        Random11 = (ImageView) findViewById(R.id.Random11);
        Random12 = (ImageView) findViewById(R.id.Random12);
        Random13 = (ImageView) findViewById(R.id.Random13);
        Random14 = (ImageView) findViewById(R.id.Random14);
        Random15 = (ImageView) findViewById(R.id.Random15);
        Random16 = (ImageView) findViewById(R.id.Random16);
        Random17 = (ImageView) findViewById(R.id.Random17);
        Random18 = (ImageView) findViewById(R.id.Random18);
        boton = (ImageButton) findViewById(R.id.buttonContador);
        volverR = (ImageView) findViewById(R.id.devueltaR);
        backR = (ImageView) findViewById(R.id.backR);
        gridLayout = (GridLayout) findViewById(R.id.Gridlayout);
        tiempoinicial = 1000;
        Segundos = (TextView) findViewById(R.id.segundosR);
        PalabraSegundos = getString(R.string.Segundos);
        actualizarhora(0);

        Imaggensi = new boolean[]{false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false};


        for(int i=0; i<gridLayout.getChildCount(); i++) {
            ImageView counter = (ImageView) gridLayout.getChildAt(i);
            counter.setImageDrawable(null);
        }
        /************************************************************************************************
         * boton para volver a jugar
         *********************************************************************************************/

        volverR.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                for (int i = 0; i < Imaggensi.length-1; i++) {
                    Imaggensi[i] = false;
                }
                finJuego = false;

                for(int i=0; i<gridLayout.getChildCount(); i++) {
                    ImageView counter = (ImageView) gridLayout.getChildAt(i);
                    counter.setImageDrawable(null);
                }

                AumentarVelocidad.cancel();
                EsteCountDownTimer.cancel();
                segundero.cancel();
                tiempoinicial = 1000;
                actualizarhora(0);
                primeravez = true;
                contadoranuncio++;
                if (contadoranuncio%3==0) {
                    mInterstitialAd.loadAd(new AdRequest.Builder().build());
                }

            }
        });
        /************************************************************************************************
         * boton para volver a atras
         *********************************************************************************************/
        backR.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent myintent = new Intent(JuegoRandom.this, PantallaInicial.class);
                startActivity(myintent);
                AumentarVelocidad.cancel();
                EsteCountDownTimer.cancel();
                segundero.cancel();
            }
        });

        /************************************************************************************************
         * boton para empezar
         *********************************************************************************************/

        boton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!finJuego && primeravez) {
                    AumentarVelocidad.start();
                    EsteCountDownTimer.start();
                    segundero.start();
                    primeravez = false;
                }

            }
        });

        /************************************************************************************************
         *
         *********************************************************************************************/


        /************************************************************************************************
         * countsown segundos
         *********************************************************************************************/

        segundero = new CountDownTimer(1000000000, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                segundos++;
                actualizarhora(segundos);

            }

            @Override
            public void onFinish() {

            }
        };





        /************************************************************************************************
         * estructura de cowndoun timer pàra que aparezcan mas rapido cada vez
         *********************************************************************************************/

        AumentarVelocidad= new CountDownTimer(2000,1000) {
                 @Override
                 public void onTick(long millisUntilFinished) {

                 }

                 @Override
                 public void onFinish() {
                     tiempoinicial = (int) Math.round(tiempoinicial *0.95);
                     AumentarVelocidad.start();

                     EsteCountDownTimer = new CountDownTimer(tiempoinicial, 1000) {
                         @Override
                         public void onTick(long millisUntilFinished) {
                             Enseñar1();
                         }

                         @Override
                         public void onFinish() {
                             chequearsi3();
                             if (finJuego){
                                 Toast Ganador1 = Toast.makeText(getApplicationContext(), "Se acabo", Toast.LENGTH_LONG);
                                 Ganador1.show();
                                 AumentarVelocidad.cancel();
                                 segundero.cancel();
                                 primeravez = true;
                                 if (contadoranuncio%3==0) {
                                     anuncio.callOnClick();
                                 }
                             }else {
                                 EsteCountDownTimer.start();
                                 Toast contador = Toast.makeText(getApplicationContext(), "Ha subido "+tiempoinicial, Toast.LENGTH_LONG);
                                 contador.show();
                             }

                         }
                     };


                 }
             };




            EsteCountDownTimer = new CountDownTimer(tiempoinicial, 1000) {
                @Override
                public void onTick(long millisUntilFinished) {
                    Enseñar1();
                }

                @Override
                public void onFinish() {
                    chequearsi3();
                    if (finJuego){
                        Toast Ganador1 = Toast.makeText(getApplicationContext(), "Se acabo", Toast.LENGTH_LONG);
                        Ganador1.show();
                        AumentarVelocidad.cancel();
                        segundero.cancel();
                        primeravez = true;
                    }else {
                        EsteCountDownTimer.start();
                        Toast contador = Toast.makeText(getApplicationContext(), "Ha subido "+tiempoinicial, Toast.LENGTH_LONG);
                        contador.show();
                    }

                }
            };

        /************************************************************************************************
         *
         *********************************************************************************************/



    }


    public void Enseñar1() {
        numeroRandom = NumeroalAzar();
        ImageView ImagenRandom = (ImageView) gridLayout.getChildAt(numeroRandom);
        if (Imaggensi[numeroRandom] != true) {
            ImagenRandom.setImageResource(R.drawable.redondobuenof);
            Imaggensi[numeroRandom] = true;
        } else {
            Enseñar1();
        }
    }



    public static int NumeroalAzar() {
        int minimo = 0;
        int maximo = 17;
        int aleatorio = 0;
        for (int i = 0; i < 10; i++)
            aleatorio = (int) (Math.random() * (maximo - minimo + 1) + (minimo));
        return aleatorio;

    }




    public void invisible(View view) {
        if (!finJuego) {
            int numeroclicado = gridLayout.indexOfChild(view);
            Imaggensi[numeroclicado] = false;
            ImageView Imaenborrada = (ImageView) gridLayout.getChildAt(numeroclicado);
            Imaenborrada.setImageDrawable(null);
        }



    }


    public  boolean chequearsi3(){
        contadorvisible = 0;
        for (int i = 0; i < Imaggensi.length-1; i++) {
            if (Imaggensi[i]== true){
                contadorvisible++;
            }
            if (contadorvisible >= 3){
                finJuego = true;
                break;
            }
        }


        return finJuego;
    }

    public void actualizarhora(int segundos) {


        Segundos.setText(PalabraSegundos+ " "+ String.valueOf(segundos));


    }



}

