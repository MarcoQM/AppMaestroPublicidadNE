package com.example.appmaestropublicidadne.zone;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Transaction;
import androidx.room.Update;

import java.util.List;

@Dao
public interface ZoneDao {

    @Query("SELECT * FROM zones")
    List<Zone> getZones();

    @Query("SELECT * FROM zones WHERE id LIKE :idZone")
    Zone getZone(String idZone);

    @Insert
    void addZone(Zone zone);

    @Delete
    void deleteZone(Zone zone);

    @Update
    void updateZone(Zone zone);

    @Transaction
    @Query("SELECT * FROM zones")
    List<ZoneWithPublicities> getZoneWithPublicities();

}
