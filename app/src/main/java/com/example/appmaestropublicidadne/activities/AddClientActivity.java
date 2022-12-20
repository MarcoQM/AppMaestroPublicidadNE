package com.example.appmaestropublicidadne.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.appmaestropublicidadne.R;
import com.example.appmaestropublicidadne.client.Client;
import com.example.appmaestropublicidadne.client.ClientsRepository;
import com.example.appmaestropublicidadne.zone.Zone;

public class AddClientActivity extends AppCompatActivity {

    Button buttonAddClient;
    EditText editTextClientName;
    EditText editTextClientCode;

    ClientsRepository clientsRepository;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_client);

        buttonAddClient = findViewById(R.id.buttonSaveClient);
        editTextClientCode = findViewById(R.id.editTextClientCode);
        editTextClientName = findViewById(R.id.editTextClientName);
        clientsRepository = ClientsRepository.get(this);


        buttonAddClient.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view){
                Client client = new Client();
                client.setId(editTextClientCode.getText().toString());
                client.setName(editTextClientName.getText().toString());

                //Guardar datos
                clientsRepository.addClient(client);

                Toast.makeText(AddClientActivity.this, "Se guardaron los datos con éxito", Toast.LENGTH_LONG).show();

                Intent next = new Intent(getApplicationContext(), ClientActivity.class);
                startActivity(next);


            }
        });

    }
}