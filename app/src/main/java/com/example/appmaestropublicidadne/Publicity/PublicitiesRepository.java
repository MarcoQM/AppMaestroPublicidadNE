package com.example.appmaestropublicidadne.Publicity;

import android.content.Context;

import androidx.room.Room;

import com.example.appmaestropublicidadne.AppDatabase;

import java.util.List;

public class PublicitiesRepository {

    private static PublicitiesRepository publicitiesRepository;

    private PublicityDao publicityDao;

    private PublicitiesRepository(Context context) {
        Context appContext = context.getApplicationContext();
        AppDatabase publicityDatabase = Room.databaseBuilder(appContext, AppDatabase.class, "publicites")
                                                    .allowMainThreadQueries().build();

        publicityDao = publicityDatabase.publicityDao();
    }

    //Singleton
    public static PublicitiesRepository get(Context context) {
        if(publicitiesRepository == null) {
            publicitiesRepository = new PublicitiesRepository(context);
        }
        return publicitiesRepository;
    }

    public List<Publicity> getPublicities() {
        return publicityDao.getPublicities();
    }

    public Publicity getPublicity(Integer id) {
        return publicityDao.getPublicity(id);
    }

    public void addPublicity(Publicity publicity) {
        publicityDao.addPublicity(publicity);
    }

    public void updatePublicity(Publicity publicity) {
        publicityDao.updatePublicity(publicity);
    }

    public void deletePublicity(Publicity publicity) {
        publicityDao.deletePublicity(publicity);
    }
}
