package com.punix.fingerclick;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

public class PantallaInicial extends AppCompatActivity {

    FirebaseFirestore db;
    public  String recod;
    public  String recod2;
    TextView recordRandom ;
    TextView recordRPersona;
    TextView recordTrampa ;
    TextView recordTPersona;
    String World_Record0;
    String World_Record;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pantalla_inicial);
        getSupportActionBar().hide();


        Button botonWR = (Button) findViewById(R.id.buttonWR);
        Button boton1v1 = (Button) findViewById(R.id.buttonv1v);
        Button buttonrand = (Button) findViewById(R.id.buttonrand);
        Button buttontramp = (Button) findViewById(R.id.buttontramp);

        recordRandom = (TextView) findViewById(R.id.recordRandom);
        recordRPersona = (TextView) findViewById(R.id.recordRPersona);

        recordTrampa = (TextView) findViewById(R.id.recordTramp);
        recordTPersona = (TextView) findViewById(R.id.recordTrampPersona);

        World_Record0 = getString(R.string.recod_mundial0);
        World_Record = getString(R.string.recod_mundial);

        /**************************************************************************************
         * PARRAFO QUE RECUPERA TODOS LOS DATOS DE LA BBDD
         ************************************************************************************/
        db = FirebaseFirestore.getInstance();
        recuperardatos();


        botonWR.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent myintent = new Intent(PantallaInicial.this, MainActivity.class);
                startActivity(myintent);
            }
        });

        boton1v1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent myintent = new Intent(PantallaInicial.this, juego_pareja.class);
                startActivity(myintent);
            }
        });

        buttonrand.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent myintent = new Intent(PantallaInicial.this, JuegoRandom.class);
                startActivity(myintent);
            }
        });

        buttontramp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent myintent = new Intent(PantallaInicial.this, JuegoTrampa.class);
                startActivity(myintent);
            }
        });
    }

    public void recuperardatos() {

        /**
         * HE CREADO DOS ARRAYS Y CON ELLOS VAMOS A OBTENER TODOS LOS RECODS
         */
        /**
         * PEDAZO DE CODIGO CON EL QUE CONSEGUIMOS RECUPERAR UN DATO DE LA BASE DE DATOS BUSCANDO DENTRO DEL documentSnapshot
         */
        db.collection("Random").document("Random").get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
            @Override
            public void onSuccess(DocumentSnapshot documentSnapshot) {
                recod = (String) documentSnapshot.get("Ramdom");

                    if (recod != null) {
                        recordRandom.setText("  " + World_Record + " " + recod);
                    } else {
                        recordRandom.setText("  " + World_Record0);
                }
            }
        });

        db.collection("Personas").document("Random").get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
            @Override
            public void onSuccess(DocumentSnapshot documentSnapshot) {
                recod2 = (String) documentSnapshot.get("Random");

                if (recod2 != null) {
                    recordRPersona.setText("  " + recod2);
                } else {
                    recordRPersona.setText("  " + recod2);
                }
            }
        });

        db.collection("Trampa").document("Trampa").get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
            @Override
            public void onSuccess(DocumentSnapshot documentSnapshot) {
                recod = (String) documentSnapshot.get("Trampa");

                if (recod != null) {
                    recordTrampa.setText("  " + World_Record + " " + recod);
                } else {
                    recordTrampa.setText("  " + World_Record0);
                }
            }
        });

        db.collection("Personas").document("Trampa").get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
            @Override
            public void onSuccess(DocumentSnapshot documentSnapshot) {
                recod2 = (String) documentSnapshot.get("Trampa");

                if (recod2 != null) {
                    recordTPersona.setText("  " + recod2);
                } else {
                    recordTPersona.setText("  " + recod2);
                }
            }
        });
    }
    public void onBackPressed() {
        Intent startMain = new Intent(Intent.ACTION_MAIN);
        startMain.addCategory(Intent.CATEGORY_HOME);
        startMain.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(startMain);

    }


}
