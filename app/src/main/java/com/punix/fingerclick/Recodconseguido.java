package com.punix.fingerclick;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class Recodconseguido extends AppCompatActivity {
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


    }
}
