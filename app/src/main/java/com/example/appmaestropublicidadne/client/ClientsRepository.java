package com.example.appmaestropublicidadne.client;

import android.content.Context;
import androidx.room.Room;

import com.example.appmaestropublicidadne.AppDatabase;

import java.util.List;

public class ClientsRepository {

    private static ClientsRepository clientsRepository;

    private ClientDao clientDao;

    private ClientsRepository(Context context) {
        Context appContext = context.getApplicationContext();
        AppDatabase clientDatabase = Room.databaseBuilder(appContext, AppDatabase.class, "clients")
                                        .allowMainThreadQueries().build();

        clientDao = clientDatabase.clientDao();
    }

    //Singleton
    public static ClientsRepository get(Context context) {
        if(clientsRepository == null) {
            clientsRepository = new ClientsRepository(context);
        }
        return clientsRepository;
    }

    public List<Client> getClients() {
        return clientDao.getClients();
    }

    public Client getClient(String id) {
        return  clientDao.getClient(id);
    }

    public void addClient(Client client) {
        clientDao.addClient(client);
    }

    public void updateClient(Client client) {
        clientDao.updateClient(client);
    }

    public void deleteClient(Client client) {
        clientDao.deleteClient(client);
    }

    public List<ClientWithPublicities> getClientWithPublicities() {
        return clientDao.getClientWithPublicities();
    }
}
