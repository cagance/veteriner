package dev.patika.veteriner.api;

import dev.patika.veteriner.business.IDoctorService;
import dev.patika.veteriner.core.result.ResultData;
import dev.patika.veteriner.core.result.ResultHelper;
import dev.patika.veteriner.core.utilies.IModelMapperService;
import dev.patika.veteriner.dto.request.doctor.DoctorSaveRequest;
import dev.patika.veteriner.dto.request.doctor.DoctorUpdateRequest;
import dev.patika.veteriner.dto.response.doctor.DoctorResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/v1/doctors")
@RequiredArgsConstructor
public class DoctorController {

    private final IDoctorService doctorService;
    private final IModelMapperService modelMapper;

    @GetMapping()
    @ResponseStatus(HttpStatus.OK)
    public ResultData<List<DoctorResponse>> findAll() {
        List<Doctor> doctors = this.doctorService.findAll();
        List<DoctorResponse> doctorResponses = doctors.stream()
                .map(doctor -> this.modelMapper.forResponse().map(doctor, DoctorResponse.class))
                .collect(Collectors.toList());
        return ResultHelper.success(doctorResponses);
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ResultData<DoctorResponse> getById(@PathVariable("id") Long id) {
        Doctor doctor = this.doctorService.getById(id);
        return ResultHelper.success(this.modelMapper.forResponse().map(doctor, DoctorResponse.class));
    }

    @PostMapping()
    @ResponseStatus(HttpStatus.CREATED)
    public ResultData<DoctorResponse> save(@Valid @RequestBody DoctorSaveRequest doctorSaveRequest) {
        Doctor savedDoctor = this.modelMapper.forRequest().map(doctorSaveRequest, Doctor.class);
        this.doctorService.save(savedDoctor);
        return ResultHelper.created(this.modelMapper.forResponse().map(savedDoctor, DoctorResponse.class));
    }

    @PutMapping()
    @ResponseStatus(HttpStatus.OK)
    public ResultData<DoctorResponse> update(@Valid @RequestBody DoctorUpdateRequest doctorUpdateRequest) {
        Doctor updatedDoctor = this.modelMapper.forRequest().map(doctorUpdateRequest, Doctor.class);
        this.doctorService.update(updatedDoctor);
        return ResultHelper.success(this.modelMapper.forResponse().map(updatedDoctor, DoctorResponse.class));
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ResultData<DoctorResponse> delete(@PathVariable("id") Long id) {
        this.doctorService.delete(id);
        return ResultHelper.success(null);
    }
}
