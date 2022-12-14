package com.example.appmaestropublicidadne.Publicity;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;

import com.example.appmaestropublicidadne.client.Client;
import com.example.appmaestropublicidadne.zone.Zone;

@Entity(tableName = "publicities")
public class Publicity {

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    private Integer id;

    @ColumnInfo(name = "name")
    @NonNull
    private String name;

    @ColumnInfo(name = "zone_id")
    @NonNull
    public Integer zoneId;

    @ColumnInfo(name = "client_id")
    @NonNull
    public Integer clientId;

    @ColumnInfo(name = "registration_status")
    @NonNull
    private String registrationStatus = "A";

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @NonNull
    public String getName() {
        return name;
    }

    public void setName(@NonNull String name) {
        this.name = name;
    }

    @NonNull
    public Integer getZoneId() {
        return zoneId;
    }

    public void setZoneId(@NonNull Integer zoneId) {
        this.zoneId = zoneId;
    }

    @NonNull
    public Integer getClientId() {
        return clientId;
    }

    public void setClientId(@NonNull Integer clientId) {
        this.clientId = clientId;
    }

    @NonNull
    public String getRegistrationStatus() {
        return registrationStatus;
    }

    public void setRegistrationStatus(@NonNull String registrationStatus) {
        this.registrationStatus = registrationStatus;
    }

    @Override
    public String toString() {
        return "Publicity{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", zoneId=" + zoneId +
                ", clientId=" + clientId +
                ", registrationStatus='" + registrationStatus + '\'' +
                '}';
    }
}
