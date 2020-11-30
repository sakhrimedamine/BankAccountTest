package com.domain.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class Client {

    private final String name;

    private final List<Account> accounts = new ArrayList<>();

    public Client(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public List<Account> getAccounts() {
        return accounts;
    }
    
    public Optional<Account> getAccountByCode(String accountCode) {
    	return accounts.stream().filter(a -> a.getCode().equals(accountCode)).findFirst();
    }

}
