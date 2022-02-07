package com.example.RestApiApplication.Services;

import java.util.List;

import com.example.RestApiApplication.Entities.Client;
import com.example.RestApiApplication.Exceptions.ClientNotFoundException;
import com.example.RestApiApplication.Repositories.ClientRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ClientService {
  @Autowired
  private ClientRepository clientRepository;

  public Client create(Client client) {
    Client foundClient = this.clientRepository.findByNameAndEmail(
        client.getName(),
        client.getEmail()
    );

    if (foundClient != null) {
      return foundClient;
    } else {
      return this.clientRepository.save(client);
    }
  }

  public Client getById(Long id) throws ClientNotFoundException {
    return this.clientRepository
      .findById(id)
      .orElseThrow(() -> new ClientNotFoundException());
  }

  public List<Client> getAll() {
    return this.clientRepository.findAll();
  }

  public Client update(Client client, Long id) throws ClientNotFoundException {
    Client foundClient = this.clientRepository
        .findById(id)
        .orElseThrow(() -> new ClientNotFoundException());

    foundClient.setEmail(client.getName());
    foundClient.setName(client.getEmail());

    return this.clientRepository.save(foundClient);
  }

  public Long delete(Long id) {
    this.clientRepository.deleteById(id);
    return id;
  }

}
