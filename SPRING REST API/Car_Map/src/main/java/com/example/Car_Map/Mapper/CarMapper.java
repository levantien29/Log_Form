package com.example.Car_Map.Mapper;

import java.util.List;

import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

import com.example.Car_Map.Dto.CarRequest;
import com.example.Car_Map.Dto.CarResponse;
import com.example.Car_Map.Model.Car;
@Mapper(componentModel = "spring")
public interface CarMapper {
    // theem
    public Car toCar(CarRequest request);

    // lissst
    public List<CarResponse> toList(List<Car> cars);

    //toReponse
    public CarResponse toReponse(Car car);

    //update
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    public void toUpdate(@MappingTarget Car car, CarRequest request);
}
