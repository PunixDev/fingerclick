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

import androidx.appcompat.app.AppCompatActivity;

public class segundos5 extends AppCompatActivity {

    int sumatorio = 0;
    boolean tiempobajando;
    int segundosrecibidos;
    ImageView back;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.segundos5);
        getSupportActionBar().hide();

        back = (ImageView) findViewById(R.id.back);
        back.setVisibility(View.INVISIBLE);

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent myintent = new Intent(segundos5.this, MainActivity.class);
                startActivity(myintent);

            }
        });



        ImageButton botonpulsar = (ImageButton) findViewById(R.id.botonaco);
        final TextView Score = (TextView) findViewById(R.id.Score);


        Score.setText("Score  0");


        botonpulsar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (sumatorio == 0) {
                    new CountDownTimer(segundosrecibidos, 1000) {


                        public void onTick(long millisUntilFinished) {
                            Toast toast = Toast.makeText(getApplicationContext(), "seconds remaining: " + millisUntilFinished / 1000, Toast.LENGTH_LONG);
                            toast.setGravity(Gravity.CENTER|Gravity.TOP,0,100);
                            toast.show();
                            tiempobajando= true;

                        }

                        public void onFinish() {
                            Toast.makeText(getApplicationContext(), "done!", Toast.LENGTH_LONG).show();
                            back.setVisibility(View.VISIBLE);
                            tiempobajando= false;


                        }
                    }.start();
                }
                if (tiempobajando){
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
        int segundorecibidos =myIntent.getIntExtra("Segundos",0);
        if (segundorecibidos != 0) {
            segundosrecibidos = segundorecibidos;
        }

    }
}


