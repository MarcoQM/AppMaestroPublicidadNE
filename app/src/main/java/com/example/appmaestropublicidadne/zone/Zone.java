package com.example.appmaestropublicidadne.zone;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "zones")
public class Zone {

    //@PrimaryKey(autoGenerate = true)
    @PrimaryKey
    @NonNull
    @ColumnInfo(name = "id")
    private String id;

    @ColumnInfo(name = "name")
    @NonNull
    private String name;

    @ColumnInfo(name = "registration_status")
    @NonNull
    private String registrationStatus = "A";

    public String getId() {
        return id;
    }

    public void setId(String id) {
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
    public String getRegistrationStatus() {
        return registrationStatus;
    }

    public void setRegistrationStatus(@NonNull String registrationStatus) {
        this.registrationStatus = registrationStatus;
    }

    @Override
    public String toString() {
        return "Zone{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", registrationStatus='" + registrationStatus + '\'' +
                '}';
    }
}