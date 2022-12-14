package com.example.appmaestropublicidadne.client;

import androidx.room.Embedded;
import androidx.room.Relation;

import com.example.appmaestropublicidadne.Publicity.Publicity;

import java.util.List;

public class ClientWithPublicities {

    @Embedded
    public Client client;

    @Relation(parentColumn = "id", entityColumn = "client_id")
    public List<Publicity> publicities;

    @Override
    public String toString() {
        return "ClientWithPublicities{" +
                "client=" + client +
                ", publicities=" + publicities +
                '}';
    }
}
