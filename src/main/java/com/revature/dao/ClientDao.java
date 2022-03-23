package com.revature.dao;

import com.revature.models.Clients;
import com.revature.utility.ConnectionUtility;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ClientDao {



    //TODO: Get all of the CLients
    public List<Clients> getAllClients() throws SQLException {


        List<Clients> clients = new ArrayList<>();
        try(Connection conn = ConnectionUtility.getConnection();){


            Statement statement = conn.createStatement();
            ResultSet resultSet = statement.executeQuery("select * from clients");

            while(resultSet.next()){

                int clientId = resultSet.getInt("client_id");
                String firstname = resultSet.getString("first_name");
                String lastname = resultSet.getString("last_name");
                int age = resultSet.getInt("age");


                clients.add(new Clients(clientId,firstname,lastname,age));
            }
            return clients;
        }
    }




    //TODO: Add clients for POSTING
    public Clients addClient(Clients clients) throws SQLException{

        try (Connection conn = ConnectionUtility.getConnection();){


            String query = "insert into clients  values (?, ?, ?, ?)";

            //Generated key is for serials Statement.generatedkey
            PreparedStatement pS = conn.prepareCall(query);

            pS.setInt(1, clients.getClientid());
            pS.setString(2, clients.getFirstName());
            pS.setString(3, clients.getLastName());
            pS.setInt(4, clients.getAge());

            int result = pS.executeUpdate();



            return new Clients(clients.getClientid(), clients.getFirstName(), clients.getLastName(), clients.getAge());



        }
    }


    //TODO: To get a client by their ID
    public Clients getClientById(int clientid) throws SQLException{

        try(Connection conn = ConnectionUtility.getConnection()){

            String query = "select client_id, first_name , last_name , age  from clients where client_id = ?";
            PreparedStatement pS = conn.prepareStatement(query);

            pS.setInt(1, clientid);

            ResultSet result = pS.executeQuery();


            //Note .getString takes the select strings like test_id, test_names, test_numbers it gets the data from the
            // resultset
            while (result.next()){

                int client_i= result.getInt("client_id");
                String firstname = result.getString("first_name");
                String lastname = result.getString("last_name");
                int age = result.getInt("age");


                return new Clients(client_i,firstname,lastname,age);
            }
        }

        return null;
    }







    //TODO: Update
    public Clients updateById(Clients clients) throws SQLException{


        try(Connection conn = ConnectionUtility.getConnection()){
            String dRs = "update clients \n" +
                    "set first_name = ?,last_name = ?,  age = ?\n" +
                    "where client_id  = ?";

            PreparedStatement pS = conn.prepareStatement(dRs);

            pS.setInt(4, clients.getClientid());
            pS.setString(1, clients.getFirstName());
            pS.setString(2, clients.getLastName());
            pS.setInt(3, clients.getAge());

            int result = pS.executeUpdate();

            System.out.println(result + " rows affected");

            return new Clients();
        }

    }



    //TODO: Delete CLient
    public boolean deleteClientbyId(int client_id) throws SQLException {
        try (Connection con = ConnectionUtility.getConnection()) {
            String sql = "delete from clients  where client_id  = ?";

            PreparedStatement pS = con.prepareStatement(sql);

            pS.setInt(1, client_id);

            int resultSet = pS.executeUpdate();

            if (resultSet == 1) {
                System.out.println(resultSet + " rows affected");
                return true;
            }
        }

        return false;
    }



}
