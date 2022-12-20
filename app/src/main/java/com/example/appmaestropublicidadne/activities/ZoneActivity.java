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
import com.example.appmaestropublicidadne.adapters.ZoneRecyclerViewAdapter;
import com.example.appmaestropublicidadne.zone.Zone;
import com.example.appmaestropublicidadne.zone.ZonesRepository;

import java.util.ArrayList;
import java.util.List;

public class ZoneActivity extends AppCompatActivity implements SearchView.OnQueryTextListener{
    private static String TAG = "ZoneActivity";

    ZoneRecyclerViewAdapter zoneRecyclerViewAdapter;
    RecyclerView recyclerViewZones;
    SearchView searchViewZones;
    Button buttonAddZone;

    ZonesRepository zonesRepository;
    List<Zone> zoneList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_zone);

        zoneList = new ArrayList<>();
        searchViewZones = findViewById(R.id.searchViewZone);
        recyclerViewZones = findViewById(R.id.recyclerViewZones);
        buttonAddZone = findViewById(R.id.buttonRegisterZone);

        recyclerViewZones.setLayoutManager(new LinearLayoutManager(this));

        zonesRepository = ZonesRepository.get(this);
        //Mostrar los datos
        zoneRecyclerViewAdapter = new ZoneRecyclerViewAdapter(zonesRepository.getZones());
        recyclerViewZones.setAdapter(zoneRecyclerViewAdapter);

        buttonAddZone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addZone();
            }
        });

        searchViewZones.setOnQueryTextListener(this);

    }

    public void addZone() {
        Intent intent = new Intent(this, AddZoneActivity.class);
        startActivity(intent);
    }

    @Override
    public boolean onQueryTextSubmit(String s) {
        return false;
    }

    @Override
    public boolean onQueryTextChange(String s) {
        zoneRecyclerViewAdapter.plasticFilter(s);
        return false;
    }
}