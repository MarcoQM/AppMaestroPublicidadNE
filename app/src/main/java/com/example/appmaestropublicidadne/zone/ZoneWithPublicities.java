package com.example.appmaestropublicidadne.zone;

import androidx.room.Embedded;
import androidx.room.Relation;

import com.example.appmaestropublicidadne.Publicity.Publicity;

import java.util.List;

public class ZoneWithPublicities {
    @Embedded
    public Zone zone;

    @Relation(parentColumn = "id", entityColumn = "zone_id")
    public List<Publicity> publicities;

    @Override
    public String toString() {
        return "ZoneWithPublicities{" +
                "zone=" + zone +
                ", publicities=" + publicities +
                '}';
    }
}
