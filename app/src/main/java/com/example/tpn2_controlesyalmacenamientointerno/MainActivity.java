package com.example.tpn2_controlesyalmacenamientointerno;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;

import java.util.Calendar;

public class MainActivity extends AppCompatActivity {

    EditText txtNombre, txtApellido, txtTelefono, txtEmail, txtDireccion, txtFechaNac;
    Spinner spinnerContacto, spinner2;
    DatePickerDialog.OnDateSetListener setListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        txtNombre = (EditText) findViewById(R.id.txtNombre);
        txtApellido = (EditText) findViewById(R.id.txtApellido);
        txtTelefono = (EditText) findViewById(R.id.txtTelefono);
        txtEmail = (EditText) findViewById(R.id.txtEmail);
        txtDireccion = (EditText) findViewById(R.id.txtDireccion);

        spinnerContacto = (Spinner) findViewById(R.id.spinnerTelefono);
        spinner2 = (Spinner) findViewById(R.id.spinner2);

        String []opciones = {"Casa", "Trabajo", "Móvil"};
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, R.layout.spinner_item_teleymail, opciones);
        spinnerContacto.setAdapter(adapter);
        spinner2.setAdapter(adapter);

        //Creación de Calendario en el EditText tipo Date
        txtFechaNac = (EditText) findViewById(R.id.txtFecha);

            Calendar calendar = Calendar.getInstance();
            final int anio = calendar.get(Calendar.YEAR);
            final int mes = calendar.get(Calendar.MONTH);
            final int dia = calendar.get(Calendar.DAY_OF_MONTH);

            txtFechaNac.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    DatePickerDialog datePickerDialog = new DatePickerDialog(MainActivity.this,android.R.style.Theme_Holo_Dialog_MinWidth
                            ,setListener,anio,mes,dia);
                    datePickerDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                    datePickerDialog.show();
                }
            });
            setListener = new DatePickerDialog.OnDateSetListener() {
                @Override
                public void onDateSet(DatePicker datePicker, int anio, int mes, int dia) {
                    mes = mes+1;
                    String fecha = dia + "/" + mes + "/" + anio;
                    txtFechaNac.setText(fecha);
                }
            };
    }

    @Override
    public boolean onCreateOptionsMenu(Menu m){
       getMenuInflater().inflate(R.menu.menu_activity,m);
       return true;
    }

    public void SiguientePagina(View view){
        Intent i = new Intent(this, MainActivity2.class);
        i.putExtra("nombre",txtNombre.getText().toString());
        i.putExtra("apellido",txtApellido.getText().toString());
        i.putExtra("telefono",txtTelefono.getText().toString());
        i.putExtra("email",txtEmail.getText().toString());
        i.putExtra("direccion",txtDireccion.getText().toString());
        i.putExtra("tipotel",spinnerContacto.getSelectedItem().toString());
        i.putExtra("spinner2",spinner2.getSelectedItem().toString());
        i.putExtra("fechanac",txtFechaNac.getText().toString());

        startActivity(i);
    }
}