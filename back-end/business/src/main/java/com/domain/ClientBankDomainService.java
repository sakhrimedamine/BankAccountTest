package com.domain;

import java.util.List;
import java.util.Optional;

import com.domain.model.Account;
import com.domain.model.Client;

/*
 * THIS IS NOT EXPECTED TO BECOME A SPRING-BOOT SERVICE
 */
public class ClientBankDomainService {

	private static final String ACCOUNT_NOT_FOUND_EXECPTION = "Account not found.";

	
	public Account getAccountInformation(Client client , String code) {
		return getAccountByCode(client.getAccounts(), code).orElseThrow(
				() -> new IllegalArgumentException(ACCOUNT_NOT_FOUND_EXECPTION));
	}
	
	public Account depositAmmount(Client client , String code, double ammount) {
		Account account = getAccountInformation(client, code);
		account.setBalance(account.getBalance() + ammount);
		return account;
	}
	
	public Account withdrawAmmount(Client client , String code, double ammount) {
		Account account = getAccountInformation(client, code);
		account.setBalance(account.getBalance() - ammount);
		return account;
	}
	
	private Optional<Account> getAccountByCode(List<Account> accounts, String code) {
		return accounts.stream().filter(a -> a.getCode().equals(code)).findFirst();
	}
}