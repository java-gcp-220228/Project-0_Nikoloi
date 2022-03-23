package com.revature.models;

public class BankAccount {


    private int bankId;
    private int clientId;
    private String bank_name;
    private String username;
    private String password;
    private float money;

    public BankAccount(){}

    public BankAccount(int bankId, int clientId, String bank_name, String username, String password, float money) {
        this.bankId = bankId;
        this.clientId = clientId;
        this.bank_name = bank_name;
        this.username = username;
        this.password = password;
        this.money = money;
    }

    public int getBankId() {
        return bankId;
    }

    public void setBankId(int bankId) {
        this.bankId = bankId;
    }

    public int getClientId() {
        return clientId;
    }

    public void setClientId(int clientId) {
        this.clientId = clientId;
    }

    public String getBank_name() {
        return bank_name;
    }

    public void setBank_name(String bank_name) {
        this.bank_name = bank_name;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public float getMoney() {
        return money;
    }

    public void setMoney(float money) {
        this.money = money;
    }
}
