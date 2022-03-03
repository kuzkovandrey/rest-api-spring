package com.example.RestApiApplication.Services;

import java.util.List;

import com.example.RestApiApplication.Entities.PriceList;
import com.example.RestApiApplication.Exceptions.PriceListNotFoundException;
import com.example.RestApiApplication.Repositories.PriceListRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PriceListService {
  @Autowired
  private PriceListRepository priceListRepository;

  private PriceList findByDescription(String desc) {
    return this.priceListRepository.findByDescription(desc);
  }

  public PriceList create(PriceList price) {
    PriceList priceList = this.findByDescription(price.getDescription());

    if (priceList == null) return this.priceListRepository.save(price);

    priceList.setCost(price.getCost());
      
    return this.priceListRepository.save(priceList);
  }

  public PriceList getById(Long id) throws PriceListNotFoundException {
    return this.priceListRepository
      .findById(id)
      .orElseThrow(() -> new PriceListNotFoundException());
  }

  public List<PriceList> getAll() {
    return this.priceListRepository.findAll();
  }

  public PriceList update(PriceList price, Long id) throws PriceListNotFoundException {
    PriceList priceList = this.getById(id);

    priceList.setCost(price.getCost());
    priceList.setDescription(price.getDescription());

    return this.priceListRepository.save(priceList);
  }

  public Long delete(Long id) {
    this.priceListRepository.deleteById(id);
    return id;
  }
  
}
