package com.revature.services;

import com.revature.dao.BankAccountDao;
import com.revature.exception.ExpectedExceptions;
import com.revature.models.BankAccount;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


import java.sql.SQLException;
import java.util.List;

public class BankAccountServices {

    private static Logger logger = LoggerFactory.getLogger(BankAccountServices.class);

    private BankAccountDao bankAccountDao;

    public BankAccountServices(){
        this.bankAccountDao = new BankAccountDao();
    };

    public BankAccountServices(BankAccountDao bankAccountDao){
        this.bankAccountDao = bankAccountDao;
    }

    public List<BankAccount> getAllBankAccounts(String id) throws SQLException, ExpectedExceptions {

        try {
            int bankId = Integer.parseInt(id);
            List<BankAccount> bankAccount = bankAccountDao.getAllBankAccountsbyClientId(bankId);

            if (bankAccount == null) {
                throw new ExpectedExceptions("Client with id " + bankId + " was not found");
            }
            return bankAccount;
        }catch (NumberFormatException e) {
            throw new IllegalArgumentException("Id provided for Bank Account must be a valid int");
        }
    }

    public List<BankAccount> getAllBankAccountsBewteentwoValues(String id, String betweenVal1String, String betweenVal2String) throws SQLException, ExpectedExceptions {

        try {
            int clientId = Integer.parseInt(id);
            int betweenVal1 = Integer.parseInt(betweenVal1String);
            int betweenVal2 = Integer.parseInt(betweenVal2String);
            List<BankAccount> bankAccount = bankAccountDao.getAllBankAccountsbyClientId(clientId,betweenVal1,betweenVal2);

            if (bankAccount == null) {
                throw new ExpectedExceptions("Bank Account with id " + clientId + " was not found");
            }
            return bankAccount;
        }catch (NumberFormatException e) {
            throw new IllegalArgumentException("Id provided for Bank Account must be a valid int");
        }
    }


    public  BankAccount addBankAccountService(String id, BankAccount bank) throws SQLException {
        try {
            int clientId = Integer.parseInt(id);
            BankAccount addBankAcc = bankAccountDao.addBankAccount(clientId, bank);

            return addBankAcc;
        }
    catch (NumberFormatException e) {
        throw new IllegalArgumentException("Id provided for Bank Account must be a valid int");
    }
    }

    public List<BankAccount> getAllBankAccountsbyBankandClientId(String id, String clId2) throws SQLException, ExpectedExceptions {

        try {
            int bankId = Integer.parseInt(id);
            int clientId = Integer.parseInt(clId2);
            List<BankAccount> bankAccount = bankAccountDao.getAllBankAccountsbyClientIdAndBankId(bankId,clientId);

            if (bankAccount == null) {
                throw new ExpectedExceptions("Bank Account with id " + bankId + " was not found");
            }
            return bankAccount;
        }catch (NumberFormatException e) {
            throw new IllegalArgumentException("Id provided for client must be a valid int");
        }
    }


    public BankAccount updateBankByClientAndBank(String clientIdString, String bankIdString, BankAccount bankAccount) throws SQLException, ExpectedExceptions {

        try {
            int clientId = Integer.parseInt(clientIdString);
            int bankId = Integer.parseInt(bankIdString);

            if (bankAccountDao.getAllBankAccountsbyClientId(clientId) == null) {
                throw new ExpectedExceptions("User is trying to edit an Bank Account that does not exist. Client with id " + clientId
                        + " was not found");
            }


            bankAccount.setBankId(bankId);
            bankAccount.setClientId(clientId);
            BankAccount updateBankAccount = bankAccountDao.updateByBankIdandClientId(bankAccount,clientId,bankId);

            return updateBankAccount;
        } catch(NumberFormatException e) {
            throw new IllegalArgumentException("Id provided for bank Account must be a valid int");
        }
    }



    public boolean deleteBankAccountbyClientIdAndBankId(String clientidString, String bankIdString) throws SQLException, ExpectedExceptions {

        try {
            int clientId = Integer.parseInt(clientidString);
            int bankId = Integer.parseInt(bankIdString);

            boolean bankBool = bankAccountDao.deleteBankAccountbyclientIdAndBankAccountId(clientId,bankId);

            if(bankBool == false){
                throw new ExpectedExceptions("Bank account with id " + clientId + " was not found");
            }
            return bankBool;
        }
        catch (NumberFormatException e){
            throw new IllegalArgumentException("Id provided for bank Account must be a valid int");
        }
    }




}
