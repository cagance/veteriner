package dev.patika.veteriner.business;

import dev.patika.veteriner.entities.AvailableDate;

import java.util.List;

public interface IAvailableDateService {
    AvailableDate getById(Long id);
    AvailableDate save(AvailableDate availableDate);
    AvailableDate update(AvailableDate availableDate);
    void delete(Long id);
    List<AvailableDate> findAll();
}
