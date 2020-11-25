package com.service.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.service.model.ClientEntity;

public interface ClientRepository extends JpaRepository<ClientEntity, Long>{

	public ClientEntity findByName(String name);

}
