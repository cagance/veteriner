package dev.patika.veteriner.business;

import dev.patika.veteriner.core.exception.DuplicateDataException;
import dev.patika.veteriner.core.exception.NotFoundException;
import dev.patika.veteriner.dao.AvailableDateRepo;
import dev.patika.veteriner.dao.DoctorRepo;
import dev.patika.veteriner.entities.AvailableDate;
import dev.patika.veteriner.entities.Doctor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AvailableDateManager implements IAvailableDateService {

    private final AvailableDateRepo availableDateRepo;
    private final DoctorRepo doctorRepo;

    @Override
    public AvailableDate getById(Long id) {
        return this.availableDateRepo.findById(id).orElseThrow(() -> new NotFoundException(id + " id li müsait gün bulunamadı!"));
    }

    @Override
    public AvailableDate save(AvailableDate availableDate) {
        Doctor doctor = this.doctorRepo.findById(availableDate.getDoctor().getId()).orElseThrow(()-> new NotFoundException("Doktor bulunamadı"));
        availableDate.setDoctor(doctor);

        Optional<AvailableDate> isAvailableDateExist = availableDateRepo.findByAvailableDateAndDoctorId(availableDate.getAvailableDate(), availableDate.getDoctor().getId());
        if (isAvailableDateExist.isPresent()) {
            throw new DuplicateDataException("Doktorun bu tarihte zaten bir müsait günü var!");
        }

        return this.availableDateRepo.save(availableDate);
    }

    @Override
    public AvailableDate update(AvailableDate availableDate) {
        this.getById(availableDate.getId());
        return this.availableDateRepo.save(availableDate);
    }

    @Override
    public void delete(Long id) {
        this.availableDateRepo.delete(this.getById(id));
    }

    @Override
    public List<AvailableDate> findAll() {
        return this.availableDateRepo.findAll();
    }
}
