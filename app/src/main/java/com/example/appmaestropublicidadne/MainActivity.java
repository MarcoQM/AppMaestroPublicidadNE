package com.example.appmaestropublicidadne;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import android.os.Bundle;

import com.example.appmaestropublicidadne.Publicity.PublicitiesRepository;
import com.example.appmaestropublicidadne.Publicity.Publicity;
import com.example.appmaestropublicidadne.client.Client;
import com.example.appmaestropublicidadne.client.ClientsRepository;
import com.example.appmaestropublicidadne.zone.Zone;
import com.example.appmaestropublicidadne.zone.ZonesRepository;

public class MainActivity extends AppCompatActivity {

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
        publicity.setName("Impresa");
        publicity.setClientId(1);
        publicity.setZoneId(2);

        PublicitiesRepository publicitiesRepository = PublicitiesRepository.get(this);
        //publicitiesRepository.addPublicity(publicity);
        publicitiesRepository.getPublicities().forEach(System.out::println);

    }
}