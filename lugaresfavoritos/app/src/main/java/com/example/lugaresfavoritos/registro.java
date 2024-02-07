package com.example.lugaresfavoritos;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.lugaresfavoritos.database.Persona;

public class registro extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro);


        Intent intent = getIntent();
        String nombre = intent.getStringExtra("NOMBRE");
        String direccion = intent.getStringExtra("DIRECCION");
        String telefono = intent.getStringExtra("TELEFONO");

        // Mostrar los datos en TextViews u otros elementos de tu interfaz
        TextView textViewNombre = findViewById(R.id.textViewNombre);
        TextView textViewDireccion = findViewById(R.id.textViewDireccion);
        TextView textViewTelefono = findViewById(R.id.textViewTelefono);
        textViewNombre.setText(nombre);
        textViewDireccion.setText(direccion);
        textViewTelefono.setText(telefono);




    }

}