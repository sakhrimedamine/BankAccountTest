package com.service.repository;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.domain.model.Client;
import com.domain.ports.ClientPort;
import com.service.springDataRepository.SpringDataClientRepository;

@Component
public class ClientRepository implements ClientPort{

	@Autowired
	private SpringDataClientRepository springDataClientRepository;
	
	@Override
	public Optional<Client> load(String name) {
		return Optional.ofNullable(springDataClientRepository.findByName(name).toClient());
	}

}
