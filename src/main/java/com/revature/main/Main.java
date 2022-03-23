package com.revature.main;

import com.revature.controller.BankAccountController;
import com.revature.controller.ClientController;
import com.revature.controller.Controller;
import com.revature.controller.ExceptionController;
import io.javalin.Javalin;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;



public class Main {

    public static Logger logger = LoggerFactory.getLogger(Main.class);
    public static void main(String[] args) {



        //TODO: Map endpoints


        Javalin app = Javalin.create();


        app.before((ctx) -> {
            logger.info(ctx.method() + " request received for " + ctx.path());
        });

        mapControllers(app, new ClientController(),new BankAccountController(),new ExceptionController());

        app.start();


    }

    public static void mapControllers(Javalin app, Controller... controllers) {
        for (Controller c : controllers) {
            c.mapEndpoints(app);
        }
    }
}
