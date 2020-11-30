package com.service.springDataRepository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.service.model.AccountEntity;

public interface SpringDataAccountRepository extends JpaRepository<AccountEntity, Long>{

	public AccountEntity findByCode(String code);

}
