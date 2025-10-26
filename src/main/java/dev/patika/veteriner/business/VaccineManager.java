package dev.patika.veteriner.business;

import dev.patika.veteriner.core.exception.DuplicateDataException;
import dev.patika.veteriner.core.exception.NotFoundException;
import dev.patika.veteriner.dao.AnimalRepo;
import dev.patika.veteriner.dao.VaccineRepo;
import dev.patika.veteriner.entities.Animal;
import dev.patika.veteriner.entities.Vaccine;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
public class VaccineManager implements IVaccineService {

    private final VaccineRepo vaccineRepo;
    private final AnimalRepo animalRepo;

    @Override
    public Vaccine getById(Long id) {
        return this.vaccineRepo.findById(id).orElseThrow(() -> new NotFoundException(id + " id li aşı bulunamadı!"));
    }

    @Override
    public Vaccine save(Vaccine vaccine) {
        Animal animal = this.animalRepo.findById(vaccine.getAnimal().getId()).orElseThrow(()-> new NotFoundException("Hayvan bulunamadı"));
        vaccine.setAnimal(animal);

        List<Vaccine> vaccines = vaccineRepo.findByNameAndCodeAndAnimalId(vaccine.getName(), vaccine.getCode(), vaccine.getAnimal().getId());
        for (Vaccine v : vaccines) {
            if (v.getProtectionFinishDate().isAfter(vaccine.getProtectionStartDate())) {
                throw new DuplicateDataException("Bu aşının koruyuculuğu henüz bitmedi!");
            }
        }

        return this.vaccineRepo.save(vaccine);
    }

    @Override
    public Vaccine update(Vaccine vaccine) {
        this.getById(vaccine.getId());
        return this.vaccineRepo.save(vaccine);
    }

    @Override
    public void delete(Long id) {
        this.vaccineRepo.delete(this.getById(id));
    }

    @Override
    public List<Vaccine> findAll() {
        return this.vaccineRepo.findAll();
    }

    @Override
    public List<Vaccine> getByAnimalId(Long animalId) {
        return this.vaccineRepo.findByAnimalId(animalId);
    }

    @Override
    public List<Vaccine> getByProtectionFinishDateBetween(LocalDate startDate, LocalDate endDate) {
        return this.vaccineRepo.findByProtectionFinishDateBetween(startDate, endDate);
    }
}
