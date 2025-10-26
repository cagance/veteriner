package dev.patika.veteriner.dto.request.vaccine;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class VaccineSaveRequest {
    @NotEmpty(message = "Aşı adı boş olamaz.")
    private String name;
    @NotEmpty(message = "Aşı kodu boş olamaz.")
    private String code;
    @NotNull(message = "Koruma başlangıç tarihi boş olamaz.")
    private LocalDate protectionStartDate;
    @NotNull(message = "Koruma bitiş tarihi boş olamaz.")
    private LocalDate protectionFinishDate;
    @NotNull(message = "Hayvan ID boş olamaz.")
    private Long animalId;
}
