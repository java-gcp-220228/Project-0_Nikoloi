package com.revature.controller;

import com.revature.models.BankAccount;
import com.revature.models.Clients;
import com.revature.services.BankAccountServices;
import com.revature.services.ClientServices;
import io.javalin.Javalin;
import io.javalin.http.Handler;

import java.util.List;

public class ClientController implements Controller {

    private ClientServices clientServices;


    public ClientController() {
        this.clientServices = new ClientServices();
    }

    // This lambda will implicitly have "throws Exception" based on the functional interface
    // This is something to be aware of, because you might actually want to handle some exceptions
    private Handler getAllClients = (ctx) -> {
        List<Clients> clients = clientServices.getAllClients();

        ctx.json(clients);

    };

    private Handler getClientsbyId = (ctx) -> {
        String id = ctx.pathParam("clientID");

        Clients clients = clientServices.getClientsById(id);

        ctx.json(clients);
    };

    private Handler updatebyId = (ctx) ->{

        var clientsupdate = ctx.bodyAsClass(Clients.class);
        Clients updateClient = clientServices.updateClientService(ctx.pathParam("clientID"), clientsupdate);

        ctx.status(200);
        ctx.json(updateClient);
    };

    private Handler deleteClient = (ctx) -> {

        String id = ctx.pathParam("clientID");

        var clients = clientServices.deleteClientById(id);

        ctx.json(clients);

    };

    private Handler addClients = (ctx) -> {
        var addClients = ctx.bodyAsClass(Clients.class);

        Clients client = clientServices.addClients(addClients);

        ctx.status(201);
        ctx.json(client);
    };







    @Override
    public void mapEndpoints(Javalin app) {
        app.get("/clients", getAllClients);
        app.get("/clients/{clientID}", getClientsbyId);
        app.put("/clients/{clientID}", updatebyId);
        app.post("/clients", addClients);
        app.delete("/clients/{clientID}", deleteClient);

    }


}
