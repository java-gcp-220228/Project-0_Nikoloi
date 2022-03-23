package com.revature.services;

import com.revature.dao.BankAccountDao;
import com.revature.exception.ExpectedExceptions;
import com.revature.models.BankAccount;
import com.revature.models.Clients;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class BankAccountServicesTest {

    BankAccountDao mockDao = mock(BankAccountDao.class);




    @Test
    public void testIfInvalidBClientId() throws SQLException, ExpectedExceptions {

        BankAccountServices bankAccountServices = new BankAccountServices(mockDao);



        try{
            bankAccountServices.getAllBankAccounts("client_id");
        }
        catch (IllegalArgumentException e){
            String expectedMessage = "Id provided for client must be a valid int";
            String actualMessage = e.getMessage();

            assertEquals(expectedMessage, actualMessage);
        }

    }



    @Test
    public void testIfInvalidUpdateByClientIdAndBankId() throws SQLException, ExpectedExceptions {

        BankAccountServices bankAccountServices = new BankAccountServices(mockDao);


        BankAccount bank = new BankAccount();
        try{
            bankAccountServices.updateBankByClientAndBank("Ui","ds",bank);
        }
        catch (IllegalArgumentException e){
            String expectedMessage = "Id provided for client must be a valid int";
            String actualMessage = e.getMessage();

            assertEquals(expectedMessage, actualMessage);
        }

    }









}