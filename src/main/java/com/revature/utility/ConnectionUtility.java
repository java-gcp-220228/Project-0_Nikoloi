package com.revature.utility;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionUtility {


    public static Connection getConnection() throws SQLException {


        //TODO: Database login
        String url = System.getenv("db_url");
        String username = System.getenv("db_username");
        String password = System.getenv("db_password");

        //TODO: Connect Database
        Connection conn = DriverManager.getConnection(url, username, password);


        return conn;
    }
}
