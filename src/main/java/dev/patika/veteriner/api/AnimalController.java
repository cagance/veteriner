package dev.patika.veteriner.api;

import dev.patika.veteriner.business.IAnimalService;
import dev.patika.veteriner.core.result.ResultData;
import dev.patika.veteriner.core.result.ResultHelper;
import dev.patika.veteriner.core.utilies.IModelMapperService;
import dev.patika.veteriner.dto.request.animal.AnimalSaveRequest;
import dev.patika.veteriner.dto.request.animal.AnimalUpdateRequest;
import dev.patika.veteriner.dto.response.animal.AnimalResponse;
import dev.patika.veteriner.entities.Animal;
import dev.patika.veteriner.entities.Customer;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/v1/animals")
@RequiredArgsConstructor
public class AnimalController {

    private final IAnimalService animalService;
    private final IModelMapperService modelMapper;

    @GetMapping()
    @ResponseStatus(HttpStatus.OK)
    public ResultData<List<AnimalResponse>> findAll() {
        List<Animal> animals = this.animalService.findAll();
        List<AnimalResponse> animalResponses = animals.stream()
                .map(animal -> this.modelMapper.forResponse().map(animal, AnimalResponse.class))
                .collect(Collectors.toList());
        return ResultHelper.success(animalResponses);
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ResultData<AnimalResponse> getById(@PathVariable("id") Long id) {
        Animal animal = this.animalService.getById(id);
        return ResultHelper.success(this.modelMapper.forResponse().map(animal, AnimalResponse.class));
    }

    @GetMapping("/filter-by-name")
    @ResponseStatus(HttpStatus.OK)
    public ResultData<List<AnimalResponse>> getByName(@RequestParam("name") String name) {
        List<Animal> animals = this.animalService.getByName(name);
        List<AnimalResponse> animalResponses = animals.stream()
                .map(animal -> this.modelMapper.forResponse().map(animal, AnimalResponse.class))
                .collect(Collectors.toList());
        return ResultHelper.success(animalResponses);
    }

    @GetMapping("/filter-by-customer")
    @ResponseStatus(HttpStatus.OK)
    public ResultData<List<AnimalResponse>> getByCustomer(@RequestParam("customerId") Long customerId) {
        List<Animal> animals = this.animalService.getByCustomer(customerId);
        List<AnimalResponse> animalResponses = animals.stream()
                .map(animal -> this.modelMapper.forResponse().map(animal, AnimalResponse.class))
                .collect(Collectors.toList());
        return ResultHelper.success(animalResponses);
    }

    @PostMapping()
    @ResponseStatus(HttpStatus.CREATED)
    public ResultData<AnimalResponse> save(@Valid @RequestBody AnimalSaveRequest animalSaveRequest) {
        Animal savedAnimal = this.modelMapper.forRequest().map(animalSaveRequest, Animal.class);
        Customer customer = new Customer();
        customer.setId(animalSaveRequest.getCustomerId());
        savedAnimal.setCustomer(customer);
        this.animalService.save(savedAnimal);
        return ResultHelper.created(this.modelMapper.forResponse().map(savedAnimal, AnimalResponse.class));
    }

    @PutMapping()
    @Response.Status(HttpStatus.OK)
    public ResultData<AnimalResponse> update(@Valid @RequestBody AnimalUpdateRequest animalUpdateRequest) {
        Animal updatedAnimal = this.modelMapper.forRequest().map(animalUpdateRequest, Animal.class);
        Customer customer = new Customer();
        customer.setId(animalUpdateRequest.getCustomerId());
        updatedAnimal.setCustomer(customer);
        this.animalService.update(updatedAnimal);
        return ResultHelper.success(this.modelMapper.forResponse().map(updatedAnimal, AnimalResponse.class));
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ResultData<AnimalResponse> delete(@PathVariable("id") Long id) {
        this.animalService.delete(id);
        return ResultHelper.success(null);
    }
}
