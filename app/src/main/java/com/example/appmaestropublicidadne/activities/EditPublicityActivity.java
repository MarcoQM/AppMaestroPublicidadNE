package com.example.appmaestropublicidadne.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.appmaestropublicidadne.Publicity.PublicitiesRepository;
import com.example.appmaestropublicidadne.Publicity.Publicity;
import com.example.appmaestropublicidadne.R;
import com.example.appmaestropublicidadne.client.Client;
import com.example.appmaestropublicidadne.client.ClientsRepository;
import com.example.appmaestropublicidadne.zone.Zone;
import com.example.appmaestropublicidadne.zone.ZonesRepository;

public class EditPublicityActivity extends AppCompatActivity {

    Button buttonEditPublicity;
    EditText editTextPublicityNameEdit;
    EditText editTextPublicityCodeEdit;
    Spinner spinnerPublicityClientsEdit;
    Spinner spinnerPublicityZonesEdit;
    RadioGroup radioGroupPublicityStatusEdit;
    RadioButton radioButtonPublicityStatusActive;
    RadioButton radioButtonPublicityStatusInactive;
    RadioButton radioButtonPublicityStatusDelete;

    String publicityId;
    PublicitiesRepository publicitiesRepository;
    ClientsRepository clientsRepository;
    ZonesRepository zonesRepository;

    Publicity publicity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_publicity);

        publicitiesRepository = PublicitiesRepository.get(this);
        clientsRepository = ClientsRepository.get(this);
        zonesRepository = ZonesRepository.get(this);

        if (savedInstanceState == null) {
            Bundle extras = getIntent().getExtras();
            if (extras == null) {
                publicityId = null;
            } else {
                publicityId = extras.getString("ID");
            }
        } else {
            publicityId = (String) savedInstanceState.getSerializable("ID");
        }

        //Campos y botones del layout editar
        buttonEditPublicity = findViewById(R.id.buttonSavePublicityEdit);
        editTextPublicityNameEdit = findViewById(R.id.editTextPublicityNameEdit);
        editTextPublicityCodeEdit = findViewById(R.id.editTextPublicityCodeEdit);
        spinnerPublicityClientsEdit = findViewById(R.id.spinnerPublicityClientsEdit);
        spinnerPublicityZonesEdit = findViewById(R.id.spinnerPublicityZonesEdit);
        radioGroupPublicityStatusEdit = findViewById(R.id.radioButtonPublicityStatusEdit);
        radioButtonPublicityStatusActive = findViewById(R.id.radioButtonPublicityStatusActive);
        radioButtonPublicityStatusInactive = findViewById(R.id.radioButtonPublicityStatusInactive);
        radioButtonPublicityStatusDelete = findViewById(R.id.radioButtonPublicityStatusDelete);

        //el id no debe cambiar
        editTextPublicityCodeEdit.setKeyListener(null);

        //Se llenan los campos con los datos de la publicidad que se quiere editar
        publicity = publicitiesRepository.getPublicity(publicityId);
        dataSpinnerClients();
        dataSpinnerZones();
        dataCheckBoxZones();
        editTextPublicityCodeEdit.setText(publicity.getId());
        editTextPublicityNameEdit.setText(publicity.getName());

        //Se actualizar la publicidad
        buttonEditPublicity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Publicity publicity = new Publicity();

                Client client = (Client) spinnerPublicityClientsEdit.getSelectedItem();
                Zone zone = (Zone) spinnerPublicityZonesEdit.getSelectedItem();

                publicity.setId(editTextPublicityCodeEdit.getText().toString());
                publicity.setName(editTextPublicityNameEdit.getText().toString());
                publicity.setClientId(client.getId());
                publicity.setZoneId(zone.getId());
                publicity.setRegistrationStatus(verifyCheckPublicityStatus());

                //Guardar datos
                publicitiesRepository.updatePublicity(publicity);

                Toast.makeText(EditPublicityActivity.this, "Se actualizaron los datos con éxito", Toast.LENGTH_SHORT).show();
                Intent next = new Intent(getApplicationContext(), PublicityActivity.class);
                startActivity(next);
            }
        });


    }

    public void dataSpinnerClients() {
        ArrayAdapter<Client> adapter = new ArrayAdapter<>(this, androidx.appcompat.R.layout.support_simple_spinner_dropdown_item, clientsRepository.getClients());
        spinnerPublicityClientsEdit.setAdapter(adapter);
        spinnerPublicityClientsEdit.setSelection(adapter.getPosition(clientsRepository.getClient(publicity.getClientId())));

    }

    public void dataSpinnerZones() {
        ArrayAdapter<Zone> adapter = new ArrayAdapter<>(this, androidx.appcompat.R.layout.support_simple_spinner_dropdown_item, zonesRepository.getZones());
        spinnerPublicityZonesEdit.setAdapter(adapter);
        spinnerPublicityZonesEdit.setSelection(adapter.getPosition(zonesRepository.getZone(publicity.getZoneId())));
    }

    public void dataCheckBoxZones() {
        switch (publicity.getRegistrationStatus()) {
            case "A":
                radioButtonPublicityStatusActive.setChecked(true);
                break;
            case "I":
                radioButtonPublicityStatusInactive.setChecked(true);
                break;
            case "*":
                radioButtonPublicityStatusDelete.setChecked(true);
                break;

        }
    }

    public String verifyCheckPublicityStatus() {
        if (radioButtonPublicityStatusActive.isChecked()) {
            return "A";
        } else if(radioButtonPublicityStatusInactive.isChecked()){
            return "I";
        } else if(radioButtonPublicityStatusDelete.isChecked()) {
            return "*";
        }
        return "*";
    }
}