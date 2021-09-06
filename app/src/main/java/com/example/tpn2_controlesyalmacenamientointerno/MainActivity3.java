package com.example.tpn2_controlesyalmacenamientointerno;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.EOFException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class MainActivity3 extends AppCompatActivity {

    private TextView tvContactos;
    private ListView lv1;
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);

        tvContactos = (TextView) findViewById(R.id.tvContactos);
        lv1=(ListView) findViewById(R.id.lv1);

        List<Contacto> listContactos = new ArrayList<Contacto>();
        try{
            ObjectInputStream objInput = new ObjectInputStream(openFileInput("contactos.dat"));

            Contacto contacto = (Contacto) objInput.readObject();
            while (contacto != null) {
                listContactos.add(contacto);
                contacto = (Contacto) objInput.readObject();
            }

            objInput.close();
        } catch (EOFException ex) {
            // Fin del archivo.
        } catch (IOException e){
            Toast.makeText(this, "Ha ocurrido un error al cargar el archivo.", Toast.LENGTH_SHORT).show();
        } catch (ClassNotFoundException e){
            Log.e("MainActivity", "Error clase no encontrada");
        }

        ArrayAdapter<Contacto> adapter = new ArrayAdapter<>(this, R.layout.list_item, listContactos);
        lv1.setAdapter(adapter);
    }
}