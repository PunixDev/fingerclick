package com.punix.fingerclick;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
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
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class JuegoRandom extends AppCompatActivity {


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
    Button pantllaganadora;
    String PalabraSegundos;
    TextView WordlRecord;
    int segundos = 0;
    int recordmundial;
    int contadoranuncio;
    FirebaseFirestore db;
    private InterstitialAd mInterstitialAd;
    Button anuncio;
    Map<String, String> user = new HashMap<>();
    String recordactual;
    String Hayrecord;
    String Sigueintentando;
    String Instrucciones;
    String explicacion;
    String aceptar;
    SharedPreferences sharedPreferences;



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
        WordlRecord = (TextView) findViewById(R.id.WordlRecord2);
        pantllaganadora = (Button) findViewById(R.id.pantallaganadora);
        gridLayout = (GridLayout) findViewById(R.id.Gridlayout);
        tiempoinicial = 1000;
        Segundos = (TextView) findViewById(R.id.segundosR);
        PalabraSegundos = getString(R.string.Segundos);
        actualizarhora(0);
        Imaggensi = new boolean[]{false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false};
        pantllaganadora.setVisibility(View.INVISIBLE);
        Hayrecord = getString(R.string.Hayrecord);
        Instrucciones = getString(R.string.Instrucciones);
        explicacion = getString(R.string.explicacion);
        aceptar = getString(R.string.aceptar);
        Sigueintentando = getString(R.string.Sigueintentando);
        obtenerdatos();


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
                segundos = 0;

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
                    recordmundial = Integer.parseInt(String.valueOf(WordlRecord.getText()));
                }

            }
        });

        /************************************************************************************************
         *
         *********************************************************************************************/


        /**********************************************************************************************
         * Alerta explica el juego
         *********************************************************************************************/

        sharedPreferences = this.getSharedPreferences("com.punix.fingerclick", Context.MODE_PRIVATE);
        String HaEntrado = sharedPreferences.getString("Si", "error");
        Log.i("THE LANG", HaEntrado);


        if(HaEntrado.equals("error")) {
            new AlertDialog.Builder(this)
                    .setIcon(android.R.drawable.ic_dialog_alert)
                    .setTitle(Instrucciones)
                    .setMessage(explicacion)
                    .setPositiveButton(aceptar, new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            setSI("Si");
                        }
                    }).show();
        }


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
                             chequearsi3();
                         }

                         @Override
                         public void onFinish() {
                             chequearsi3();
                             if (finJuego){
                                 AumentarVelocidad.cancel();
                                 segundero.cancel();
                                 primeravez = true;
                                 user.put("Ramdom", String.valueOf(segundos));
                                 if (segundos > recordmundial){
                                     actualizarbbdd();
                                     Toast.makeText(getApplicationContext(), Hayrecord, Toast.LENGTH_LONG).show();
                                     pantllaganadora.callOnClick();
                                     finish();
                                 }else{
                                     Toast.makeText(getApplicationContext(), Sigueintentando, Toast.LENGTH_LONG).show();
                                     if (contadoranuncio%3==0) {
                                         anuncio.callOnClick();
                                     }
                                 }

                             }else {
                                 EsteCountDownTimer.start();
                             }

                         }
                     };


                 }
             };




            EsteCountDownTimer = new CountDownTimer(tiempoinicial, 1000) {
                @Override
                public void onTick(long millisUntilFinished) {
                    Enseñar1();
                    chequearsi3();
                }

                @Override
                public void onFinish() {
                    chequearsi3();
                    if (finJuego){
                        AumentarVelocidad.cancel();
                        segundero.cancel();
                        primeravez = true;
                    }else {
                        EsteCountDownTimer.start();
                    }

                }
            };

        /************************************************************************************************
         *
         *********************************************************************************************/



        /************************************************************************************************
         *REVISAR
         *********************************************************************************************/
        pantllaganadora.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent myintentGanador = new Intent(JuegoRandom.this, Recodconseguido.class);
                startActivity(myintentGanador);
                Intent Ganador2 = new Intent(JuegoRandom.this,Recodconseguido.class);
                Ganador2.putExtra("RecordRandom",segundos);
                startActivity(Ganador2);
            }
        });



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
    public  void actualizarbbdd(){
        db.collection("Random").document("Random").set(user);
    }

    public void obtenerdatos(){

        /**
         * PEDAZO DE CODIGO CON EL QUE CONSEGUIMOS RECUPERAR UN DATO DE LA BASE DE DATOS BUSCANDO DENTRO DEL documentSnapshot
         */

        db.collection("Random").document(String.valueOf("Random")).get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
            @Override
            public void onSuccess(DocumentSnapshot documentSnapshot) {
                recordactual = (String) documentSnapshot.get("Ramdom");
                if (recordactual != null) {
                    WordlRecord.setText(recordactual);
                }else{
                    WordlRecord.setText("0");
                }
                WordlRecord.setVisibility(View.INVISIBLE);
            }
        });

    }
    public void onBackPressed() {
        Intent myintent = new Intent(JuegoRandom.this, PantallaInicial.class);
        startActivity(myintent);
        AumentarVelocidad.cancel();
        EsteCountDownTimer.cancel();
        segundero.cancel();
    }

    public void setSI (String si){

        sharedPreferences.edit().putString("Si", si).apply();


    }
    }



