package dev.patika.veteriner.dto.response.availableDate;

import dev.patika.veteriner.dto.response.doctor.DoctorResponse;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AvailableDateResponse {
    private Long id;
    private LocalDate availableDate;
    private DoctorResponse doctor;
}
