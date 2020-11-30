package com.domain.model;

public class Account {

    private final String code;

    private double balance = 0;

    public Account(String code) {
        this.code = code;
    }

    public String getCode() {
        return code;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }
    
    public void withdraw(double amount) {
    	balance = balance - amount;
    }
    
    public void deposit(double amount) {
    	balance = balance + amount;
    }


}
