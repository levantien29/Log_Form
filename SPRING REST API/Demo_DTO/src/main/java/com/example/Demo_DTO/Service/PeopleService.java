package com.example.Demo_DTO.Service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.example.Demo_DTO.DTO.PeopleRepose;
import com.example.Demo_DTO.DTO.PeopleRequest;
import com.example.Demo_DTO.Model.People;
import com.example.Demo_DTO.Repository.PeopleRepository;

import lombok.RequiredArgsConstructor;
@Service
@RequiredArgsConstructor // DÙNG ĐỂ KHÔNG CẦN VIẾT CONTRUCSTOR THỦ CÔNG
public class PeopleService implements IPeopleService {
    private final PeopleRepository peopleRepository;

    // public PeopleService(PeopleRepository peopleRepository) {
    //     this.peopleRepository = peopleRepository;
    // }

    private PeopleRepose convertToReponse(People people) {
        return new PeopleRepose(people.getId(), people.getName(), people.getEmail(), people.getAddress());
    }

    @Override
    public List<PeopleRepose> getAll() {
        return peopleRepository.findAll()
                /*
                 * Lấy tất cả People từ database → chuyển thành Stream →
                 * dùng map() để chuyển từng People thành PeopleRepose →
                 * thu lại kết quả thành List<PeopleRepose>.
                 */
                .stream()
                .map(this::convertToReponse)
                .collect(Collectors.toList());
    }

    @Override
    public PeopleRepose getByid(Long id) {
        People people = peopleRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Nooot found"));
        return convertToReponse(people);
    }

    @Override
    public PeopleRepose update(Long id, PeopleRequest request) {
        People people = peopleRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Noot found"));
        people.setName(request.getName());
        people.setEmail(request.getEmail());
        people.setAddress(request.getAddress());
        return convertToReponse(peopleRepository.save(people));
    }

    @Override
    public void delete(Long id) {
        peopleRepository.deleteById(id);
    }

    @Override
    public PeopleRepose create(PeopleRequest request) {
        People people = new People(null, request.getName(), request.getEmail(), request.getAddress(),
                request.getPassword());
        return convertToReponse(peopleRepository.save(people));
    }
}
