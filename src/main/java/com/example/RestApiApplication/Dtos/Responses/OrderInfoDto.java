package com.example.RestApiApplication.Dtos.Responses;

import com.example.RestApiApplication.Entities.Employee;
import com.example.RestApiApplication.Entities.PriceList;
import java.util.List;

public class OrderInfoDto {
    private List<Employee> employeesList;

    private List<PriceList> priceLists;

    OrderInfoDto() {}

    public OrderInfoDto(
            List<Employee> employeesList,
            List<PriceList> priceLists
    ) {
        this.employeesList = employeesList;
        this.priceLists = priceLists;
    }

    public List<Employee> getEmployeesList() {
        return this.employeesList;
    }

    public void setEmployeesList(List<Employee> employeesList) {
        this.employeesList = employeesList;
    }

    public List<PriceList> getPriceList() {
        return this.priceLists;
    }

    public void setPriceList(List<PriceList> priceLists) {
        this.priceLists = priceLists;
    }

}
