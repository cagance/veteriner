package dev.patika.veteriner.dto.response.appointment;

import dev.patika.veteriner.dto.response.animal.AnimalResponse;
import dev.patika.veteriner.dto.response.doctor.DoctorResponse;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AppointmentResponse {
    private Long id;
    private LocalDateTime appointmentDate;
    private AnimalResponse animal;
    private DoctorResponse doctor;
}
