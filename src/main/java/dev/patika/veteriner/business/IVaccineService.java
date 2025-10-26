package dev.patika.veteriner.business;

import dev.patika.veteriner.entities.Vaccine;

import java.time.LocalDate;
import java.util.List;

public interface IVaccineService {
    Vaccine getById(Long id);
    Vaccine save(Vaccine vaccine);
    Vaccine update(Vaccine vaccine);
    void delete(Long id);
    List<Vaccine> findAll();
    List<Vaccine> getByAnimalId(Long animalId);
    List<Vaccine> getByProtectionFinishDateBetween(LocalDate startDate, LocalDate endDate);
}
