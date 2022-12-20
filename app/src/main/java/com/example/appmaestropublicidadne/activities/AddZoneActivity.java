package com.example.appmaestropublicidadne.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.appmaestropublicidadne.R;
import com.example.appmaestropublicidadne.zone.Zone;
import com.example.appmaestropublicidadne.zone.ZonesRepository;

public class AddZoneActivity extends AppCompatActivity {

    Button buttonAddZone;
    EditText editTextZoneName;
    EditText editTextZoneCode;

    ZonesRepository zonesRepository;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_zone);

        buttonAddZone = findViewById(R.id.buttonSaveZone);
        editTextZoneName = findViewById(R.id.editTextZoneName);
        editTextZoneCode = findViewById(R.id.editTextZoneCode);
        zonesRepository = ZonesRepository.get(this);


        buttonAddZone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view){
                Zone zone = new Zone();
                zone.setId(editTextZoneCode.getText().toString());
                zone.setName(editTextZoneName.getText().toString());
                Intent next = new Intent(getApplicationContext(), ZoneActivity.class);
                startActivity(next);

                //Guardar datos
                zonesRepository.addZone(zone);

                Toast.makeText(AddZoneActivity.this, "Se guardaron los datos con éxito", Toast.LENGTH_SHORT).show();

            }
        });
    }
}