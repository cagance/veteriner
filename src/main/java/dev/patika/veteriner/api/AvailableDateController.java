package dev.patika.veteriner.api;

import dev.patika.veteriner.business.IAvailableDateService;
import dev.patika.veteriner.core.result.ResultData;
import dev.patika.veteriner.core.result.ResultHelper;
import dev.patika.veteriner.core.utilies.IModelMapperService;
import dev.patika.veteriner.dto.request.availableDate.AvailableDateSaveRequest;
import dev.patika.veteriner.dto.request.availableDate.AvailableDateUpdateRequest;
import dev.patika.veteriner.dto.response.availableDate.AvailableDateResponse;
import dev.patika.veteriner.entities.AvailableDate;
import dev.patika.veteriner.entities.Doctor;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/v1/available-dates")
@RequiredArgsConstructor
public class AvailableDateController {

    private final IAvailableDateService availableDateService;
    private final IModelMapperService modelMapper;

    @GetMapping()
    @ResponseStatus(HttpStatus.OK)
    public ResultData<List<AvailableDateResponse>> findAll() {
        List<AvailableDate> availableDates = this.availableDateService.findAll();
        List<AvailableDateResponse> availableDateResponses = availableDates.stream()
                .map(availableDate -> this.modelMapper.forResponse().map(availableDate, AvailableDateResponse.class))
                .collect(Collectors.toList());
        return ResultHelper.success(availableDateResponses);
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ResultData<AvailableDateResponse> getById(@PathVariable("id") Long id) {
        AvailableDate availableDate = this.availableDateService.getById(id);
        return ResultHelper.success(this.modelMapper.forResponse().map(availableDate, AvailableDateResponse.class));
    }

    @PostMapping()
    @ResponseStatus(HttpStatus.CREATED)
    public ResultData<AvailableDateResponse> save(@Valid @RequestBody AvailableDateSaveRequest availableDateSaveRequest) {
        AvailableDate savedAvailableDate = this.modelMapper.forRequest().map(availableDateSaveRequest, AvailableDate.class);
        Doctor doctor = new Doctor();
        doctor.setId(availableDateSaveRequest.getDoctorId());
        savedAvailableDate.setDoctor(doctor);
        this.availableDateService.save(savedAvailableDate);
        return ResultHelper.created(this.modelMapper.forResponse().map(savedAvailableDate, AvailableDateResponse.class));
    }

    @PutMapping()
    @ResponseStatus(HttpStatus.OK)
    public ResultData<AvailableDateResponse> update(@Valid @RequestBody AvailableDateUpdateRequest availableDateUpdateRequest) {
        AvailableDate updatedAvailableDate = this.modelMapper.forRequest().map(availableDateUpdateRequest, AvailableDate.class);
        Doctor doctor = new Doctor();
        doctor.setId(availableDateUpdateRequest.getDoctorId());
        updatedAvailableDate.setDoctor(doctor);
        this.availableDateService.update(updatedAvailableDate);
        return ResultHelper.success(this.modelMapper.forResponse().map(updatedAvailableDate, AvailableDateResponse.class));
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ResultData<AvailableDateResponse> delete(@PathVariable("id") Long id) {
        this.availableDateService.delete(id);
        return ResultHelper.success(null);
    }
}
