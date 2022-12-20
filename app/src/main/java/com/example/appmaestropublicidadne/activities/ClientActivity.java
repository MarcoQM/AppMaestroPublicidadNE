package com.example.appmaestropublicidadne.activities;
import androidx.appcompat.app.AppCompatActivity;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.appcompat.widget.SearchView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.appmaestropublicidadne.R;
import com.example.appmaestropublicidadne.adapters.ClientRecyclerViewAdapter;
import com.example.appmaestropublicidadne.client.Client;
import com.example.appmaestropublicidadne.client.ClientsRepository;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

public class ClientActivity extends AppCompatActivity implements androidx.appcompat.widget.SearchView.OnQueryTextListener{
    private static String TAG = "ClientActivity";

    ClientRecyclerViewAdapter clientRecyclerViewAdapter;
    RecyclerView recyclerViewClients;
    SearchView searchViewClients;
    Button buttonAddClient;

    FloatingActionButton floatingActionButton;

    ClientsRepository clientsRepository;
    List<Client> clientList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_client);

        clientList = new ArrayList<>();
        searchViewClients = findViewById(R.id.searchViewClient);
        recyclerViewClients = findViewById(R.id.recyclerViewClients);
        buttonAddClient = findViewById(R.id.buttonRegisterClient);
        floatingActionButton = findViewById(R.id.floatingActionButtonClientHome);

        recyclerViewClients.setLayoutManager(new LinearLayoutManager(this));
        clientsRepository = ClientsRepository.get(this);
        searchViewClients.setOnQueryTextListener(this);

        //Mostrar datos
        clientRecyclerViewAdapter = new ClientRecyclerViewAdapter(clientsRepository.getClients(), this);
        recyclerViewClients.setAdapter(clientRecyclerViewAdapter);

        buttonAddClient.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addClient();
            }
        });

        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                goToHome();
            }
        });
    }

    public void goToHome() {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);

    }

    public void addClient() {
        Intent intent = new Intent(this, AddClientActivity.class);
        startActivity(intent);
    }

    @Override
    public boolean onQueryTextSubmit(String s) {
        return false;
    }

    @Override
    public boolean onQueryTextChange(String s) {
        clientRecyclerViewAdapter.clientFilter(s);
        return false;
    }
}