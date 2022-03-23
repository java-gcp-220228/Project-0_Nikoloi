package com.revature.controller;

import com.revature.models.BankAccount;
import com.revature.services.BankAccountServices;
import io.javalin.Javalin;
import io.javalin.http.Handler;

import java.util.List;

public class BankAccountController implements Controller{
    private BankAccountServices bankAccountServices;

    public BankAccountController() {
        this.bankAccountServices = new BankAccountServices();
    }

    private Handler seeAllBankAccountsById = (ctx) -> {

        String clientID = ctx.pathParam("clientID");
        String amountLessThan = ctx.queryParam("amountLessThan");
        String amountGreaterThan = ctx.queryParam("amountGreaterThan");

        if (amountLessThan == null && amountGreaterThan == null) {

            List<BankAccount> bankAccounts = bankAccountServices.getAllBankAccounts(clientID);
            ctx.json(bankAccounts);

        }
        else {
            List<BankAccount> bankAccounts = bankAccountServices.getAllBankAccountsBewteentwoValues(clientID,amountLessThan,amountGreaterThan);
            ctx.json(bankAccounts);

        }


    };


    private Handler seeAllBankAccountsByIdandBankId = (ctx) -> {

        String clientid = ctx.pathParam("clientID");
        String bankid = ctx.pathParam("bankID");
        List<BankAccount> bankAccounts = bankAccountServices.getAllBankAccountsbyBankandClientId(clientid, bankid);

        ctx.json(bankAccounts);
    };
    private Handler addBankAccountById= (ctx) -> {
        var addBankAccounts = ctx.bodyAsClass(BankAccount.class);

        String id = ctx.pathParam("clientID");
        BankAccount bank = bankAccountServices.addBankAccountService(id, addBankAccounts);

        ctx.status(201);
        ctx.json(bank);
    };

    private Handler updatebankByClientIdAndBankId = (ctx) ->{

        var bankUpdate = ctx.bodyAsClass(BankAccount.class);
        BankAccount uppdateBank = bankAccountServices.updateBankByClientAndBank(ctx.pathParam("clientID"),
                ctx.pathParam("bankID"),bankUpdate);

        ctx.status(200);
        ctx.json(uppdateBank);
    };

    private Handler deleteBankbyClientandBankId = (ctx) -> {

        String clientid = ctx.pathParam("clientID");
        String bankId = ctx.pathParam("bankID");


        var banks = bankAccountServices.deleteBankAccountbyClientIdAndBankId(clientid, bankId);

        ctx.json(banks);

    };




    @Override
    public void mapEndpoints(Javalin app) {
        app.get("/clients/{clientID}/bankaccounts", seeAllBankAccountsById);
        app.post("/clients/{clientID}/bankaccounts", addBankAccountById);
        app.get("/clients/{clientID}/bankaccounts/{bankID}", seeAllBankAccountsByIdandBankId);
        app.put("/clients/{clientID}/bankaccounts/{bankID}", updatebankByClientIdAndBankId);
        app.delete("/clients/{clientID}/bankaccounts/{bankID}", deleteBankbyClientandBankId);


    }
}
