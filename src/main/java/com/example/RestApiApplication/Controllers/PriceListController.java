package com.example.RestApiApplication.Controllers;

import java.util.List;

import com.example.RestApiApplication.Constants.PathVariables;
import com.example.RestApiApplication.Constants.ApiController;
import com.example.RestApiApplication.Dtos.Responses.ErrorResponse;
import com.example.RestApiApplication.Entities.PriceList;
import com.example.RestApiApplication.Exceptions.PriceListNotFoundException;
import com.example.RestApiApplication.Services.PriceListService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(ApiController.PRICE_LISTS)
public class PriceListController {
  @Autowired
  private PriceListService priceListService;

  @PostMapping
  public ResponseEntity<PriceList> create(@RequestBody PriceList priceList) {
    try {
      PriceList newPriceList = this.priceListService.create(priceList);

      return new ResponseEntity<PriceList>(newPriceList, HttpStatus.CREATED);
    } catch (Exception e) {
      return ResponseEntity.badRequest().body(null);
    }
  }

  @GetMapping
  public ResponseEntity<List<PriceList>> getAll() {
    return new ResponseEntity<List<PriceList>>(
      this.priceListService.getAll(),
      HttpStatus.OK
    ); 
  }

  @GetMapping(ApiController.ID_PATH)
  public ResponseEntity<?> getById(@PathVariable(PathVariables.ID) Long id) {
    try {
      PriceList findPriceList = this.priceListService.getById(id);

      return new ResponseEntity<PriceList>(findPriceList, HttpStatus.OK);
    } catch (PriceListNotFoundException e) {
      return ErrorResponse.getNotFoundError(e);
    } catch (Exception e) {
      return ErrorResponse.getServerError();
    }
  }

  @PatchMapping(ApiController.ID_PATH)
  public ResponseEntity<?> update(
    @RequestBody PriceList priceList,
    @PathVariable("id") Long id
  ) {
    try {
      PriceList updatedPriceList = this.priceListService.update(priceList, id);

      return new ResponseEntity<PriceList>(updatedPriceList, HttpStatus.CREATED);
    } catch (PriceListNotFoundException e) {
      return ErrorResponse.getNotFoundError(e);
    } catch (Exception e) {
      return ErrorResponse.getServerError();
    }
  }

  @DeleteMapping(ApiController.ID_PATH)
  public ResponseEntity<Long> delete(@PathVariable(PathVariables.ID) Long id) {
    this.priceListService.delete(id);
    
    return new ResponseEntity<Long>(id, HttpStatus.OK);
  }
}
