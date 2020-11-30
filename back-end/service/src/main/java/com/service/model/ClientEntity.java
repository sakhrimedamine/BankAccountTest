package com.service.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.domain.model.Account;
import com.domain.model.Client;

@Entity
@Table(name = "CLIENT")
public class ClientEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String name;

    @OneToMany(mappedBy = "owner")
    private final List<AccountEntity> accounts = new ArrayList<>();

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<AccountEntity> getAccounts() {
        return accounts;
    }
    
	public Client toClient() {
		Client client = new Client(name);
		for(AccountEntity accountEntity : accounts) {
			Account account = new Account(accountEntity.getCode());
			account.setBalance(accountEntity.getBalance());
			client.getAccounts().add(account);
		}
		return client;
	}
}
