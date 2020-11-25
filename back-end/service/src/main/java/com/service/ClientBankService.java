package com.service;

import java.util.Optional;

import org.springframework.stereotype.Service;

import com.domain.ClientBankDomainService;
import com.domain.model.Account;
import com.domain.model.Client;
import com.service.model.AccountEntity;
import com.service.model.ClientEntity;
import com.service.repository.AccountRepository;
import com.service.repository.ClientRepository;

@Service
public class ClientBankService {

	private static final String CLIENT_NOT_FOUND_EXECPTION = "Client not found.";

    private ClientBankDomainService innerService;
    private ClientRepository clientRepository;
    private AccountRepository accountRepository;

    public ClientBankService(ClientRepository clientRepository, AccountRepository accountRepository) {
        this.innerService = new ClientBankDomainService();
        this.clientRepository = clientRepository;
        this.accountRepository = accountRepository;
    }
    
	public Account getAccountInformations(String clientName, String  accountCode) {
		ClientEntity clientEntity = getClientByName(clientName);
		return innerService.getAccountInformation(mapClientEntityToClient(clientEntity), accountCode);
	}
	
	public Account depositAmmount(String clientName, String  accountCode, double ammount) {
		ClientEntity clientEntity = getClientByName(clientName);
		Account account = innerService.depositAmmount(mapClientEntityToClient(clientEntity), accountCode, ammount);
		updateAccount(account);
		return account;
	}
	
	public Account withdrawAmmount(String clientName, String  accountCode, double ammount) {
		ClientEntity clientEntity = getClientByName(clientName);
		Account account =innerService.withdrawAmmount(mapClientEntityToClient(clientEntity), accountCode, ammount);
		updateAccount(account);
		return account;
	}
	
	private	 ClientEntity getClientByName(String clientName) {
		return Optional.ofNullable(clientRepository.findByName(clientName))
				.orElseThrow(() -> new IllegalArgumentException(CLIENT_NOT_FOUND_EXECPTION));
	}
	
	private void updateAccount(Account account) {
		AccountEntity accountEntity = findAccountByCode(account.getCode());
		accountEntity.setBalance(account.getBalance());
		accountRepository.save(accountEntity);
	}
	
	private	 AccountEntity findAccountByCode(String code) {
		return accountRepository.findByCode(code);
	}
	
	private Client mapClientEntityToClient(ClientEntity entity) {
		Client client = new Client(entity.getName());
		for(AccountEntity accountEntity : entity.getAccounts()) {
			Account account = new Account(accountEntity.getCode());
			account.setBalance(accountEntity.getBalance());
			client.getAccounts().add(account);
		}
		return client;
	}
}
