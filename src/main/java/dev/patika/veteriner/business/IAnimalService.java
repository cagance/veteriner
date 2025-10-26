package dev.patika.veteriner.business;

import dev.patika.veteriner.entities.Animal;

import java.util.List;

public interface IAnimalService {
    Animal getById(Long id);
    Animal save(Animal animal);
    Animal update(Animal animal);
    void delete(Long id);
    List<Animal> findAll();
    List<Animal> getByName(String name);

    List<Animal> getByCustomer(Long customerId);
}
