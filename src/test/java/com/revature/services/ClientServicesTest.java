package com.revature.services;

import com.revature.dao.ClientDao;
import com.revature.exception.ExpectedExceptions;
import com.revature.models.Clients;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class ClientServicesTest {

    private ClientDao mockDao = mock(ClientDao.class);


    @Test
    void getAllClients() throws SQLException {

        List<Clients> mockClients = new ArrayList<>();
        mockClients.add(new Clients(1, "Johnny", "Bravo", 25));
        mockClients.add(new Clients(2, "Jhus", "Control", 21));

        //When the mock layer is called don't return it just run a mock of it
        when(mockDao.getAllClients()).thenReturn(mockClients);


        //This is for creating the service layer
        ClientServices clientServices = new ClientServices(mockDao);

        List<Clients> real = clientServices.getAllClients();

        List<Clients> expected = new ArrayList<>(mockClients);
        Assertions.assertEquals(expected, real);






    }



    //Negative test it is supposed to an a clientException
    @Test
    public void clientDoesNotExistException() throws SQLException, ExpectedExceptions {

        ClientServices clientServices = new ClientServices(mockDao);

        Assertions.assertThrows(ExpectedExceptions.class, () ->{

            clientServices.getClientsById("1");
        });

    }

    //negative test it is supposed to return an error
    @Test
    public void clientiDTestReturnsAnIllegalArgument() throws SQLException, ExpectedExceptions {

        ClientServices clientServices = new ClientServices(mockDao);

        try{

            clientServices.getClientsById("yeah");
        }
        catch(IllegalArgumentException e) {
            String expectedMessage = "Id provided for client must be a valid int";
            String actualMessage = e.getMessage();

            Assertions.assertEquals(expectedMessage, actualMessage);
        }
    }

    //Positive test clientservice is supposed to return the same client
    @Test
    public void clientReturnsTheSameObject()throws SQLException, ExpectedExceptions {



        //The value
        Clients clients = new Clients(1, "Johnny", "Kennedy", 25);

       when(mockDao.getClientById(1)).thenReturn(clients);

       ClientServices clientServices = new ClientServices(mockDao);

       Clients actual = clientServices.getClientsById("1");

       //See if both objects are the same
        assertTrue(actual == clients);


    }



    @Test
    public void deleteClientByIdException() throws SQLException, ExpectedExceptions {


        ClientServices clientServices = new ClientServices(mockDao);


        try{
            clientServices.deleteClientById("nerfthis");
        }
        catch (IllegalArgumentException e){
            String expectedMessage = "Id provided for client must be a valid int";
            String actualMessage = e.getMessage();

            Assertions.assertEquals(expectedMessage, actualMessage);
        }
    }







}