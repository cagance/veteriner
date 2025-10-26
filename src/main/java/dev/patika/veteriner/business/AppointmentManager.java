package dev.patika.veteriner.business;

import dev.patika.veteriner.core.exception.DuplicateDataException;
import dev.patika.veteriner.core.exception.NotFoundException;
import dev.patika.veteriner.dao.AnimalRepo;
import dev.patika.veteriner.dao.AppointmentRepo;
import dev.patika.veteriner.dao.AvailableDateRepo;
import dev.patika.veteriner.dao.DoctorRepo;
import dev.patika.veteriner.entities.Animal;
import dev.patika.veteriner.entities.Appointment;
import dev.patika.veteriner.entities.AvailableDate;
import dev.patika.veteriner.entities.Doctor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AppointmentManager implements IAppointmentService {

    private final AppointmentRepo appointmentRepo;
    private final AnimalRepo animalRepo;
    private final DoctorRepo doctorRepo;
    private final AvailableDateRepo availableDateRepo;

    @Override
    public Appointment getById(Long id) {
        return this.appointmentRepo.findById(id).orElseThrow(() -> new NotFoundException(id + " id li randevu bulunamadı!"));
    }

    @Override
    public Appointment save(Appointment appointment) {
        Animal animal = this.animalRepo.findById(appointment.getAnimal().getId()).orElseThrow(()-> new NotFoundException("Hayvan bulunamadı"));
        appointment.setAnimal(animal);

        Doctor doctor = this.doctorRepo.findById(appointment.getDoctor().getId()).orElseThrow(()-> new NotFoundException("Doktor bulunamadı"));
        appointment.setDoctor(doctor);

        Optional<AvailableDate> availableDate = availableDateRepo.findByAvailableDateAndDoctorId(appointment.getAppointmentDate().toLocalDate(), appointment.getDoctor().getId());
        if (availableDate.isEmpty()) {
            throw new NotFoundException("Doktor bu tarihte çalışmamaktadır!");
        }

        List<Appointment> appointments = appointmentRepo.findByDoctorIdAndAppointmentDate(appointment.getDoctor().getId(), appointment.getAppointmentDate());
        if (!appointments.isEmpty()) {
            throw new DuplicateDataException("Girilen saatte başka bir randevu mevcuttur.");
        }

        return this.appointmentRepo.save(appointment);
    }

    @Override
    public Appointment update(Appointment appointment) {
        this.getById(appointment.getId());
        return this.appointmentRepo.save(appointment);
    }

    @Override
    public void delete(Long id) {
        this.appointmentRepo.delete(this.getById(id));
    }

    @Override
    public List<Appointment> findAll() {
        return this.appointmentRepo.findAll();
    }

    @Override
    public List<Appointment> getByDoctorIdAndAppointmentDate(Long doctorId, LocalDateTime appointmentDate) {
        return this.appointmentRepo.findByAppointmentDateBetweenAndDoctorId(appointmentDate, appointmentDate.plusDays(1), doctorId);
    }

    @Override
    public List<Appointment> getByAnimalIdAndAppointmentDate(Long animalId, LocalDateTime appointmentDate) {
        return this.appointmentRepo.findByAppointmentDateBetweenAndAnimalId(appointmentDate, appointmentDate.plusDays(1), animalId);
    }
}
