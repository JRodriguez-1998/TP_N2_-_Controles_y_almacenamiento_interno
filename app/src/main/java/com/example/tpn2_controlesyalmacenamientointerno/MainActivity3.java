package com.example.tpn2_controlesyalmacenamientointerno;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.json.JSONArray;
import org.json.JSONException;

import java.io.EOFException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class MainActivity3 extends AppCompatActivity {

    private TextView tvContactos;
    private ListView lv1;

    ArrayList<Contacto> listContactos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);

        tvContactos = (TextView) findViewById(R.id.tvContactos);
        lv1=(ListView) findViewById(R.id.lv1);

        //List<Contacto> listContactos = new ArrayList<Contacto>();
        /*try{
            ObjectInputStream objInput = new ObjectInputStream(openFileInput("contactos.dat"));

            Contacto contacto = (Contacto) objInput.readObject();
            while (contacto != null) {
                listContactos.add(contacto);
                contacto = (Contacto) objInput.readObject();
            }

            objInput.close();
        } catch (EOFException ex) {
             //Fin del archivo.
        } catch (IOException e){
            Toast.makeText(this, "Ha ocurrido un error al cargar el archivo.", Toast.LENGTH_SHORT).show();
        } catch (ClassNotFoundException e){
            Log.e("MainActivity", "Error clase no encontrada");
        }*/

        listContactos = new ArrayList<>();
        SharedPreferences prefs = getSharedPreferences("contactos", Context.MODE_PRIVATE);
        Gson gson = new Gson();  //Instancia Gson.
        String json1 = prefs.getString("contacto",null);
        Type type = new TypeToken<ArrayList<Contacto>>() {}.getType();
        listContactos = gson.fromJson(json1, type);
        if(listContactos == null) {
            listContactos = new ArrayList<>();
            Toast.makeText(this, "No hay Registros", Toast.LENGTH_SHORT).show();
        }
        else {
            ArrayAdapter<Contacto> adapter = new ArrayAdapter<>(this, R.layout.list_item, listContactos);
            lv1.setAdapter(adapter);
        }

        lv1.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Contacto contacto = (Contacto) lv1.getItemAtPosition(i);

                tvContactos.setText("Nombre: " + contacto.getNombre() + " " + contacto.getApellido() + "\r\n" +
                        "Telefono " + contacto.getTipoTelefono() + " : " + contacto.getTelefono() + "\r\n" +
                        "Mail: " + contacto.getEmail() + "\r\n" +
                        "Direccion: " + contacto.getDireccion() + "\r\n" +
                        "Fecha de Nac.: " + contacto.getFechaNacimiento() + "\r\n" +
                        "Nivel de Estudios: " + contacto.getNivelEstudios() + "\r\n" +
                        "Intereses: " + contacto.getIntereses() + "\r\n" +
                        "Recibe info?: " + contacto.getRecibirInformacion());
            }
        });
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

}