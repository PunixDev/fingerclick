package com.punix.fingerclick;

import androidx.activity.OnBackPressedCallback;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import java.lang.reflect.Array;

public class MainActivity extends AppCompatActivity {
    FirebaseFirestore db;
    int segundoss5 = 5000;
    int segundoss10 = 10000;
    int segundoss15 = 15000;
    int segundoss20 = 20000;
    int segundoss30 = 30000;
    int segundoss40 = 40000;
    int segundoss45 = 45000;
    int segundoss50 = 50000;
    int segundoss55= 55000;
    int segundoss60= 60000;
    int segundoss65= 65000;
    int segundoss70= 70000;
    int segundoss75= 75000;
    int segundoss80= 80000;
    int segundoss90= 90000;
    int segundoss100= 100000;
    int segundoss110= 110000;
    int segundoss120= 120000;
    int segundoss180= 180000;
    int segundoss240= 240000;
    int segundoss300= 300000;
    int segundoss360= 360000;
    int segundoss480= 480000;
    int segundoss600= 600000;

    String [] claves = { "5 segundos","10 segundos","15 segundos","20 segundos","30 segundos","40 segundos", "45 segundos", "50 segundos","55 segundos","60 segundos","65 segundos","70 segundos","75 segundos","80 segundos","90 segundos","100 segundos","110 segundos","120 segundos","180 segundos","240 segundos","300 segundos","360 segundos","480 segundos","600 segundos"};
    int [] tiempos = {5,10,15,20,30,40,45,50,55,60,65,70,75,80,90,100,110,120,180,240,300,360,480,600};
    public  String recod;
    public  String recod2;


    TextView Tsegundos5 ;
    TextView Tsegundos10;
    TextView Tsegundos15;
    TextView Tsegundos20;
    TextView Tsegundos30;
    TextView Tsegundos40;
    TextView Tsegundos45;
    TextView Tsegundos50;
    TextView Tsegundos55;
    TextView Tsegundos60;
    TextView Tsegundos65;
    TextView Tsegundos70;
    TextView Tsegundos75;
    TextView Tsegundos80;
    TextView Tsegundos90;
    TextView Tsegundos100;
    TextView Tsegundos110;
    TextView Tsegundos120;
    TextView Tsegundos180;
    TextView Tsegundos240;
    TextView Tsegundos300;
    TextView Tsegundos360;
    TextView Tsegundos480;
    TextView Tsegundos600;

    TextView Tsegundos52 ;
    TextView Tsegundos102;
    TextView Tsegundos152;
    TextView Tsegundos202;
    TextView Tsegundos302;
    TextView Tsegundos402;
    TextView Tsegundos452;
    TextView Tsegundos502;
    TextView Tsegundos552;
    TextView Tsegundos602;
    TextView Tsegundos652;
    TextView Tsegundos702;
    TextView Tsegundos752;
    TextView Tsegundos802;
    TextView Tsegundos902;
    TextView Tsegundos1002;
    TextView Tsegundos1102;
    TextView Tsegundos1202;
    TextView Tsegundos1802;
    TextView Tsegundos2402;
    TextView Tsegundos3002;
    TextView Tsegundos3602;
    TextView Tsegundos4802;
    TextView Tsegundos6002;
    String World_Record;
    String World_Record0;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportActionBar().hide();

        World_Record = getString(R.string.recod_mundial);
        World_Record0 = getString(R.string.recod_mundial0);


        /**************************************************************************************
         * PARRAFO QUE RECUPERA TODOS LOS DATOS DE LA BBDD
         ************************************************************************************/
         db = FirebaseFirestore.getInstance();


       /************************************************************************************************************************
        * EN ESTE `PEDAZO DE CODIGO SE DEFINEN LOS BOTONES Y SUS CORRESPONDIENTES INTENT PARA PASAR LOS DATOS A LA OTRA PANTALLA
        ************************************************************************************************************************/

        Button segundos5 = (Button) findViewById(R.id.button);
        Button segundos10 = (Button) findViewById(R.id.button2);
        Button segundos15 = (Button) findViewById(R.id.button3);
        Button segundos20 = (Button) findViewById(R.id.button4);
        Button segundos30 = (Button) findViewById(R.id.button5);
        Button segundos40 = (Button) findViewById(R.id.button6);
        Button segundos45 = (Button) findViewById(R.id.button7);
        Button segundos50 = (Button) findViewById(R.id.button8);
        Button segundos55 = (Button) findViewById(R.id.button9);
        Button segundos60 = (Button) findViewById(R.id.button10);
        Button segundos65 = (Button) findViewById(R.id.button11);
        Button segundos70 = (Button) findViewById(R.id.button12);
        Button segundos75 = (Button) findViewById(R.id.button13);
        Button segundos80 = (Button) findViewById(R.id.button14);
        Button segundos90 = (Button) findViewById(R.id.button15);
        Button segundos100 = (Button) findViewById(R.id.button16);
        Button segundos110 = (Button) findViewById(R.id.button17);
        Button segundos120 = (Button) findViewById(R.id.button18);
        Button segundos180 = (Button) findViewById(R.id.button19);
        Button segundos240 = (Button) findViewById(R.id.button20);
        Button segundos300 = (Button) findViewById(R.id.button21);
        Button segundos360 = (Button) findViewById(R.id.button22);
        Button segundos480 = (Button) findViewById(R.id.button23);
        Button segundos600 = (Button) findViewById(R.id.button24);


        OnBackPressedCallback callback = new OnBackPressedCallback(true /* enabled by default */) {
            @Override
            public void handleOnBackPressed() {
                // Handle the back button event
                Intent myintent = new Intent(MainActivity.this, PantallaInicial.class);
                startActivity(myintent);

            }
        };





        Tsegundos5 = (TextView) findViewById(R.id.record5);
        Tsegundos10 = (TextView) findViewById(R.id.record10);
        Tsegundos15 = (TextView) findViewById(R.id.record15);
        Tsegundos20 = (TextView) findViewById(R.id.record20);
        Tsegundos30 = (TextView) findViewById(R.id.record30);
        Tsegundos40 = (TextView) findViewById(R.id.record40);
        Tsegundos45 = (TextView) findViewById(R.id.record45);
        Tsegundos50 = (TextView) findViewById(R.id.record50);
        Tsegundos55 = (TextView) findViewById(R.id.record55);
        Tsegundos60 = (TextView) findViewById(R.id.record60);
        Tsegundos65 = (TextView) findViewById(R.id.record65);
        Tsegundos70 = (TextView) findViewById(R.id.record70);
        Tsegundos75 = (TextView) findViewById(R.id.record75);
        Tsegundos80 = (TextView) findViewById(R.id.record80);
        Tsegundos90 = (TextView) findViewById(R.id.record90);
        Tsegundos100 = (TextView) findViewById(R.id.record100);
        Tsegundos110 = (TextView) findViewById(R.id.record110);
        Tsegundos120 = (TextView) findViewById(R.id.record120);
        Tsegundos180 = (TextView) findViewById(R.id.record180);
        Tsegundos240 = (TextView) findViewById(R.id.record240);
        Tsegundos300 = (TextView) findViewById(R.id.record300);
        Tsegundos360 = (TextView) findViewById(R.id.record360);
        Tsegundos480 = (TextView) findViewById(R.id.record480);
        Tsegundos600 = (TextView) findViewById(R.id.record600);


        Tsegundos52 = (TextView) findViewById(R.id.record52);
        Tsegundos102 = (TextView) findViewById(R.id.record102);
        Tsegundos152 = (TextView) findViewById(R.id.record152);
        Tsegundos202 = (TextView) findViewById(R.id.record202);
        Tsegundos302 = (TextView) findViewById(R.id.record302);
        Tsegundos402 = (TextView) findViewById(R.id.record402);
        Tsegundos452 = (TextView) findViewById(R.id.record452);
        Tsegundos502 = (TextView) findViewById(R.id.record502);
        Tsegundos552 = (TextView) findViewById(R.id.record552);
        Tsegundos602 = (TextView) findViewById(R.id.record602);
        Tsegundos652 = (TextView) findViewById(R.id.record652);
        Tsegundos702 = (TextView) findViewById(R.id.record702);
        Tsegundos752 = (TextView) findViewById(R.id.record752);
        Tsegundos802 = (TextView) findViewById(R.id.record802);
        Tsegundos902 = (TextView) findViewById(R.id.record902);
        Tsegundos1002 = (TextView) findViewById(R.id.record1002);
        Tsegundos1102 = (TextView) findViewById(R.id.record1102);
        Tsegundos1202 = (TextView) findViewById(R.id.record1202);
        Tsegundos1802 = (TextView) findViewById(R.id.record1802);
        Tsegundos2402 = (TextView) findViewById(R.id.record2402);
        Tsegundos3002 = (TextView) findViewById(R.id.record3002);
        Tsegundos3602 = (TextView) findViewById(R.id.record3602);
        Tsegundos4802 = (TextView) findViewById(R.id.record4802);
        Tsegundos6002 = (TextView) findViewById(R.id.record6002);



        for (int i = 0; i < claves.length; i++) {
            recuperardatos(String.valueOf(tiempos[i]), claves[i]);

        }

        segundos5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent myintent = new Intent(MainActivity.this, segundos5.class);
                startActivity(myintent);
                Intent segundos5 = new Intent(MainActivity.this,segundos5.class);
                segundos5.putExtra("Segundos", segundoss5);
                startActivity(segundos5);


            }
        });

        segundos10.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent myintent = new Intent(MainActivity.this, segundos5.class);
                startActivity(myintent);
                Intent segundos10 = new Intent(MainActivity.this,segundos5.class);
                segundos10.putExtra("Segundos", segundoss10);
                startActivity(segundos10);


            }
        });

        segundos15.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent myintent = new Intent(MainActivity.this, segundos5.class);
                startActivity(myintent);
                Intent segundos15 = new Intent(MainActivity.this,segundos5.class);
                segundos15.putExtra("Segundos", segundoss15);
                startActivity(segundos15);


            }
        });

        segundos20.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent myintent = new Intent(MainActivity.this, segundos5.class);
                startActivity(myintent);
                Intent segundos20 = new Intent(MainActivity.this,segundos5.class);
                segundos20.putExtra("Segundos", segundoss20);
                startActivity(segundos20);


            }
        });

        segundos30.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent myintent = new Intent(MainActivity.this, segundos5.class);
                startActivity(myintent);
                Intent segundos30 = new Intent(MainActivity.this,segundos5.class);
                segundos30.putExtra("Segundos", segundoss30);
                startActivity(segundos30);

            }

        });

        segundos40.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent myintent = new Intent(MainActivity.this, segundos5.class);
                startActivity(myintent);
                Intent segundos40 = new Intent(MainActivity.this,segundos5.class);
                segundos40.putExtra("Segundos", segundoss40);
                startActivity(segundos40);

            }
        });

        segundos45.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent myintent = new Intent(MainActivity.this, segundos5.class);
                startActivity(myintent);
                Intent segundos45 = new Intent(MainActivity.this,segundos5.class);
                segundos45.putExtra("Segundos", segundoss45);
                startActivity(segundos45);

            }
        });

        segundos50.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent myintent = new Intent(MainActivity.this, segundos5.class);
                startActivity(myintent);
                Intent segundos50 = new Intent(MainActivity.this,segundos5.class);
                segundos50.putExtra("Segundos", segundoss50);
                startActivity(segundos50);

            }
        });

        segundos55.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent myintent = new Intent(MainActivity.this, segundos5.class);
                startActivity(myintent);
                Intent segundos55 = new Intent(MainActivity.this,segundos5.class);
                segundos55.putExtra("Segundos", segundoss55);
                startActivity(segundos55);

            }
        });

        segundos60.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent myintent = new Intent(MainActivity.this, segundos5.class);
                startActivity(myintent);
                Intent segundos60 = new Intent(MainActivity.this,segundos5.class);
                segundos60.putExtra("Segundos", segundoss60);
                startActivity(segundos60);

            }
        });

        segundos65.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent myintent = new Intent(MainActivity.this, segundos5.class);
                startActivity(myintent);
                Intent segundos65 = new Intent(MainActivity.this,segundos5.class);
                segundos65.putExtra("Segundos", segundoss65);
                startActivity(segundos65);

            }
        });

        segundos70.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent myintent = new Intent(MainActivity.this, segundos5.class);
                startActivity(myintent);
                Intent segundos70 = new Intent(MainActivity.this,segundos5.class);
                segundos70.putExtra("Segundos", segundoss70);
                startActivity(segundos70);

            }
        });

        segundos75.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent myintent = new Intent(MainActivity.this, segundos5.class);
                startActivity(myintent);
                Intent segundos75 = new Intent(MainActivity.this,segundos5.class);
                segundos75.putExtra("Segundos", segundoss75);
                startActivity(segundos75);

            }
        });

        segundos80.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent myintent = new Intent(MainActivity.this, segundos5.class);
                startActivity(myintent);
                Intent segundos80 = new Intent(MainActivity.this,segundos5.class);
                segundos80.putExtra("Segundos", segundoss80);
                startActivity(segundos80);

            }
        });

        segundos90.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent myintent = new Intent(MainActivity.this, segundos5.class);
                startActivity(myintent);
                Intent segundos90 = new Intent(MainActivity.this,segundos5.class);
                segundos90.putExtra("Segundos", segundoss90);
                startActivity(segundos90);

            }
        });

        segundos100.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent myintent = new Intent(MainActivity.this, segundos5.class);
                startActivity(myintent);
                Intent segundos100 = new Intent(MainActivity.this,segundos5.class);
                segundos100.putExtra("Segundos", segundoss100);
                startActivity(segundos100);

            }
        });

        segundos110.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent myintent = new Intent(MainActivity.this, segundos5.class);
                startActivity(myintent);
                Intent segundos110 = new Intent(MainActivity.this,segundos5.class);
                segundos110.putExtra("Segundos", segundoss110);
                startActivity(segundos110);

            }
        });

        segundos120.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent myintent = new Intent(MainActivity.this, segundos5.class);
                startActivity(myintent);
                Intent segundos120 = new Intent(MainActivity.this,segundos5.class);
                segundos120.putExtra("Segundos", segundoss120);
                startActivity(segundos120);

            }
        });

        segundos180.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent myintent = new Intent(MainActivity.this, segundos5.class);
                startActivity(myintent);
                Intent segundos180 = new Intent(MainActivity.this,segundos5.class);
                segundos180.putExtra("Segundos", segundoss180);
                startActivity(segundos180);

            }
        });

        segundos240.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent myintent = new Intent(MainActivity.this, segundos5.class);
                startActivity(myintent);
                Intent segundos240 = new Intent(MainActivity.this,segundos5.class);
                segundos240.putExtra("Segundos", segundoss240);
                startActivity(segundos240);

            }
        });

        segundos300.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent myintent = new Intent(MainActivity.this, segundos5.class);
                startActivity(myintent);
                Intent segundos300 = new Intent(MainActivity.this,segundos5.class);
                segundos300.putExtra("Segundos", segundoss300);
                startActivity(segundos300);

            }
        });

        segundos360.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent myintent = new Intent(MainActivity.this, segundos5.class);
                startActivity(myintent);
                Intent segundos360 = new Intent(MainActivity.this,segundos5.class);
                segundos360.putExtra("Segundos", segundoss360);
                startActivity(segundos360);

            }
        });

        segundos480.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent myintent = new Intent(MainActivity.this, segundos5.class);
                startActivity(myintent);
                Intent segundos480 = new Intent(MainActivity.this,segundos5.class);
                segundos480.putExtra("Segundos", segundoss480);
                startActivity(segundos480);

            }
        });

        segundos600.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent myintent = new Intent(MainActivity.this, segundos5.class);
                startActivity(myintent);
                Intent segundos600 = new Intent(MainActivity.this,segundos5.class);
                segundos600.putExtra("Segundos", segundoss600);
                startActivity(segundos600);

            }
        });




    }

    public void recuperardatos(String tiemposcal, final String clave){

        /**
         * HE CREADO DOS ARRAYS Y CON ELLOS VAMOS A OBTENER TODOS LOS RECODS
         */
        /**
         * PEDAZO DE CODIGO CON EL QUE CONSEGUIMOS RECUPERAR UN DATO DE LA BASE DE DATOS BUSCANDO DENTRO DEL documentSnapshot
         */
            db.collection("users").document(tiemposcal).get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
                @Override
                public void onSuccess(DocumentSnapshot documentSnapshot) {
                        recod = (String) documentSnapshot.get(clave);
                    Log.d("segundero2", "record " + recod);

                    if (clave.equals(claves[0])){
                        if (recod != null) {
                            Tsegundos5.setText("  " + World_Record +" " +recod);
                        }else{
                            Tsegundos5.setText("  " + World_Record0);
                            }
                    }else if(clave.equals(claves[1])){
                        if (recod != null) {
                        Tsegundos10.setText("  " + World_Record +" " +recod);
                        }else{
                            Tsegundos10.setText("  " + World_Record0);
                        }
                    }else if(clave.equals(claves[2])) {
                        if (recod != null) {
                            Tsegundos15.setText("  " + World_Record +" " +recod);
                        } else {
                            Tsegundos15.setText("  " + World_Record0);
                        }
                    }else if(clave.equals(claves[3])) {
                        if (recod != null) {
                            Tsegundos20.setText("  " + World_Record +" " +recod);
                        } else {
                            Tsegundos20.setText("  " + World_Record0);
                        }
                    }else if(clave.equals(claves[4])) {
                        if (recod != null) {
                            Tsegundos30.setText("  " + World_Record +" " +recod);
                        } else {
                            Tsegundos30.setText("  " + World_Record0);
                        }
                    }else if(clave.equals(claves[5])) {
                        if (recod != null) {
                            Tsegundos40.setText("  " + World_Record +" " +recod);
                        } else {
                            Tsegundos40.setText("  " + World_Record0);
                        }
                    }else if(clave.equals(claves[6])) {
                        if (recod != null) {
                            Tsegundos45.setText("  " + World_Record +" " +recod);
                        } else {
                            Tsegundos45.setText("  " + World_Record0);
                        }
                    }else if(clave.equals(claves[7])) {
                        if (recod != null) {
                            Tsegundos50.setText("  " + World_Record +" " +recod);
                        } else {
                            Tsegundos50.setText("  " + World_Record0);
                        }
                    }else if(clave.equals(claves[8])) {
                        if (recod != null) {
                            Tsegundos55.setText("  " + World_Record +" " +recod);
                        } else {
                            Tsegundos55.setText("  " + World_Record0);
                        }
                    }else if(clave.equals(claves[9])) {
                        if (recod != null) {
                            Tsegundos60.setText("  " + World_Record +" " +recod);
                        } else {
                            Tsegundos60.setText("  " + World_Record0);
                        }
                    }else if(clave.equals(claves[10])) {
                        if (recod != null) {
                            Tsegundos65.setText("  " + World_Record +" " +recod);
                        } else {
                            Tsegundos65.setText("  " + World_Record0);
                        }
                    }else if(clave.equals(claves[11])) {
                        if (recod != null) {
                            Tsegundos70.setText("  " + World_Record +" " +recod);
                        } else {
                            Tsegundos70.setText("  " + World_Record0);
                        }
                    }else if(clave.equals(claves[12])) {
                        if (recod != null) {
                            Tsegundos75.setText("  " + World_Record +" " +recod);
                        } else {
                            Tsegundos75.setText("  " + World_Record0);
                        }
                    }else if(clave.equals(claves[13])) {
                        if (recod != null) {
                            Tsegundos80.setText("  " + World_Record +" " +recod);
                        } else {
                            Tsegundos80.setText("  " + World_Record0);
                        }
                    }else if(clave.equals(claves[14])) {
                        if (recod != null) {
                            Tsegundos90.setText("  " + World_Record +" " +recod);
                        } else {
                            Tsegundos90.setText("  " + World_Record0);
                        }
                    }else if(clave.equals(claves[15])) {
                        if (recod != null) {
                            Tsegundos100.setText("  " + World_Record +" " +recod);
                        } else {
                            Tsegundos100.setText("  " + World_Record0);
                        }
                    }else if(clave.equals(claves[16])) {
                        if (recod != null) {
                            Tsegundos110.setText("  " + World_Record +" " +recod);
                        } else {
                            Tsegundos110.setText("  " + World_Record0);
                        }
                    }else if(clave.equals(claves[17])) {
                        if (recod != null) {
                            Tsegundos120.setText("  " + World_Record +" " +recod);
                        } else {
                            Tsegundos120.setText("  " + World_Record0);
                        }
                    }else if(clave.equals(claves[18])) {
                        if (recod != null) {
                            Tsegundos180.setText("  " + World_Record +" " +recod);
                        } else {
                            Tsegundos180.setText("  " + World_Record0);
                        }
                    }else if(clave.equals(claves[19])) {
                        if (recod != null) {
                            Tsegundos240.setText("  " + World_Record +" " +recod);
                        } else {
                            Tsegundos240.setText("  " + World_Record0);
                        }
                    }else if(clave.equals(claves[20])) {
                        if (recod != null) {
                            Tsegundos300.setText("  " + World_Record +" " +recod);
                        } else {
                            Tsegundos300.setText("  " + World_Record0);
                        }
                    }else if(clave.equals(claves[21])) {
                        if (recod != null) {
                            Tsegundos360.setText("  " + World_Record +" " +recod);
                        } else {
                            Tsegundos360.setText("  " + World_Record0);
                        }
                    }else if(clave.equals(claves[22])) {
                        if (recod != null) {
                            Tsegundos480.setText("  " + World_Record +" " +recod);
                        } else {
                            Tsegundos480.setText("  " + World_Record0);
                        }
                    }else if(clave.equals(claves[23])) {
                        if (recod != null) {
                            Tsegundos600.setText("  " + World_Record +" " +recod);
                        } else {
                            Tsegundos600.setText("  " + World_Record0);
                        }
                    }

                }
            });

        /**
         * Se recuperan las personas que tienen el record
         */

        db.collection("Personas").document(tiemposcal).get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
            @Override
            public void onSuccess(DocumentSnapshot documentSnapshot) {
                recod2 = (String) documentSnapshot.get(clave);
                Log.d("segundero2", "record " + recod);

                if (clave.equals(claves[0])){
                    if (recod2 != null) {
                        Tsegundos52.setText("  " +recod2);
                    }
                }else if(clave.equals(claves[1])){
                    if (recod2 != null) {
                        Tsegundos102.setText("  " +recod2);
                    }
                }else if(clave.equals(claves[2])) {
                    if (recod2 != null) {
                        Tsegundos152.setText("  " +recod2);
                    }
                }else if(clave.equals(claves[3])) {
                    if (recod2 != null) {
                        Tsegundos202.setText("  " +recod2);
                    }
                }else if(clave.equals(claves[4])) {
                    if (recod2 != null) {
                        Tsegundos302.setText("  " +recod2);
                    }
                }else if(clave.equals(claves[5])) {
                    if (recod2 != null) {
                        Tsegundos402.setText("  " +recod2);
                    }
                }else if(clave.equals(claves[6])) {
                    if (recod2 != null) {
                        Tsegundos452.setText("  " +recod2);
                    }
                }else if(clave.equals(claves[7])) {
                    if (recod2 != null) {
                        Tsegundos502.setText("  " +recod2);
                    }
                }else if(clave.equals(claves[8])) {
                    if (recod2 != null) {
                        Tsegundos552.setText("  " +recod2);
                    }
                }else if(clave.equals(claves[9])) {
                    if (recod2 != null) {
                        Tsegundos602.setText("  " +recod2);
                    }
                }else if(clave.equals(claves[10])) {
                    if (recod2 != null) {
                        Tsegundos652.setText("  " +recod2);
                    }
                }else if(clave.equals(claves[11])) {
                    if (recod2 != null) {
                        Tsegundos702.setText("  " +recod2);
                    }
                }else if(clave.equals(claves[12])) {
                    if (recod2 != null) {
                        Tsegundos752.setText("  " +recod2);
                    }
                }else if(clave.equals(claves[13])) {
                    if (recod2 != null) {
                        Tsegundos802.setText("  " +recod2);
                    }
                }else if(clave.equals(claves[14])) {
                    if (recod2 != null) {
                        Tsegundos902.setText("  " +recod2);
                    }
                }else if(clave.equals(claves[15])) {
                    if (recod2 != null) {
                        Tsegundos1002.setText("  " +recod2);
                    }
                }else if(clave.equals(claves[16])) {
                    if (recod2 != null) {
                        Tsegundos1102.setText("  " +recod2);
                    }
                }else if(clave.equals(claves[17])) {
                    if (recod2 != null) {
                        Tsegundos1202.setText("  " +recod2);
                    }
                }else if(clave.equals(claves[18])) {
                    if (recod2 != null) {
                        Tsegundos1802.setText("  " +recod2);
                    }
                }else if(clave.equals(claves[19])) {
                    if (recod2 != null) {
                        Tsegundos2402.setText("  " +recod2);
                    }
                }else if(clave.equals(claves[20])) {
                    if (recod2 != null) {
                        Tsegundos3002.setText("  " +recod2);
                    }
                }else if(clave.equals(claves[21])) {
                    if (recod2 != null) {
                        Tsegundos3602.setText("  " +recod2);
                    }
                }else if(clave.equals(claves[22])) {
                    if (recod2 != null) {
                        Tsegundos4802.setText("  " +recod2);
                    }
                }else if(clave.equals(claves[23])) {
                    if (recod2 != null) {
                        Tsegundos6002.setText("  " +recod2);
                    }
                }

            }
        });





    }
    public void onBackPressed() {
        Intent myintent = new Intent(MainActivity.this, PantallaInicial.class);
        startActivity(myintent);
    }
}


