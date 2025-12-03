package com.example.Car_Map.Service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.example.Car_Map.Dto.CarRequest;
import com.example.Car_Map.Dto.CarResponse;
import com.example.Car_Map.Dto.CarSearchRequest;
import com.example.Car_Map.Exception.BadRequestException;
import com.example.Car_Map.Exception.ResourceNotFoundException;
import com.example.Car_Map.Mapper.CarMapper;
import com.example.Car_Map.Model.Car;
import com.example.Car_Map.Repository.CarRepository;
import com.example.Car_Map.specification.CarSpecification;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CarService implements ICarService {
    private final CarRepository repository;
    private final CarMapper mapper;

    @Override
    public Page<CarResponse> getAll(Pageable pageable) {
        return repository.findAll(pageable).map(mapper::toReponse);
    }

    @Override
    public CarResponse getById(Long id) {
        Car car = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("id", "Không tìm thấy xe có id = " + id));
        return mapper.toReponse(car);
    }

    @Override
    public CarResponse create(CarRequest request){
        if (repository.existsByName(request.getName())) {
            throw new BadRequestException("name", "Tên đã tồn tại");
        }
        if (repository.existsByCode(request.getCode())) {
            throw new BadRequestException("code", "Code đã tồn tại");
        }
        Car car = mapper.toCar(request);
        return mapper.toReponse(repository.save(car));
    }

    @Override
    public CarResponse update(CarRequest request, Long id){
        Car car = repository.findById(id)
        .orElseThrow(()-> new ResourceNotFoundException("id", "Không tìm thấy xe có id = " + id));
        if (repository.existsByNameAndIdNot(request.getName(), id)) {
            throw new ResourceNotFoundException("name", "Xe đã tồn tại, Vui lòng thêm xe khác");
        }
        if (repository.existsByCodeAndIdNot(request.getCode(), id)) {
            throw new ResourceNotFoundException("code", "Code đã tồn tại, Vui lòng thêm code khác");
        }
        mapper.toUpdate(car, request);
        return mapper.toReponse(repository.save(car));
    }

    @Override
    public void delete(Long id){
        if (!repository.existsById(id)) {
            throw new ResourceNotFoundException("id", "Không tìm thấy xe có id = " + id);
        }
        repository.deleteById(id);
    }

    @Override
    public Page<CarResponse> searchCar(CarSearchRequest request, Pageable pageable){
        Page<Car> page = repository.findAll(CarSpecification.build(request), pageable);
        return page.map(mapper::toReponse);
    }
}
