package dev.patika.veteriner.business;

import dev.patika.veteriner.core.exception.NotFoundException;
import dev.patika.veteriner.dao.CustomerRepo;
import dev.patika.veteriner.entities.Customer;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CustomerManager implements ICustomerService {

    private final CustomerRepo customerRepo;

    @Override
    public Customer getById(Long id) {
        return this.customerRepo.findById(id).orElseThrow(() -> new NotFoundException(id + " id li müşteri bulunamadı!"));
    }

    @Override
    public Customer save(Customer customer) {
        return this.customerRepo.save(customer);
    }

    @Override
    public Customer update(Customer customer) {
        this.getById(customer.getId());
        return this.customerRepo.save(customer);
    }

    @Override
    public void delete(Long id) {
        this.customerRepo.delete(this.getById(id));
    }

    @Override
    public List<Customer> findAll() {
        return this.customerRepo.findAll();
    }

    @Override
    public Customer getByName(String name) {
        return this.customerRepo.findByName(name).orElseThrow(() -> new NotFoundException(name + " isimli müşteri bulunamadı!"));
    }
}
