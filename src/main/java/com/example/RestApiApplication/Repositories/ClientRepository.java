package com.example.RestApiApplication.Repositories;

import java.util.List;
import com.example.RestApiApplication.Entities.Client;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClientRepository extends CrudRepository<Client, Long> { 
  Client findByNameAndEmail(String name, String email);
  List<Client> findAll();
}
