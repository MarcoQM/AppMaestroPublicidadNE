package com.example.appmaestropublicidadne.activities;
import androidx.appcompat.app.AppCompatActivity;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.example.appmaestropublicidadne.Aniadir;
import com.example.appmaestropublicidadne.R;
import com.example.appmaestropublicidadne.adapters.ClientRecyclerViewAdapter;
import com.example.appmaestropublicidadne.client.Client;

import java.util.ArrayList;

public class ClientActivity extends AppCompatActivity {
    private static String TAG = "ClientActivity";

    ClientRecyclerViewAdapter adapterClient;
    RecyclerView recyclerView;
    ArrayList<Client> clientes;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_client);

        Button aniadir = findViewById(R.id.button_aniadir);

        clientes = new ArrayList<Client>();
        cargarClientes();
        mostrarDatos();

        aniadir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view){
                Intent next = new Intent(getApplicationContext(), Aniadir.class);
                startActivity(next);
                String str = "";

                Log.d(TAG, str);
            }
        });
    }

    public void mostrarDatos() {
        adapterClient = new ClientRecyclerViewAdapter(this, clientes);
        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapterClient);
    }

    public void cargarClientes(){
        Client cliente1 = new Client();
        cliente1.setId(1);
        cliente1.setName("Arnold");
        cliente1.setRegistrationStatus("A");
        clientes.add(cliente1);

        Client cliente2 = new Client();
        cliente2.setId(2);
        cliente2.setName("Marco");
        cliente2.setRegistrationStatus("I");
        clientes.add(cliente2);

        Client cliente3 = new Client();
        cliente3.setId(3);
        cliente3.setName("Rodrigo");
        cliente3.setRegistrationStatus("E");
        clientes.add(cliente3);

        Client cliente4 = new Client();
        cliente4.setId(4);
        cliente4.setName("Anyela");
        cliente4.setRegistrationStatus("A");
        clientes.add(cliente4);
        Log.d(TAG, "mensaje\n"+clientes.get(0).toString());
        Log.d(TAG, "mensaje\n"+clientes.get(1).toString());
        Log.d(TAG, "mensaje\n"+clientes.get(2).toString());
        Log.d(TAG, "mensaje\n"+clientes.get(3).toString());
    }
}