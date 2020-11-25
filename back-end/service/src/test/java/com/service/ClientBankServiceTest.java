package com.service;

import static org.mockito.Mockito.when;

import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import com.domain.model.Account;
import com.service.model.AccountEntity;
import com.service.model.ClientEntity;
import com.service.repository.AccountRepository;
import com.service.repository.ClientRepository;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ClientBankServiceTest {

	@MockBean
    private ClientRepository clientRepository;
	
	@MockBean
    private AccountRepository accountRepository;

	@Autowired
    private ClientBankService clientBankService;
	
	
	@Test
	public void WhenGetAccountWithValidNameAndAccountCodeThenReturnCorrespondingAccount() {
		// Given	
    	String accountCode = "14451";
    	double accountBalance = 0;
    	AccountEntity account = new AccountEntity();
    	account.setCode(accountCode);
    	account.setBalance(accountBalance);
    	
    	String clientName = "sakhri.mohamed";
    	ClientEntity clientEntity  = new ClientEntity();
    	clientEntity.setName(clientName);
    	clientEntity.getAccounts().add(account);
    	
		when(clientRepository.findByName(clientName)).thenReturn(clientEntity);

		// When
		 Account retreivedAccount = clientBankService.getAccountInformations(clientName, accountCode);
		
		// Then
	    Assertions.assertThat(retreivedAccount.getCode()).isEqualTo(accountCode);
	    Assertions.assertThat(retreivedAccount.getBalance()).isEqualTo(accountBalance);
	}
	
	@Test
	public void WhenDepositAmmountWithValidNameAndAccountCodeThenReturnUpdatedAccount() {
		// Given	
    	String accountCode = "14451";
    	double accountBalance = 0;
    	AccountEntity account = new AccountEntity();
    	account.setCode(accountCode);
    	account.setBalance(accountBalance);
    	
    	String clientName = "sakhri.mohamed";
    	ClientEntity clientEntity  = new ClientEntity();
    	clientEntity.setName(clientName);
    	clientEntity.getAccounts().add(account);
    	
    	double ammount = 100;
    	
		when(clientRepository.findByName(clientName)).thenReturn(clientEntity);
		when(accountRepository.findByCode(accountCode)).thenReturn(account);

		// When
		 Account retreivedAccount = clientBankService.depositAmmount(clientName, accountCode, ammount);
		
		// Then
	    Assertions.assertThat(retreivedAccount.getCode()).isEqualTo(accountCode);
	    Assertions.assertThat(retreivedAccount.getBalance()).isEqualTo(accountBalance + ammount);
	}
	
	@Test
	public void WhenWithdrawAmmountWithValidNameAndAccountCodeThenReturnUpdatedAccount() {
		// Given	
    	String accountCode = "14451";
    	double accountBalance = 200;
    	AccountEntity account = new AccountEntity();
    	account.setCode(accountCode);
    	account.setBalance(accountBalance);
    	
    	String clientName = "sakhri.mohamed";
    	ClientEntity clientEntity  = new ClientEntity();
    	clientEntity.setName(clientName);
    	clientEntity.getAccounts().add(account);
    	
    	double ammount = 100;
    	
		when(clientRepository.findByName(clientName)).thenReturn(clientEntity);
		when(accountRepository.findByCode(accountCode)).thenReturn(account);

		// When
		 Account retreivedAccount = clientBankService.withdrawAmmount(clientName, accountCode, ammount);
		
		// Then
	    Assertions.assertThat(retreivedAccount.getCode()).isEqualTo(accountCode);
	    Assertions.assertThat(retreivedAccount.getBalance()).isEqualTo(accountBalance - ammount);
	}

}
