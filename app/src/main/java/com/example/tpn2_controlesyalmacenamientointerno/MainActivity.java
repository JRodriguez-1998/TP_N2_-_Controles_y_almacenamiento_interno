package com.example.tpn2_controlesyalmacenamientointerno;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.Menu;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;

public class MainActivity extends AppCompatActivity {

    EditText txtNombre, txtApellido, txtTelefono, txtEmail;
    Spinner spinnerContacto, spinner2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        txtNombre = (EditText) findViewById(R.id.txtNombre);
        txtApellido = (EditText) findViewById(R.id.txtApellido);
        txtTelefono = (EditText) findViewById(R.id.txtTelefono);
        txtEmail = (EditText) findViewById(R.id.txtEmail);
        spinnerContacto = (Spinner) findViewById(R.id.spinnerTelefono);
        spinner2 = (Spinner) findViewById(R.id.spinner2);

        String []opciones = {"Casa", "Trabajo", "Móvil"};
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, opciones);
        spinnerContacto.setAdapter(adapter);
        spinner2.setAdapter(adapter);
    }
    @Override
    public boolean onCreateOptionsMenu(Menu m){
       getMenuInflater().inflate(R.menu.menu_activity,m);
       return true;
    }
}