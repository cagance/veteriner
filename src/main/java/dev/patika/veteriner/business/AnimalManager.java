package dev.patika.veteriner.business;

import dev.patika.veteriner.core.exception.NotFoundException;
import dev.patika.veteriner.dao.AnimalRepo;
import dev.patika.veteriner.dao.CustomerRepo;
import dev.patika.veteriner.entities.Animal;
import dev.patika.veteriner.entities.Customer;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AnimalManager implements IAnimalService {

    private final AnimalRepo animalRepo;
    private final CustomerRepo customerRepo;

    @Override
    public Animal getById(Long id) {
        return this.animalRepo.findById(id).orElseThrow(() -> new NotFoundException(id + " id li hayvan bulunamadı!"));
    }

    @Override
    public Animal save(Animal animal) {
        Customer customer = this.customerRepo.findById(animal.getCustomer().getId()).orElseThrow(()-> new NotFoundException("Müşteri bulunamadı"));
        animal.setCustomer(customer);
        return this.animalRepo.save(animal);
    }

    @Override
    public Animal update(Animal animal) {
        this.getById(animal.getId());
        return this.animalRepo.save(animal);
    }

    @Override
    public void delete(Long id) {
        this.animalRepo.delete(this.getById(id));
    }

    @Override
    public List<Animal> findAll() {
        return this.animalRepo.findAll();
    }

    @Override
    public List<Animal> getByName(String name) {
        return this.animalRepo.findByName(name);
    }

    @Override
    public List<Animal> getByCustomer(Long customerId) {
        return this.animalRepo.findAll().stream().filter(animal -> animal.getCustomer().getId().equals(customerId)).toList();
    }
}
