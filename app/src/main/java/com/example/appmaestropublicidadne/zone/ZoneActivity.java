package com.example.appmaestropublicidadne.zone;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.appmaestropublicidadne.Aniadir;
import com.example.appmaestropublicidadne.R;
import com.example.appmaestropublicidadne.adapters.ClientRecyclerViewAdapter;
import com.example.appmaestropublicidadne.adapters.ZoneRecyclerViewAdapter;
import com.example.appmaestropublicidadne.zone.Zone;

import java.util.ArrayList;

public class ZoneActivity extends AppCompatActivity {
    private static String TAG = "ZoneActivity";

    ZoneRecyclerViewAdapter adapterZone;
    RecyclerView recyclerView;
    ArrayList<Zone> zonas;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_zone);

        Button aniadir = findViewById(R.id.button_aniadir);

        zonas = new ArrayList<Zone>();
        cargarZonas();
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
        adapterZone = new ZoneRecyclerViewAdapter(this, zonas);
        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapterZone);
    }

    public void cargarZonas(){
        Zone zone1 = new Zone();
        zone1.setId(1);
        zone1.setName("Miraflores");
        zone1.setRegistrationStatus("A");
        zonas.add(zone1);

        Zone zone2 = new Zone();
        zone2.setId(2);
        zone2.setName("Alto Selva Alegre");
        zone2.setRegistrationStatus("I");
        zonas.add(zone2);

        Log.d(TAG, "mensaje\n"+zonas.get(0).toString());
        Log.d(TAG, "mensaje\n"+zonas.get(1).toString());
    }
}