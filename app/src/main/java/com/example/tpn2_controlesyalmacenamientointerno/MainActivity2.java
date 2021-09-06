package com.example.tpn2_controlesyalmacenamientointerno;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.CheckBox;
import android.widget.RadioButton;
import android.widget.Switch;
import android.widget.Toast;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.ObjectOutput;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class MainActivity2 extends AppCompatActivity {

    private RadioButton r1,r2,r3,r4,r5;
    private CheckBox chk1,chk2,chk3,chk4;
    private Switch sw;
    private Contacto contacto;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        contacto = (Contacto) getIntent().getSerializableExtra("contacto");

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

    public void Guardar(View view) {
        if (r1.isChecked())
            contacto.setNivelEstudios(r1.getText().toString());
        else if (r2.isChecked())
            contacto.setNivelEstudios(r2.getText().toString());
        else if (r3.isChecked())
            contacto.setNivelEstudios(r3.getText().toString());
        else if (r4.isChecked())
            contacto.setNivelEstudios(r4.getText().toString());
        else if (r5.isChecked())
            contacto.setNivelEstudios(r5.getText().toString());
        else
        {
            Toast.makeText(this, "Debe seleccionar un nivel de estudios", Toast.LENGTH_SHORT).show();
            return;
        }

        List<String> intereses = new ArrayList<String>();
        if (chk1.isChecked())
            intereses.add(chk1.getText().toString());
        if (chk2.isChecked())
            intereses.add(chk2.getText().toString());
        if (chk3.isChecked())
            intereses.add(chk3.getText().toString());
        if (chk4.isChecked())
            intereses.add(chk4.getText().toString());
        contacto.setIntereses(intereses);

        contacto.setRecibirInformacion(sw.isChecked());

        try {
            ObjectOutputStream objOutput = new ObjectOutputStream(openFileOutput("contactos.dat", MODE_PRIVATE));
            objOutput.writeObject(contacto);
            objOutput.close();

            Toast.makeText(this, "El contacto se ha guardado correctamente.", Toast.LENGTH_SHORT).show();
        }
        catch (Exception ex) {
            Toast.makeText(this, "Ha ocurrido un error al guardar el contacto.", Toast.LENGTH_SHORT).show();
        }
    }
}