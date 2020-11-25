package com.service.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.domain.model.Account;
import com.service.ClientBankService;

@RestController
@RequestMapping("/api/clients")
public class ClientBankController {
	
	
	@Autowired
	private ClientBankService clientBankService;
	
	
	@GetMapping("{clientName}/accounts/{accountCode}")
	public ResponseEntity<Account> getAccountIformations(
			@PathVariable("clientName") String clientName,
			@PathVariable("accountCode") String accountCode) {
		
		return ResponseEntity.ok(clientBankService.getAccountInformations(clientName, accountCode));
	}
	
	@PutMapping("{clientName}/accounts/{accountCode}/deposit/{ammount}")
	public ResponseEntity<Account> depositAmmount(
			@PathVariable("clientName") String clientName,
			@PathVariable("accountCode") String accountCode,
			@PathVariable("ammount") Double  ammount) {
		
		return ResponseEntity.ok(clientBankService.depositAmmount(clientName, accountCode, ammount));
	}
	
	@PutMapping("{clientName}/accounts/{accountCode}/withdraw/{ammount}")
	public ResponseEntity<Account> withdrawAmmount(
			@PathVariable("clientName") String clientName,
			@PathVariable("accountCode") String accountCode,
			@PathVariable("ammount") Double  ammount) {
		
		return ResponseEntity.ok(clientBankService.withdrawAmmount(clientName, accountCode, ammount));
	}

}
