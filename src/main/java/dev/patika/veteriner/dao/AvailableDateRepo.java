package dev.patika.veteriner.dao;

import dev.patika.veteriner.entities.AvailableDate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public interface AvailableDateRepo extends JpaRepository<AvailableDate, Long> {
    Optional<AvailableDate> findByAvailableDateAndDoctorId(LocalDate availableDate, Long doctorId);
}
