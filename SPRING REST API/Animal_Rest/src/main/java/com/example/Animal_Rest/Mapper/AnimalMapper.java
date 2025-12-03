package com.example.Animal_Rest.Mapper;

import com.example.Animal_Rest.Dto.AnimalRequest;
import com.example.Animal_Rest.Dto.AnimalResponse;
import com.example.Animal_Rest.Model.Animal;

public class AnimalMapper {
    // chuyển từ request sang model
    public static Animal toAnimal(AnimalRequest request) {
        return new Animal(null, request.getName(), request.getSpecies(), request.getColor(),
                request.getAge(), request.getGender(), request.getPassword());
    }

    // hàm trả từ Animal ra Response
    public static AnimalResponse toResponse(Animal animal) {
        return new AnimalResponse(animal.getId(), animal.getName(),
                animal.getSpecies(), animal.getColor(), animal.getAge(), animal.getGender());
    }

    //hàm update
    public static void update(Animal animal, AnimalRequest request){
        animal.setName(request.getName());
        animal.setSpecies(request.getSpecies());
        animal.setColor(request.getColor());
        animal.setAge(request.getAge());
        animal.setGender(request.getGender());
        animal.setPassword(request.getPassword());
    }
}
