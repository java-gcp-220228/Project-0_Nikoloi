package com.revature.dao;

import com.revature.models.BankAccount;
import com.revature.utility.ConnectionUtility;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class BankAccountDao {


    public List<BankAccount> getAllBankAccountsbyClientId(int bankId) throws SQLException {


        List<BankAccount> accounts = new ArrayList<>();
        try(Connection conn = ConnectionUtility.getConnection();){


            String qeury = "select * from bank_account \n" +
                    "where client_id = ?";

            PreparedStatement preparedStatement = conn.prepareStatement(qeury);

            preparedStatement.setInt(1, bankId);
            ResultSet resultSet = preparedStatement.executeQuery();

            while(resultSet.next()){

                int bank_idI= resultSet.getInt("bank_id");
                int client_id= resultSet.getInt("client_id");
                String bank_name = resultSet.getString("bank_name");
                String username = resultSet.getString("username");
                String password = resultSet.getString("password");
                float money = resultSet.getFloat("money");



                accounts.add(new BankAccount(bank_idI, client_id, bank_name, username,password, money));
            }
            return accounts;
        }
    }

    public List<BankAccount> getAllBankAccountsbyClientId(int clientId, int betweenV1, int betweenV2) throws SQLException {


        List<BankAccount> accounts = new ArrayList<>();
        try(Connection conn = ConnectionUtility.getConnection();){


            String qeury = "select * \n" +
                    "from bank_account \n" +
                    "WHERE money BETWEEN ? AND ? and client_id = ?;\n";

            PreparedStatement preparedStatement = conn.prepareStatement(qeury);

            preparedStatement.setInt(1, betweenV1);
            preparedStatement.setInt(2, betweenV2);
            preparedStatement.setInt(3, clientId);

            ResultSet resultSet = preparedStatement.executeQuery();

            while(resultSet.next()){

                int bank_idI= resultSet.getInt("bank_id");
                int client_id= resultSet.getInt("client_id");
                String bank_name = resultSet.getString("bank_name");
                String username = resultSet.getString("username");
                String password = resultSet.getString("password");
                float money = resultSet.getFloat("money");



                accounts.add(new BankAccount(bank_idI, client_id, bank_name, username,password, money));
            }
            return accounts;
        }
    }


    public BankAccount addBankAccount(int clientId, BankAccount accounts) throws SQLException{

        try (Connection conn = ConnectionUtility.getConnection();){



            String query = "insert into bank_account (bank_id, client_id, bank_name, username, password , money) values (?, ?, ?, ?, ?, ?)";


            PreparedStatement pS = conn.prepareCall(query);

            pS.setInt(1, accounts.getBankId());
            pS.setInt(2, clientId);
            pS.setString(3, accounts.getBank_name());
            pS.setString(4, accounts.getUsername());
            pS.setString(5, accounts.getPassword());
            pS.setFloat(6, accounts.getMoney());

            int result = pS.executeUpdate();

            System.out.println(result + " rows affected");

            return new BankAccount(accounts.getBankId(), clientId, accounts.getBank_name(), accounts.getUsername(),
                    accounts.getPassword(), accounts.getMoney());


        }
    }



    public List<BankAccount> getAllBankAccountsbyClientIdAndBankId(int clientId, int bankId) throws SQLException {


        List<BankAccount> accounts = new ArrayList<>();
        try(Connection conn = ConnectionUtility.getConnection();){


            String qeury = "select * \n" +
                    "from bank_account\n" +
                    "where client_id = ? and bank_id = ?";

            PreparedStatement preparedStatement = conn.prepareStatement(qeury);

            preparedStatement.setInt(1, clientId);
            ResultSet resultSet = preparedStatement.executeQuery();

            while(resultSet.next()){


                int client_id= resultSet.getInt("client_id");
                String bank_name = resultSet.getString("bank_name");
                String username = resultSet.getString("username");
                String password = resultSet.getString("password");
                float money = resultSet.getFloat("money");


                System.out.println("Bank Names: " + resultSet.getString("bank_name") + " username: " +
                        resultSet.getString("username"));
                accounts.add(new BankAccount(bankId, client_id, bank_name, username,password, money));
            }
            return accounts;
        }
    }


    public BankAccount updateByBankIdandClientId(BankAccount bankAccount, int clientId, int bankId) throws SQLException{


        try(Connection conn = ConnectionUtility.getConnection()){
            String dRs = "UPDATE bank_account \n" +
                    "SET bank_name = ?, username = ?, password = ?, money = ?\n" +
                    "WHERE bank_id =? and client_id = ?";

            PreparedStatement pS = conn.prepareStatement(dRs);

            pS.setInt(5, bankId);
            pS.setInt(6, clientId);
            pS.setString(1, bankAccount.getBank_name());
            pS.setString(2, bankAccount.getUsername());
            pS.setString(3, bankAccount.getPassword());
            pS.setFloat(4, bankAccount.getMoney());

            int result = pS.executeUpdate();

            System.out.println(result + " rows affected");

            return new BankAccount();
        }

    }



    public boolean deleteBankAccountbyclientIdAndBankAccountId(int client_id, int bank_id) throws SQLException {
        try (Connection con = ConnectionUtility.getConnection()) {
            String sql = "delete \n" +
                    "from bank_account \n" +
                    "where client_id = ? and bank_id = ?";

            PreparedStatement pS = con.prepareStatement(sql);

            pS.setInt(1, client_id);
            pS.setInt(2, bank_id);

            int resultSet = pS.executeUpdate();

            if (resultSet == 1) {
                System.out.println(resultSet + " rows affected");
                return true;
            }
        }

        return false;
    }


}

