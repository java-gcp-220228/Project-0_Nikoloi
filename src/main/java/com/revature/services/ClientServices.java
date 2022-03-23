package com.revature.services;

import com.revature.dao.ClientDao;
import com.revature.exception.ExpectedExceptions;
import com.revature.models.Clients;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.SQLException;
import java.util.List;

public class ClientServices {

    private static Logger logger = LoggerFactory.getLogger(ClientServices.class);

    private ClientDao clientDao;

    public ClientServices(ClientDao clientDao) {
        this.clientDao = clientDao;
    }

    public ClientServices(){

        this.clientDao = new ClientDao();
    }



    public List<Clients> getAllClients() throws SQLException {
        return this.clientDao.getAllClients();
    }




    public Clients getClientsById(String id) throws SQLException, ExpectedExceptions {
        try {
            int clientid = Integer.parseInt(id);

            Clients clients = clientDao.getClientById(clientid);

            if (clients == null) {
                throw new ExpectedExceptions("Client with id " + clientid+ " was not found");
            }

            return clients;

        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("Id provided for client must be a valid int");
        }
    }

    public boolean deleteClientById(String id) throws SQLException, ExpectedExceptions {

        try {
            int clientId = Integer.parseInt(id);

            boolean clientsBool = clientDao.deleteClientbyId(clientId);

            if(clientsBool == false){
                throw new ExpectedExceptions("Client with id " + clientId + " was not found");
            }
            return clientsBool;
        }
        catch (NumberFormatException e){
            throw new IllegalArgumentException("Id provided for client must be a valid int");
        }


    }


    public Clients updateClientService(String id, Clients clients) throws SQLException, ExpectedExceptions {

        try {
            int clientId = Integer.parseInt(id);

            if (clientDao.getClientById(clientId) == null) {
                throw new ExpectedExceptions("User is trying to edit a Client that does not exist. Client with id " + clientId
                        + " was not found");
            }

           validateClientInformation(clients);

            clients.setClientid(clientId);
            Clients updateClient = clientDao.updateById(clients);

            return updateClient;
        } catch(NumberFormatException e) {
            throw new IllegalArgumentException("Id provided for Client must be a valid int");
        }
    }


    //TODO: Add clients Services
    public Clients addClients(Clients clients) throws SQLException {

        validateClientInformation(clients);

        Clients addClients = clientDao.addClient(clients);
        return addClients;
    }


    public void validateClientInformation(Clients clients) {
        clients.setFirstName(clients.getFirstName().trim());
        clients.setLastName(clients.getLastName().trim());

        if (!clients.getFirstName().matches("[a-zA-Z]+")) {
            throw new IllegalArgumentException("First name must only have alphabetical characters. First name input was " + clients.getFirstName());
        }

        if (!clients.getLastName().matches("[a-zA-Z]+")) {
            throw new IllegalArgumentException("Last name must only have alphabetical characters. Last name input was " + clients.getLastName());
        }

        if (clients.getAge() < 0) {
            throw new IllegalArgumentException("Adding a client with age < 0 is not valid. Age provided was " + clients.getAge());
        }
    }





}
