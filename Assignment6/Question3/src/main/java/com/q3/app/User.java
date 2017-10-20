package com.q3.app;

import java.util.ArrayList;

public class User {
    private String name;
    private String age;
    private String address;
    private String phoneNumber;
    private String bankAccountNumber;
    private String password;
    private double balance;
    private ArrayList<String> transactions;

    public User() {

    }

    public User(String name, String age, String address, String phone, String password, String bankAccountNumber, Double balance, ArrayList<String> transactions) {
        setName(name);
        setAge(age);
        setAddress(address);
        setPhoneNumber(phone);
        setPassword(password);
        setBankAccountNumber(bankAccountNumber);
        setBalance(balance);
        setTransactions(transactions);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getBankAccountNumber() {
        return bankAccountNumber;
    }

    public void setBankAccountNumber(String bankAccountNumber) {
        this.bankAccountNumber = bankAccountNumber;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public void addTransaction(String transaction) {
        getTransactions().add(transaction);
    }

    public ArrayList<String> getTransactions() {
        return transactions;
    }

    public void setTransactions(ArrayList<String> transactions) {
        this.transactions = transactions;
    }
}
