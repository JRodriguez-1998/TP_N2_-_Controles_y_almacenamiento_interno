package com.example.tpn2_controlesyalmacenamientointerno;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.Menu;
import android.widget.CheckBox;
import android.widget.RadioButton;
import android.widget.Switch;

public class MainActivity2 extends AppCompatActivity {

    private RadioButton r1,r2,r3,r4,r5;
    private CheckBox chk1,chk2,chk3,chk4;
    private Switch sw;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

      String nombre= getIntent().getStringExtra("nombre");
      String apellido= getIntent().getStringExtra("apellido");
      String telefono= getIntent().getStringExtra("telefono");
      String email= getIntent().getStringExtra("email");
      String direccion= getIntent().getStringExtra("direccion");
      String tipotel= getIntent().getStringExtra("tipotel");
      String spinner2= getIntent().getStringExtra("spinner2");
      String fechanac= getIntent().getStringExtra("fechanac");

      r1=(RadioButton) findViewById(R.id.rbPrimarioIncompleto);
      r2=(RadioButton) findViewById(R.id.rbPrimarioCompleto);
      r3=(RadioButton) findViewById(R.id.rbSecundarioIncompleto);
      r4=(RadioButton) findViewById(R.id.rbSecundarioCompleto);
      r5=(RadioButton) findViewById(R.id.rbOtros);
      chk1=(CheckBox) findViewById(R.id.cbDeporte);
      chk2=(CheckBox) findViewById(R.id.cbMusica);
      chk3=(CheckBox) findViewById(R.id.cbArte);
      chk4=(CheckBox) findViewById(R.id.cbTecnologia);
      sw=(Switch) findViewById(R.id.switchInfo);


    }

    @Override
    public boolean onCreateOptionsMenu(Menu m){
        getMenuInflater().inflate(R.menu.menu_activity,m);
        return true;
    }
}