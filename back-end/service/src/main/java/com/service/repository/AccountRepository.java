package com.service.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.service.model.AccountEntity;

public interface AccountRepository extends JpaRepository<AccountEntity, Long>{

	public AccountEntity findByCode(String code);

}
