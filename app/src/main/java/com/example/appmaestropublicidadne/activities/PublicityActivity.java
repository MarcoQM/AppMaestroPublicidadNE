package com.example.appmaestropublicidadne.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.appcompat.widget.SearchView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.appmaestropublicidadne.Publicity.PublicitiesRepository;
import com.example.appmaestropublicidadne.Publicity.Publicity;
import com.example.appmaestropublicidadne.R;
import com.example.appmaestropublicidadne.adapters.PublicityRecyclerViewAdapter;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

public class PublicityActivity extends AppCompatActivity implements androidx.appcompat.widget.SearchView.OnQueryTextListener{
    private static String TAG = "PublicityActivity";

    PublicityRecyclerViewAdapter publicityRecyclerViewAdapter;
    RecyclerView recyclerViewPublicities;
    SearchView searchViewPublicities;
    Button buttonAddPublicity;

    FloatingActionButton floatingActionButton;

    PublicitiesRepository publicitiesRepository;
    List<Publicity> publicityList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_publicity);


        publicityList = new ArrayList<>();
        searchViewPublicities = findViewById(R.id.searchViewPublicity);
        recyclerViewPublicities = findViewById(R.id.recyclerViewPublicities);
        buttonAddPublicity = findViewById(R.id.buttonRegisterPublicity);
        floatingActionButton = findViewById(R.id.floatingActionButtonPublicityHome);

        recyclerViewPublicities.setLayoutManager(new LinearLayoutManager(this));
        publicitiesRepository = PublicitiesRepository.get(this);

        //Mostar datos
        publicityRecyclerViewAdapter = new PublicityRecyclerViewAdapter(publicitiesRepository.getPublicities(), this);
        recyclerViewPublicities.setAdapter(publicityRecyclerViewAdapter);
        searchViewPublicities.setOnQueryTextListener(this);

        buttonAddPublicity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addPublicity();
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

    public void addPublicity() {
        Intent intent = new Intent(this, AddPublicityActivity.class);
        startActivity(intent);
    }

    @Override
    public boolean onQueryTextSubmit(String s) {
        return false;
    }

    @Override
    public boolean onQueryTextChange(String s) {
        publicityRecyclerViewAdapter.publicityFilter(s);
        return false;
    }
}