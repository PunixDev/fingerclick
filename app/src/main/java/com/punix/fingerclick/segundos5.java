package com.punix.fingerclick;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.InterstitialAd;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class segundos5 extends AppCompatActivity {

    private InterstitialAd mInterstitialAd;
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
    TextView WordlRecord;
    FirebaseFirestore db;
    Map<String, String> user = new HashMap<>();
    int recordmundial;
    String recordactual;
    CountDownTimer yourCountDownTimer;
    Button pantllaganadora;
    Button anuncio;
    String Puntuacion;
    String Hayrecord;
    String Sigueintentando;
    String PalabraSegundos;
    int contadoranuncio;

    /*******************************************************************************************************
     * EMPIEZA EL METODO ON CREATE
     * @param savedInstanceState
     ********************************************************************************************************/

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.segundos5);
        getSupportActionBar().hide();

        /**
         * parrafo que define la bbdd
         */
         db = FirebaseFirestore.getInstance();

        /**
         *
         */


        /**
         * Parrafo que incorpora los anuncios
         */

        mInterstitialAd = new InterstitialAd(this);
        //mInterstitialAd.setAdUnitId("ca-app-pub-9708491916754108/3448421733");
        mInterstitialAd.setAdUnitId("ca-app-pub-3940256099942544/1033173712");
        mInterstitialAd.loadAd(new AdRequest.Builder().build());

        back = (ImageView) findViewById(R.id.back);
        volver = (ImageView) findViewById(R.id.devuelta);
        Puntuacion = getString(R.string.Puntuación);
        Hayrecord = getString(R.string.Hayrecord);
        Sigueintentando = getString(R.string.Sigueintentando);
        PalabraSegundos = getString(R.string.Segundos);
        contadoranuncio = 0;

        volver.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                segundosrecibidos = segundosiniciales;
                finalizado = false;
                sumatorio = 0;
                tiempobajando = false;
                Score.setText(Puntuacion + " " + sumatorio);
                yourCountDownTimer.cancel();
                actualizarhora(0);
                obtenerdatos();
                contadoranuncio++;
                if (contadoranuncio%2==0) {
                    mInterstitialAd.loadAd(new AdRequest.Builder().build());
                }

            }
        });

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent myintent = new Intent(segundos5.this, MainActivity.class);
                startActivity(myintent);
                if (tiempobajando) {
                    yourCountDownTimer.cancel();
                }

            }
        });



        botonpulsar = (ImageButton) findViewById(R.id.botonaco);
        Score = (TextView) findViewById(R.id.Score);
        WordlRecord = (TextView) findViewById(R.id.WorldRecod);
        Segundos = (TextView) findViewById(R.id.segundosRest);
        pantllaganadora = (Button) findViewById(R.id.botonGanador);
        anuncio = (Button) findViewById(R.id.anuncio);
        pantllaganadora.setVisibility(View.INVISIBLE);
        anuncio.setVisibility(View.INVISIBLE);

        pantllaganadora.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent myintentGanador = new Intent(segundos5.this, Recodconseguido.class);
                startActivity(myintentGanador);
                Intent Ganador2 = new Intent(segundos5.this,Recodconseguido.class);
                Ganador2.putExtra("Ganador",segundosrecibidos);
                startActivity(Ganador2);
            }
        });


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



        Score.setText(Puntuacion + " " +  sumatorio);

            botonpulsar.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View v) {
                    if (!tiempobajando && !finalizado) {
                        sumatorio++;
                        Score.setText(Puntuacion +" "+ sumatorio);
                        recordmundial = Integer.parseInt(String.valueOf(WordlRecord.getText()));

                        yourCountDownTimer = new CountDownTimer(segundosrecibidos, 1000) {


                            public void onTick(long millisUntilFinished) {
                                actualizarhora((int)millisUntilFinished/1000);

                                tiempobajando = true;

                            }

                            public void onFinish() {

                                back.setVisibility(View.VISIBLE);
                                volver.setVisibility(View.VISIBLE);
                                tiempobajando = false;
                                finalizado = true;
                                user.put(segundosrecibidos/1000 + " segundos", String.valueOf(sumatorio));
                                if (sumatorio > recordmundial){
                                    actualizarbbdd();
                                    Toast.makeText(getApplicationContext(), Hayrecord, Toast.LENGTH_LONG).show();
                                    pantllaganadora.callOnClick();


                                }else{
                                    Toast.makeText(getApplicationContext(), Sigueintentando, Toast.LENGTH_LONG).show();
                                    anuncio.callOnClick();
                                }

                            }
                        }.start();
                    }


                    if (tiempobajando) {
                        sumatorio++;
                        Score.setText(Puntuacion+ " " + sumatorio);

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
            obtenerdatos();

    }

    public void actualizarhora(int segundos) {

        Segundos.setText(PalabraSegundos+ " "+ String.valueOf(segundos));

    }

    public  void actualizarbbdd(){
        db.collection("users").document(String.valueOf(segundosiniciales/1000)).set(user);
    }

    public void obtenerdatos(){

        /**
         * PEDAZO DE CODIGO CON EL QUE CONSEGUIMOS RECUPERAR UN DATO DE LA BASE DE DATOS BUSCANDO DENTRO DEL documentSnapshot
         */

        db.collection("users").document(String.valueOf(segundosiniciales/1000)).get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
            @Override
            public void onSuccess(DocumentSnapshot documentSnapshot) {
                recordactual = (String) documentSnapshot.get(segundosrecibidos/1000 + " segundos");
                if (recordactual != null) {
                    WordlRecord.setText(recordactual);
                }else{
                    WordlRecord.setText("0");
                }
                WordlRecord.setVisibility(View.INVISIBLE);
            }
        });

        }

}








