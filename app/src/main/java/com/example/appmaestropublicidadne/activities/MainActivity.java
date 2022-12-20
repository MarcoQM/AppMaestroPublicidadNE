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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        /*Zone zone1 = new Zone();
        zone1.setName("Arequipa");

        Zone zone2 = new Zone();
        zone2.setName("Lima");

        ZonesRepository zonesRepository = ZonesRepository.get(this);
        zonesRepository.addZone(zone1);
        zonesRepository.addZone(zone2);



        zonesRepository.getZones().forEach(System.out::println);*/

        /*Client client1 = new Client();
        client1.setId(1);
        client1.setName("MarcoAntonio");
        ClientsRepository clientsRepository = ClientsRepository.get(this);
        clientsRepository.updateClient(client1);

        clientsRepository.getClients().forEach(System.out::println);*/


        Publicity publicity = new Publicity();
        publicity.setName("Empresa");
        publicity.setClientId(1);
        publicity.setZoneId(2);

        PublicitiesRepository publicitiesRepository = PublicitiesRepository.get(this);
        //publicitiesRepository.addPublicity(publicity);
        publicitiesRepository.getPublicities().forEach(System.out::println);


        Button publicidad = findViewById(R.id.button_advertising);
        Button zonas = findViewById(R.id.button_zone);
        Button clientes = findViewById(R.id.button_client);


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