package dev.patika.veteriner.dto.request.customer;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CustomerSaveRequest {

    @NotEmpty(message = "Müşteri adı boş olamaz.")
    private String name;
    @NotEmpty(message = "Telefon numarası boş olamaz.")
    private String phone;
    @Email
    @NotEmpty(message = "Mail adresi boş olamaz.")
    private String mail;
    @NotEmpty(message = "Adres boş olamaz.")
    private String address;
    @NotEmpty(message = "Şehir boş olamaz.")
    private String city;
}
