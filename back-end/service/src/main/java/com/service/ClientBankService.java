package com.service;

import org.springframework.stereotype.Service;

import com.domain.ClientBankDomainService;
import com.domain.model.Account;
import com.service.repository.AccountRepository;
import com.service.repository.ClientRepository;

@Service
public class ClientBankService {

    private ClientBankDomainService innerService;

    public ClientBankService(ClientRepository clientRepository, AccountRepository accountRepository) {
        this.innerService = new ClientBankDomainService(clientRepository,  accountRepository);
    }
    
	public Account getAccountInformations(String clientName, String  accountCode) {
		return innerService.getAccountInformation(clientName, accountCode);
	}
	
	public Account depositAmmount(String clientName, String  accountCode, double ammount) {
		return innerService.depositAmmount(clientName, accountCode, ammount);
	}
	
	public Account withdrawAmmount(String clientName, String  accountCode, double ammount) {
		return innerService.withdrawAmmount(clientName, accountCode, ammount);

	}
	
}
