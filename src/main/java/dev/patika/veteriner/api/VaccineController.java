package dev.patika.veteriner.api;

import dev.patika.veteriner.business.IVaccineService;
import dev.patika.veteriner.core.result.ResultData;
import dev.patika.veteriner.core.result.ResultHelper;
import dev.patika.veteriner.core.utilies.IModelMapperService;
import dev.patika.veteriner.dto.request.vaccine.VaccineSaveRequest;
import dev.patika.veteriner.dto.request.vaccine.VaccineUpdateRequest;
import dev.patika.veteriner.dto.response.vaccine.VaccineResponse;
import dev.patika.veteriner.entities.Animal;
import dev.patika.veteriner.entities.Vaccine;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/v1/vaccines")
@RequiredArgsConstructor
public class VaccineController {

    private final IVaccineService vaccineService;
    private final IModelMapperService modelMapper;

    @GetMapping()
    @ResponseStatus(HttpStatus.OK)
    public ResultData<List<VaccineResponse>> findAll() {
        List<Vaccine> vaccines = this.vaccineService.findAll();
        List<VaccineResponse> vaccineResponses = vaccines.stream()
                .map(vaccine -> this.modelMapper.forResponse().map(vaccine, VaccineResponse.class))
                .collect(Collectors.toList());
        return ResultHelper.success(vaccineResponses);
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ResultData<VaccineResponse> getById(@PathVariable("id") Long id) {
        Vaccine vaccine = this.vaccineService.getById(id);
        return ResultHelper.success(this.modelMapper.forResponse().map(vaccine, VaccineResponse.class));
    }

    @GetMapping("/filter-by-animal")
    @ResponseStatus(HttpStatus.OK)
    public ResultData<List<VaccineResponse>> getByAnimalId(@RequestParam("animalId") Long animalId) {
        List<Vaccine> vaccines = this.vaccineService.getByAnimalId(animalId);
        List<VaccineResponse> vaccineResponses = vaccines.stream()
                .map(vaccine -> this.modelMapper.forResponse().map(vaccine, VaccineResponse.class))
                .collect(Collectors.toList());
        return ResultHelper.success(vaccineResponses);
    }

    @GetMapping("/filter-by-date")
    @ResponseStatus(HttpStatus.OK)
    public ResultData<List<VaccineResponse>> getByProtectionFinishDateBetween(
            @RequestParam("startDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate startDate,
            @RequestParam("endDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate endDate) {
        List<Vaccine> vaccines = this.vaccineService.getByProtectionFinishDateBetween(startDate, endDate);
        List<VaccineResponse> vaccineResponses = vaccines.stream()
                .map(vaccine -> this.modelMapper.forResponse().map(vaccine, VaccineResponse.class))
                .collect(Collectors.toList());
        return ResultHelper.success(vaccineResponses);
    }

    @PostMapping()
    @ResponseStatus(HttpStatus.CREATED)
    public ResultData<VaccineResponse> save(@Valid @RequestBody VaccineSaveRequest vaccineSaveRequest) {
        Vaccine savedVaccine = this.modelMapper.forRequest().map(vaccineSaveRequest, Vaccine.class);
        Animal animal = new Animal();
        animal.setId(vaccineSaveRequest.getAnimalId());
        savedVaccine.setAnimal(animal);
        this.vaccineService.save(savedVaccine);
        return ResultHelper.created(this.modelMapper.forResponse().map(savedVaccine, VaccineResponse.class));
    }

    @PutMapping()
    @ResponseStatus(HttpStatus.OK)
    public ResultData<VaccineResponse> update(@Valid @RequestBody VaccineUpdateRequest vaccineUpdateRequest) {
        Vaccine updatedVaccine = this.modelMapper.forRequest().map(vaccineUpdateRequest, Vaccine.class);
        Animal animal = new Animal();
        animal.setId(vaccineUpdateRequest.getAnimalId());
        updatedVaccine.setAnimal(animal);
        this.vaccineService.update(updatedVaccine);
        return ResultHelper.success(this.modelMapper.forResponse().map(updatedVaccine, VaccineResponse.class));
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ResultData<VaccineResponse> delete(@PathVariable("id") Long id) {
        this.vaccineService.delete(id);
        return ResultHelper.success(null);
    }
}
