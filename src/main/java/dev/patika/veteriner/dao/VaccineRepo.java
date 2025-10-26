package dev.patika.veteriner.dao;

import dev.patika.veteriner.entities.Vaccine;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface VaccineRepo extends JpaRepository<Vaccine, Long> {
    List<Vaccine> findByAnimalId(Long animalId);
    List<Vaccine> findByProtectionFinishDateBetween(LocalDate startDate, LocalDate endDate);

    List<Vaccine> findByNameAndCodeAndAnimalId(String name, String code, Long animalId);
}
