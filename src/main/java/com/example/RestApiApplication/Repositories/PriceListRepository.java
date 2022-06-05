package com.example.RestApiApplication.Repositories;

import java.util.List;
import com.example.RestApiApplication.Entities.PriceList;
import org.springframework.data.repository.CrudRepository;

public interface PriceListRepository extends CrudRepository<PriceList, Long> {
  PriceList findByDescription(String name);
  List<PriceList> findAll();
}
