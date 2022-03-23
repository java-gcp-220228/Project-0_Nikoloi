package com.revature.models;

public class Clients {

    private int clientid;
    private String firstName;
    private String lastName;
    private int age;


    public Clients(){};

    public Clients(int clientid, String firstName, String lastName, int age) {
        this.clientid = clientid;
        this.firstName = firstName;
        this.lastName = lastName;
        this.age = age;
    }

    public int getClientid() {
        return clientid;
    }

    public void setClientid(int clientid) {
        this.clientid = clientid;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }
}
