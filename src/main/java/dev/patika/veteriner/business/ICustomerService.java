package dev.patika.veteriner.business;

import dev.patika.veteriner.entities.Customer;

import java.util.List;

public interface ICustomerService {
    Customer getById(Long id);
    Customer save(Customer customer);
    Customer update(Customer customer);
    void delete(Long id);
    List<Customer> findAll();
    Customer getByName(String name);
}
