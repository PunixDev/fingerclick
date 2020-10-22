package com.punix.fingerclick;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class PantallaInicial extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pantalla_inicial);
        getSupportActionBar().hide();


        Button botonWR = (Button) findViewById(R.id.buttonWR);
        Button boton1v1 = (Button) findViewById(R.id.buttonv1v);
        Button buttonrand = (Button) findViewById(R.id.buttonrand);


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
    }
}