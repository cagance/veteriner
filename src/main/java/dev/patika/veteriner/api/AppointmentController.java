package dev.patika.veteriner.api;

import dev.patika.veteriner.business.IAppointmentService;
import dev.patika.veteriner.core.result.ResultData;
import dev.patika.veteriner.core.result.ResultHelper;
import dev.patika.veteriner.core.utilies.IModelMapperService;
import dev.patika.veteriner.dto.request.appointment.AppointmentSaveRequest;
import dev.patika.veteriner.dto.request.appointment.AppointmentUpdateRequest;
import dev.patika.veteriner.dto.response.appointment.AppointmentResponse;
import dev.patika.veteriner.entities.Animal;
import dev.patika.veteriner.entities.Appointment;
import dev.patika.veteriner.entities.Doctor;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/v1/appointments")
@RequiredArgsConstructor
public class AppointmentController {

    private final IAppointmentService appointmentService;
    private final IModelMapperService modelMapper;

    @GetMapping()
    @ResponseStatus(HttpStatus.OK)
    public ResultData<List<AppointmentResponse>> findAll() {
        List<Appointment> appointments = this.appointmentService.findAll();
        List<AppointmentResponse> appointmentResponses = appointments.stream()
                .map(appointment -> this.modelMapper.forResponse().map(appointment, AppointmentResponse.class))
                .collect(Collectors.toList());
        return ResultHelper.success(appointmentResponses);
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ResultData<AppointmentResponse> getById(@PathVariable("id") Long id) {
        Appointment appointment = this.appointmentService.getById(id);
        return ResultHelper.success(this.modelMapper.forResponse().map(appointment, AppointmentResponse.class));
    }

    @GetMapping("/filter-by-doctor")
    @ResponseStatus(HttpStatus.OK)
    public ResultData<List<AppointmentResponse>> getByDoctorIdAndAppointmentDate(
            @RequestParam("doctorId") Long doctorId,
            @RequestParam("appointmentDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime appointmentDate) {
        List<Appointment> appointments = this.appointmentService.getByDoctorIdAndAppointmentDate(doctorId, appointmentDate);
        List<AppointmentResponse> appointmentResponses = appointments.stream()
                .map(appointment -> this.modelMapper.forResponse().map(appointment, AppointmentResponse.class))
                .collect(Collectors.toList());
        return ResultHelper.success(appointmentResponses);
    }

    @GetMapping("/filter-by-animal")
    @ResponseStatus(HttpStatus.OK)
    public ResultData<List<AppointmentResponse>> getByAnimalIdAndAppointmentDate(
            @RequestParam("animalId") Long animalId,
            @RequestParam("appointmentDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime appointmentDate) {
        List<Appointment> appointments = this.appointmentService.getByAnimalIdAndAppointmentDate(animalId, appointmentDate);
        List<AppointmentResponse> appointmentResponses = appointments.stream()
                .map(appointment -> this.modelMapper.forResponse().map(appointment, AppointmentResponse.class))
                .collect(Collectors.toList());
        return ResultHelper.success(appointmentResponses);
    }

    @PostMapping()
    @ResponseStatus(HttpStatus.CREATED)
    public ResultData<AppointmentResponse> save(@Valid @RequestBody AppointmentSaveRequest appointmentSaveRequest) {
        Appointment savedAppointment = this.modelMapper.forRequest().map(appointmentSaveRequest, Appointment.class);
        Animal animal = new Animal();
        animal.setId(appointmentSaveRequest.getAnimalId());
        savedAppointment.setAnimal(animal);
        Doctor doctor = new Doctor();
        doctor.setId(appointmentSaveRequest.getDoctorId());
        savedAppointment.setDoctor(doctor);
        this.appointmentService.save(savedAppointment);
        return ResultHelper.created(this.modelMapper.forResponse().map(savedAppointment, AppointmentResponse.class));
    }

    @PutMapping()
    @ResponseStatus(HttpStatus.OK)
    public ResultData<AppointmentResponse> update(@Valid @RequestBody AppointmentUpdateRequest appointmentUpdateRequest) {
        Appointment updatedAppointment = this.modelMapper.forRequest().map(appointmentUpdateRequest, Appointment.class);
        Animal animal = new Animal();
        animal.setId(appointmentUpdateRequest.getAnimalId());
        updatedAppointment.setAnimal(animal);
        Doctor doctor = new Doctor();
        doctor.setId(appointmentUpdateRequest.getDoctorId());
        updatedAppointment.setDoctor(doctor);
        this.appointmentService.update(updatedAppointment);
        return ResultHelper.success(this.modelMapper.forResponse().map(updatedAppointment, AppointmentResponse.class));
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ResultData<AppointmentResponse> delete(@PathVariable("id") Long id) {
        this.appointmentService.delete(id);
        return ResultHelper.success(null);
    }
}
