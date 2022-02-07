package com.example.RestApiApplication.Controllers;

import java.util.List;

import com.example.RestApiApplication.Constants.ApiController;
import com.example.RestApiApplication.Entities.Client;
import com.example.RestApiApplication.Exceptions.ClientNotFoundException;
import com.example.RestApiApplication.Services.ClientService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

  @GetMapping
  public ResponseEntity<List<Client>> getAll() {
    return new ResponseEntity<List<Client>>(
      this.clientService.getAll(),
      HttpStatus.OK
    );
  }

  @GetMapping(ApiController.ID_PATH)
  public ResponseEntity<Client> getById(@PathVariable("id") Long id) {
    try {
      Client findClient = this.clientService.getById(id);

      return new ResponseEntity<>(findClient, HttpStatus.OK);
    } catch (ClientNotFoundException e) {
      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    } catch (Exception e) {
      return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }
  }

  @PatchMapping(ApiController.ID_PATH)
  public ResponseEntity<Client> update(
    @RequestBody Client client,
    @PathVariable("id") Long id
  ) {
    try {
      Client updatedClient = this.clientService.update(client, id);

      return new ResponseEntity<Client>(updatedClient, HttpStatus.CREATED);
    } catch (ClientNotFoundException e) {
      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    } catch (Exception e) {
      return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }
  }

  @DeleteMapping(ApiController.ID_PATH)
  public ResponseEntity<Long> delete(@PathVariable("id") Long id) {
    this.clientService.delete(id);
    
    return new ResponseEntity<Long>(id, HttpStatus.OK);
  }

}
