package dev.patika.veteriner.business;

import dev.patika.veteriner.core.exception.NotFoundException;
import dev.patika.veteriner.dao.DoctorRepo;
import dev.patika.veteriner.entities.Doctor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class DoctorManager implements IDoctorService {

    private final DoctorRepo doctorRepo;

    @Override
    public Doctor getById(Long id) {
        return this.doctorRepo.findById(id).orElseThrow(() -> new NotFoundException(id + " id li doktor bulunamadı!"));
    }

    @Override
    public Doctor save(Doctor doctor) {
        return this.doctorRepo.save(doctor);
    }

    @Override
    public Doctor update(Doctor doctor) {
        this.getById(doctor.getId());
        return this.doctorRepo.save(doctor);
    }

    @Override
    public void delete(Long id) {
        this.doctorRepo.delete(this.getById(id));
    }

    @Override
    public List<Doctor> findAll() {
        return this.doctorRepo.findAll();
    }
}
