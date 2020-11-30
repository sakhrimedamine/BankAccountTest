package com.domain.ports;

import java.util.Optional;

import com.domain.model.Client;

public interface ClientPort {

    Optional<Client> load(String name);
   
}
