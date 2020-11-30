package com.domain;

import com.domain.model.Account;
import com.domain.model.Client;
import com.domain.ports.AccountPort;
import com.domain.ports.ClientPort;

/*
 * THIS IS NOT EXPECTED TO BECOME A SPRING-BOOT SERVICE
 */
public class ClientBankDomainService {

	private static final String CLIENT_NOT_FOUND_EXECPTION = "Client not found.";
	private static final String ACCOUNT_NOT_FOUND_EXECPTION = "Account not found.";

	private ClientPort clientPort;

	private AccountPort accountPort;

	
	public ClientBankDomainService(ClientPort clientPort, AccountPort accountPort) {
		this.clientPort = clientPort;
		this.accountPort = accountPort;
	}

	public Account getAccountInformation(String clientName, String accountCode) {
		Client client = clientPort.load(clientName).orElseThrow(
				() -> new IllegalArgumentException(CLIENT_NOT_FOUND_EXECPTION));
		return client.getAccountByCode(accountCode).orElseThrow(
				() -> new IllegalArgumentException(ACCOUNT_NOT_FOUND_EXECPTION));
	}
	
	public Account depositAmmount(String clientName, String accountCode, double amount) {
		Account account = getAccountInformation(clientName, accountCode);
		account.deposit(amount);
		accountPort.update(accountCode, account.getBalance());
		return account;
	}
	
	public Account withdrawAmmount(String clientName, String accountCode, double amount) {
		Account account = getAccountInformation(clientName, accountCode);
		account.withdraw(amount);
		accountPort.update(accountCode, account.getBalance());
		return account;
	}
	
}