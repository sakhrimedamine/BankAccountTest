package com.service.springDataRepository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.service.model.ClientEntity;

public interface SpringDataClientRepository extends JpaRepository<ClientEntity, Long>{

	public ClientEntity findByName(String name);

}
