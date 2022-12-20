package com.example.appmaestropublicidadne;


import androidx.room.Database;
import androidx.room.RoomDatabase;

import com.example.appmaestropublicidadne.Publicity.Publicity;
import com.example.appmaestropublicidadne.Publicity.PublicityDao;
import com.example.appmaestropublicidadne.client.Client;
import com.example.appmaestropublicidadne.client.ClientDao;
import com.example.appmaestropublicidadne.zone.Zone;
import com.example.appmaestropublicidadne.zone.ZoneDao;

@Database(entities = {Client.class, Zone.class, Publicity.class}, version = 1, exportSchema = false)
public abstract class AppDatabase extends RoomDatabase{
    public abstract ZoneDao zoneDao();
    public abstract PublicityDao publicityDao();
    public abstract ClientDao clientDao();
}
