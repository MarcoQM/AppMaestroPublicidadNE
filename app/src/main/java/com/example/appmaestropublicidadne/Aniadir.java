package com.example.appmaestropublicidadne;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

import com.example.appmaestropublicidadne.activities.AddZoneActivity;
import com.example.appmaestropublicidadne.activities.ZoneActivity;
import com.example.appmaestropublicidadne.client.Client;
import com.example.appmaestropublicidadne.client.ClientsRepository;
import com.example.appmaestropublicidadne.zone.Zone;
import com.example.appmaestropublicidadne.zone.ZonesRepository;

public class Aniadir extends AppCompatActivity {

    Button buttonAdd;
    EditText editTextName;
    EditText editTextCode;
    RadioButton activo, inactivo;
    String codigo, nombre, estado, tipo;

    ZonesRepository zonesRepository;
    ClientsRepository clientsRepository;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_aniadir);

        editTextName = findViewById(R.id.editTextName);
        editTextCode = findViewById(R.id.editTextCode);
        activo =findViewById(R.id.radioButtonActivo);
        inactivo = findViewById(R.id.radioButtonInactivo);
        buttonAdd = findViewById(R.id.buttonSave);

        codigo =getIntent().getStringExtra("EditarZona");
        nombre = getIntent().getStringExtra("NombreZona");
        estado = getIntent().getStringExtra("EstadoZona");
        tipo = "Zona";
        zonesRepository = ZonesRepository.get(this);

        if (codigo == null && nombre == null && estado == null){
            codigo =getIntent().getStringExtra("EditarCliente");
            nombre = getIntent().getStringExtra("NombreCliente");
            estado = getIntent().getStringExtra("EstadoCliente");

            tipo = "Cliente";
            clientsRepository = ClientsRepository.get(this);
        }

        editTextCode.setText(codigo);
        editTextName.setText(nombre);
        if (estado.equalsIgnoreCase("A")){
            activo.setChecked(true);
        }
        else{
            inactivo.setChecked(true);
        }

        buttonAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (tipo.equalsIgnoreCase("Zona")) {
                    Zone zone = new Zone();

                    zone.setId(codigo);
                    zone.setName(editTextName.getText().toString());
                    if (activo.isChecked()) {
                        zone.setRegistrationStatus("A");
                    } else {
                        zone.setRegistrationStatus("I");
                    }
                    //Guardar datos
                    zonesRepository.updateZone(zone);
                }
                else {
                    Client client = new Client();
                    client.setId(codigo);
                    client.setName(editTextName.getText().toString());
                    if (activo.isChecked()){
                        client.setRegistrationStatus("A");
                    }
                    else{
                        client.setRegistrationStatus("I");
                    }
                    clientsRepository.updateClient(client);
                }
                Toast.makeText(Aniadir.this, "Se editaron los datos de " + codigo + " con éxito", Toast.LENGTH_LONG).show();
                Intent next = new Intent(getApplicationContext(), ZoneActivity.class);
                startActivity(next);
            }
        });

    }
}