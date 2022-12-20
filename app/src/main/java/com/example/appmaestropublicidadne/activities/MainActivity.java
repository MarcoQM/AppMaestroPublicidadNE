package com.example.appmaestropublicidadne.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.example.appmaestropublicidadne.Publicity.PublicitiesRepository;
import com.example.appmaestropublicidadne.Publicity.Publicity;
import com.example.appmaestropublicidadne.R;

public class MainActivity extends AppCompatActivity {
    private static String TAG = "MainActivity";

    Button publicidad;
    Button zonas;
    Button clientes;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        publicidad = findViewById(R.id.button_advertising);
        zonas = findViewById(R.id.button_zone);
        clientes = findViewById(R.id.button_client);


        publicidad.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view){
                Intent next = new Intent(getApplicationContext(), PublicityActivity.class);
                startActivity(next);
                String str = "";

                Log.d(TAG, str);
            }
        });

        zonas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view){
                Intent next = new Intent(getApplicationContext(), ZoneActivity.class);
                startActivity(next);
                String str = "";

                Log.d(TAG, str);
            }
        });

        clientes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent next = new Intent(getApplicationContext(), ClientActivity.class);
                startActivity(next);
                String str = "";

                Log.d(TAG, str);
            }
        });
    }
}