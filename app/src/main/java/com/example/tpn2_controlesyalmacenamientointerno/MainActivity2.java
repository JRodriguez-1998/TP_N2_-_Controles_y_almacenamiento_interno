package com.example.tpn2_controlesyalmacenamientointerno;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.CheckBox;
import android.widget.RadioButton;
import android.widget.Switch;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.ObjectOutput;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class MainActivity2 extends AppCompatActivity {

    private RadioButton r1,r2,r3,r4,r5;
    private CheckBox chk1,chk2,chk3,chk4;
    private Switch sw;
    private Contacto contacto;

    ArrayList<Contacto> listContactos;

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

        listContactos = new ArrayList<>();

        //Precargo un ArrayList para poder sumar nuevos registros
        precargarLista();
    }

    public void precargarLista(){
        SharedPreferences prefs = getSharedPreferences("contactos", Context.MODE_PRIVATE);
        Gson gson = new Gson();  //Instancia Gson.
        String json1 = prefs.getString("contacto",null);
        Type type = new TypeToken<ArrayList<Contacto>>() {}.getType();
        listContactos = gson.fromJson(json1, type);
        if(listContactos == null) listContactos = new ArrayList<>();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu m){
        getMenuInflater().inflate(R.menu.menu_activity,m);
        return true;
    }

    public void ejecutar_opc1(View view){
        Intent i= new Intent(this,MainActivity.class);
        startActivity(i);
    }
    public void ejecutar_opc2(View view){
        Intent i= new Intent(this,MainActivity3.class);
        startActivity(i);
    }


    @Override
    public  boolean onOptionsItemSelected(MenuItem opcion_menu){
        int id=opcion_menu.getItemId();

        if(id==R.id.opc1){
            ejecutar_opc1(null);
        }

        if(id==R.id.opc2){
            ejecutar_opc2(null);
        }

        return super.onOptionsItemSelected(opcion_menu);
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
            //ObjectOutputStream objOutput = new ObjectOutputStream(new FileOutputStream("contactos.dat", true));
            //objOutput.writeObject(contacto);
            //objOutput.close();

            SharedPreferences prefs = getSharedPreferences("contactos", Context.MODE_PRIVATE);
            Gson gson = new Gson();  //Instancia Gson.

            listContactos.add(contacto);

            SharedPreferences.Editor prefsEditor = prefs.edit();
            String json = gson.toJson(listContactos); //convierte a .json el objeto
            prefsEditor.putString("contacto", json);
            prefsEditor.apply();

            Toast.makeText(this, "El contacto se ha guardado correctamente.", Toast.LENGTH_SHORT).show();

            //Una vez guardado, irá al Activity del ListView
                Intent i= new Intent(this,MainActivity3.class);
                startActivity(i);
        }
        catch (Exception ex) {
            Toast.makeText(this, "Ha ocurrido un error al guardar el contacto.", Toast.LENGTH_SHORT).show();
        }
    }
}