package com.punix.fingerclick;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;

import com.google.firebase.firestore.FirebaseFirestore;

public class MainActivity extends AppCompatActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportActionBar().hide();
        FirebaseFirestore db = FirebaseFirestore.getInstance();


        Button segundos5 = (Button) findViewById(R.id.button);
        Button segundos10 = (Button) findViewById(R.id.button2);
        Button segundos15 = (Button) findViewById(R.id.button3);
        Button segundos20 = (Button) findViewById(R.id.button4);
        Button segundos30 = (Button) findViewById(R.id.button5);
        Button segundos40 = (Button) findViewById(R.id.button6);


        segundos5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent myintent = new Intent(MainActivity.this, segundos5.class);
                startActivity(myintent);
                int segundos = 5000;
                Intent segundos5 = new Intent(MainActivity.this,segundos5.class);
                segundos5.putExtra("Segundos", segundos);
                startActivity(segundos5);


            }
        });

        segundos10.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent myintent = new Intent(MainActivity.this, segundos5.class);
                startActivity(myintent);
                int segundos = 10000;
                Intent segundos10 = new Intent(MainActivity.this,segundos5.class);
                segundos10.putExtra("Segundos", segundos);
                startActivity(segundos10);


            }
        });

        segundos15.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent myintent = new Intent(MainActivity.this, segundos5.class);
                startActivity(myintent);
                int segundos = 15000;
                Intent segundos15 = new Intent(MainActivity.this,segundos5.class);
                segundos15.putExtra("Segundos", segundos);
                startActivity(segundos15);


            }
        });

        segundos20.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent myintent = new Intent(MainActivity.this, segundos5.class);
                startActivity(myintent);
                int segundos = 20000;
                Intent segundos20 = new Intent(MainActivity.this,segundos5.class);
                segundos20.putExtra("Segundos", segundos);
                startActivity(segundos20);


            }
        });

        segundos30.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent myintent = new Intent(MainActivity.this, segundos5.class);
                startActivity(myintent);
                int segundos = 30000;
                Intent segundos30 = new Intent(MainActivity.this,segundos5.class);
                segundos30.putExtra("Segundos", segundos);
                startActivity(segundos30);

            }
        });

        segundos40.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent myintent = new Intent(MainActivity.this, segundos5.class);
                startActivity(myintent);
                int segundos = 40000;
                Intent segundos40 = new Intent(MainActivity.this,segundos5.class);
                segundos40.putExtra("Segundos", segundos);
                startActivity(segundos40);

            }
        });




    }
}