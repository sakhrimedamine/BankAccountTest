package com.service.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.domain.ports.AccountPort;
import com.service.model.AccountEntity;
import com.service.springDataRepository.SpringDataAccountRepository;

@Component
public class AccountRepository implements AccountPort{

	@Autowired
	private SpringDataAccountRepository springDataAccountRepository;

	@Override
	public void update(String accountCode, double balance) {
		AccountEntity account = springDataAccountRepository.findByCode(accountCode);
		account.setBalance(balance);
		springDataAccountRepository.save(account);
	}
}
