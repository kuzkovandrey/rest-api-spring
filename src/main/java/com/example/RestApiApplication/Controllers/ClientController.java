package com.example.RestApiApplication.Controllers;

import com.example.RestApiApplication.Constants.ApiController;
import com.example.RestApiApplication.Entities.Client;
import com.example.RestApiApplication.Services.ClientService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(ApiController.CLIENTS)
public class ClientController {

  @Autowired
  private ClientService clientService;
  
  @PostMapping()
  public ResponseEntity<Client> create(@RequestBody Client client) {
    try {
      Client newClient = this.clientService.create(client);

      return new ResponseEntity<Client>(newClient, HttpStatus.CREATED);
    } catch (Exception e) {
      return ResponseEntity.badRequest().body(null);
    }
  }
  
}
