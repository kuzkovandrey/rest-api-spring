package com.example.RestApiApplication.Controllers;

import java.util.List;

import com.example.RestApiApplication.Constants.PathVariables;
import com.example.RestApiApplication.Constants.ApiController;
import com.example.RestApiApplication.Dtos.Responses.ErrorResponse;
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

  @Autowired()
  private ClientService clientService;

  @GetMapping
  public ResponseEntity<List<Client>> getAll() {
    return new ResponseEntity<List<Client>>(
      this.clientService.getAll(),
      HttpStatus.OK
    );
  }

  @GetMapping(ApiController.ID_PATH)
  public ResponseEntity<?> getById(@PathVariable(PathVariables.ID) Long id) {
    try {
      Client findClient = this.clientService.getById(id);

      return new ResponseEntity<Client>(findClient, HttpStatus.OK);
    } catch (ClientNotFoundException e) {
      return ErrorResponse.getNotFoundError(e);
    } catch (Exception e) {
      return ErrorResponse.getServerError();
    }
  }

  @DeleteMapping(ApiController.ID_PATH)
  public ResponseEntity<Long> delete(@PathVariable(PathVariables.ID) Long id) {
    this.clientService.delete(id);
    
    return new ResponseEntity<Long>(id, HttpStatus.OK);
  }

}
