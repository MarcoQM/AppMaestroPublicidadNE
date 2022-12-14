package com.example.appmaestropublicidadne.zone;

import android.content.Context;

import androidx.room.Room;

import com.example.appmaestropublicidadne.AppDatabase;

import java.util.List;

public class ZonesRepository {

    private static ZonesRepository zonesRepository;

    private ZoneDao zoneDao;

    private ZonesRepository(Context context) {
        Context appContext = context.getApplicationContext();
        AppDatabase zoneDatabase = Room.databaseBuilder(appContext, AppDatabase.class, "zones")
                                        .allowMainThreadQueries().build();

        zoneDao = zoneDatabase.zoneDao();
    }

    //Singleton
    public static ZonesRepository get(Context context) {
        if(zonesRepository == null) {
            zonesRepository = new ZonesRepository(context);
        }
        return zonesRepository;
    }

    public List<Zone> getZones() {
        return zoneDao.getZones();
    }

    public Zone getZone(Integer id) {
        return  zoneDao.getZone(id);
    }

    public void addZone(Zone zone) {
        zoneDao.addZone(zone);
    }

    public void updateZone(Zone zone) {
        zoneDao.updateZone(zone);
    }

    public void deleteZone(Zone zone) {
        zoneDao.deleteZone(zone);
    }

    public List<ZoneWithPublicities> getZoneWithPublicities() {
        return zoneDao.getZoneWithPublicities();
    }
}
