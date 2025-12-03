package com.example.Car_Map.Service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.example.Car_Map.Dto.CarRequest;
import com.example.Car_Map.Dto.CarResponse;
import com.example.Car_Map.Dto.CarSearchRequest;

public interface ICarService {
    public Page<CarResponse> getAll(Pageable pageable);

    public CarResponse getById(Long id);

    public CarResponse create(CarRequest request);

    public CarResponse update(CarRequest request, Long id);

    public void delete(Long id);

    public Page<CarResponse> searchCar(CarSearchRequest request, Pageable pageable);
}
