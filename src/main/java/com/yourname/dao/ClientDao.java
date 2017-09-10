package com.yourname.dao;

import com.yourname.entity.Client;

import java.util.Collection;

public interface ClientDao {
    Collection<Client> getAllClients();

    Client getClientById(int id);

    void removeClientById(int id);

    void updateClient(Client client);

    void insertClient(Client client);
}
