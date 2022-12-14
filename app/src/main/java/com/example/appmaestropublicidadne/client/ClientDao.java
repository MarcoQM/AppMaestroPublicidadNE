package com.example.appmaestropublicidadne.client;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Transaction;
import androidx.room.Update;

import java.util.List;

@Dao
public interface ClientDao {

    @Query("SELECT * FROM clients")
    List<Client> getClients();

    @Query("SELECT * FROM clients WHERE id LIKE :idClient")
    Client getClient(Integer idClient);

    @Insert
    void addClient(Client client);

    @Delete
    void deleteClient(Client client);

    @Update
    void updateClient(Client client);

    @Transaction
    @Query("SELECT * FROM clients")
    List<ClientWithPublicities> getClientWithPublicities();


}
