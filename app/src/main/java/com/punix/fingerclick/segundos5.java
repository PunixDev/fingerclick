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

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;
import com.google.protobuf.StringValue;

import java.sql.DatabaseMetaData;
import java.util.HashMap;
import java.util.Map;

import io.opencensus.stats.AggregationData;

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
    TextView WordlRecord;
    FirebaseFirestore db;
    Map<String, String> user = new HashMap<>();
    int recordmundial;
    String recordactual;
    CountDownTimer yourCountDownTimer;

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



        back = (ImageView) findViewById(R.id.back);
        volver = (ImageView) findViewById(R.id.devuelta);

        volver.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                segundosrecibidos = segundosiniciales;
                finalizado = false;
                sumatorio = 0;
                tiempobajando = false;
                Score.setText("Score " + sumatorio);
                //back.setVisibility(View.INVISIBLE);
                yourCountDownTimer.onFinish();
                actualizarhora(0);
                obtenerdatos();

            }
        });

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent myintent = new Intent(segundos5.this, MainActivity.class);
                startActivity(myintent);
                yourCountDownTimer.onFinish();

            }
        });



        botonpulsar = (ImageButton) findViewById(R.id.botonaco);
        Score = (TextView) findViewById(R.id.Score);
        WordlRecord = (TextView) findViewById(R.id.WorldRecod);
        Segundos = (TextView) findViewById(R.id.segundosRest);


        Score.setText("Score  0");

            botonpulsar.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View v) {
                    if (!tiempobajando && !finalizado) {
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
                                    Toast.makeText(getApplicationContext(), "Recod conseguido!", Toast.LENGTH_LONG).show();
                                }else{
                                    Toast.makeText(getApplicationContext(), "Sigue intentandolo!", Toast.LENGTH_LONG).show();
                                }

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
            obtenerdatos();





    }

    public void actualizarhora(int segundos) {

        Segundos.setText("Segundos "+ String.valueOf(segundos));

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








