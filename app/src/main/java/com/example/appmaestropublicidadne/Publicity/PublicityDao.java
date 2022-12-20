package com.example.appmaestropublicidadne.Publicity;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface PublicityDao {

    @Query("SELECT * FROM publicities")
    List<Publicity> getPublicities();

    @Query("SELECT * FROM publicities WHERE id LIKE :idPublicity")
    Publicity getPublicity(String idPublicity);

    @Insert
    void addPublicity(Publicity publicity);

    @Delete
    void deletePublicity(Publicity publicity);

    @Update
    void updatePublicity(Publicity publicity);
}
