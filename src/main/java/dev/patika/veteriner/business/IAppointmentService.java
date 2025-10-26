package dev.patika.veteriner.business;

import dev.patika.veteriner.entities.Appointment;

import java.time.LocalDateTime;
import java.util.List;

public interface IAppointmentService {
    Appointment getById(Long id);
    Appointment save(Appointment appointment);
    Appointment update(Appointment appointment);
    void delete(Long id);
    List<Appointment> findAll();
    List<Appointment> getByDoctorIdAndAppointmentDate(Long doctorId, LocalDateTime appointmentDate);
    List<Appointment> getByAnimalIdAndAppointmentDate(Long animalId, LocalDateTime appointmentDate);
}
