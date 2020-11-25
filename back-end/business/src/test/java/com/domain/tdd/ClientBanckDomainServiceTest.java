package com.domain.tdd;

import org.assertj.core.api.Assertions;
import org.junit.Test;

import com.domain.ClientBankDomainService;
import com.domain.model.Account;
import com.domain.model.Client;

public class ClientBanckDomainServiceTest {

	private ClientBankDomainService clientBankDomainService = new ClientBankDomainService();
	
	@Test(expected = IllegalArgumentException.class)
    public void whenInValidAccountCodeThenThrowIllegalArgumentException() {
    	
    	// Given
    	String accountCode = "14451";
    	double accountBalance = 0;
    	Account account = new Account(accountCode);
    	account.setBalance(accountBalance);
    	
    	String clientName = "sakhri.mohamed";
    	Client client = new Client(clientName);
    	client.getAccounts().add(account);
    	// When
    	clientBankDomainService.getAccountInformation(client, "14452");
    }
    
    @Test
    public void whenValidAccountCodeThenReturnCorrespondingAccount() {
    	
    	// Given
    	String accountCode = "14451";
    	double accountBalance = 0;
    	Account account = new Account(accountCode);
    	account.setBalance(accountBalance);
    	
    	String clientName = "sakhri.mohamed";
    	Client client = new Client(clientName);
    	client.getAccounts().add(account);
    	// When
    	Account retreivedAccount = clientBankDomainService.getAccountInformation(client, "14451");
    	// Then
        Assertions.assertThat(retreivedAccount.getCode()).isEqualTo(accountCode);
        Assertions.assertThat(retreivedAccount.getBalance()).isEqualTo(accountBalance);
    }
    
    @Test
    public void whenDepositAmmountToValidAccountThenUpdateBalance() {
    	
    	// Given
    	String accountCode = "14451";
    	double accountBalance = 0;
    	Account account = new Account(accountCode);
    	account.setBalance(accountBalance);
    	
    	String clientName = "sakhri.mohamed";
    	Client client = new Client(clientName);
    	client.getAccounts().add(account);
    	
    	double ammount = 2;
    	// When
    	Account retreivedAccount = clientBankDomainService.depositAmmount(client, "14451", ammount);
    	// Then
        Assertions.assertThat(retreivedAccount.getCode()).isEqualTo(accountCode);
        Assertions.assertThat(retreivedAccount.getBalance()).isEqualTo(accountBalance + ammount);
    }
    
    @Test
    public void whenWithdrawAmmountToValidAccountThenUpdateBalance() {
    	
    	// Given
    	String accountCode = "14451";
    	double accountBalance = 0;
    	Account account = new Account(accountCode);
    	account.setBalance(accountBalance);
    	
    	String clientName = "sakhri.mohamed";
    	Client client = new Client(clientName);
    	client.getAccounts().add(account);
    	
    	double ammount = 2;
    	// When
    	Account retreivedAccount = clientBankDomainService.withdrawAmmount(client, "14451", ammount);
    	// Then
        Assertions.assertThat(retreivedAccount.getCode()).isEqualTo(accountCode);
        Assertions.assertThat(retreivedAccount.getBalance()).isEqualTo(accountBalance - ammount);
    }
}
