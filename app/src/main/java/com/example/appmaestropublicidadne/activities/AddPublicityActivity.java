package com.example.appmaestropublicidadne.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.appmaestropublicidadne.Publicity.PublicitiesRepository;
import com.example.appmaestropublicidadne.Publicity.Publicity;
import com.example.appmaestropublicidadne.R;
import com.example.appmaestropublicidadne.client.Client;
import com.example.appmaestropublicidadne.client.ClientsRepository;
import com.example.appmaestropublicidadne.zone.Zone;
import com.example.appmaestropublicidadne.zone.ZonesRepository;

import java.util.ArrayList;
import java.util.List;

public class AddPublicityActivity extends AppCompatActivity {
    Button buttonAddPublicity;
    EditText editTextPublicityName;
    EditText editTextPublicityCode;
    Spinner spinnerPublicityClients;
    Spinner spinnerPublicityZones;

    PublicitiesRepository publicitiesRepository;
    ClientsRepository clientsRepository;
    ZonesRepository zonesRepository;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_publicity);

        buttonAddPublicity = findViewById(R.id.buttonSavePublicity);
        editTextPublicityName = findViewById(R.id.editTextPublicityName);
        editTextPublicityCode = findViewById(R.id.editTextPublicityCode);
        spinnerPublicityClients = findViewById(R.id.spinnerPublicityClients);
        spinnerPublicityZones = findViewById(R.id.spinnerPublicityZones);

        publicitiesRepository = PublicitiesRepository.get(this);
        clientsRepository = ClientsRepository.get(this);
        zonesRepository = ZonesRepository.get(this);

        dataSpinnerClients();
        dataSpinnerZones();



        buttonAddPublicity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Publicity publicity = new Publicity();

                Client client = (Client) spinnerPublicityClients.getSelectedItem();
                Zone zone = (Zone) spinnerPublicityZones.getSelectedItem();


                publicity.setId(editTextPublicityCode.getText().toString());
                publicity.setName(editTextPublicityName.getText().toString());
                publicity.setClientId(client.getId());
                publicity.setZoneId(zone.getId());

                //Guardar datos
                publicitiesRepository.addPublicity(publicity);

                Toast.makeText(AddPublicityActivity.this, "Se guardaron los datos con éxito", Toast.LENGTH_SHORT).show();
                Intent next = new Intent(getApplicationContext(), PublicityActivity.class);
                startActivity(next);

            }
        });

    }

    public void dataSpinnerClients() {
        List<Client> clients = new ArrayList<>();
        ArrayList <Client> listaClients = new ArrayList<>();
        clients = clientsRepository.getClients();
        for (Client c : clients){
            if (c.getRegistrationStatus().equalsIgnoreCase("A")){
                listaClients.add(c);
            }
        }

        ArrayAdapter<Client> adapter = new ArrayAdapter<Client>(this, android.R.layout.simple_spinner_item, listaClients);
        spinnerPublicityClients.setAdapter(adapter);

    }

    public void dataSpinnerZones() {
        List<Zone> zones = new ArrayList<>();
        ArrayList <Zone> listaZones = new ArrayList<>();
        zones = zonesRepository.getZones();
        for (Zone z : zones){
            if (z.getRegistrationStatus().equalsIgnoreCase("A")){
                listaZones.add(z);
            }
        }
        ArrayAdapter<Zone> adapter = new ArrayAdapter<Zone>(this, android.R.layout.simple_spinner_item, listaZones);
        spinnerPublicityZones.setAdapter(adapter);
    }
}