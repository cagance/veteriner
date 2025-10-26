package dev.patika.veteriner.dto.request.animal;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AnimalSaveRequest {

    @NotEmpty(message = "Hayvan adı boş olamaz.")
    private String name;
    @NotEmpty(message = "Tür boş olamaz.")
    private String species;
    @NotEmpty(message = "Cins boş olamaz.")
    private String breed;
    @NotEmpty(message = "Cinsiyet boş olamaz.")
    private String gender;
    @NotEmpty(message = "Renk boş olamaz.")
    private String colour;
    @NotNull(message = "Doğum tarihi boş olamaz.")
    private LocalDate dateOfBirth;
    @NotNull(message = "Müşteri ID boş olamaz.")
    private Long customerId;
}
