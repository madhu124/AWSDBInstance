package com.yourname.service;

import com.yourname.dao.ClientDao;
import com.yourname.entity.Client;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
public class ClientService {

    @Autowired
    @Qualifier("mysql")
    private ClientDao clientDao;

    public Collection<Client> getAllClients(){

        return this.clientDao.getAllClients();
    }

    public Client getClientById(int id){
        return this.clientDao.getClientById(id);
    }


    public void removeClientById(int id) {
        this.clientDao.removeClientById(id);
    }

    public void updateClient(Client client){
        this.clientDao.updateClient(client);
    }

    public void insertClient(Client client) {
        this.clientDao.insertClient(client);
    }
}
