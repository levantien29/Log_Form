package com.example.Phone_MapStruct.Service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.example.Phone_MapStruct.Dto.PhoneRequest;
import com.example.Phone_MapStruct.Dto.PhoneResponse;
import com.example.Phone_MapStruct.Exception.BadRequestException;
import com.example.Phone_MapStruct.Exception.ResourceNotFoundException;
import com.example.Phone_MapStruct.Mapper.PhoneMapper;
import com.example.Phone_MapStruct.Model.Phone;
import com.example.Phone_MapStruct.Repository.PhoneRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class PhoneService implements IPhoneService{
    private final PhoneRepository repository;
    private final PhoneMapper mapper;

    @Override
    public List<PhoneResponse> getAll(){
        return mapper.toList(repository.findAll());
    }

    @Override
    public Page<PhoneResponse> getAll(Pageable pageable){
        return repository.findAll(pageable).map(mapper::toResponse);
    }

    @Override
    public PhoneResponse getById(Long id){
        Phone phone = repository.findById(id)
        .orElseThrow(()-> new ResourceNotFoundException("Không tìm thấy Phone có id = " + id));
        return mapper.toResponse(phone);
    }

    @Override
    public PhoneResponse create(PhoneRequest request){
        if(repository.existsByName(request.getName())){
            throw new BadRequestException("Tên đã tồn tại");
        }
        Phone phone = mapper.toEntity(request);
        return mapper.toResponse(repository.save(phone));
    }

    @Override
    public PhoneResponse update(PhoneRequest request, Long id){
        Phone phone = repository.findById(id)
        .orElseThrow(() -> new ResourceNotFoundException("Không tìm thấy Phone có id = " + id));
        if (repository.existsByNameAndIdNot(request.getName(), id)) {
            throw new BadRequestException("Phone đã tồn tại, vui lòng nhập Phone khác");
        }
        mapper.update(phone, request);
        return mapper.toResponse(repository.save(phone));
    }

    @Override
    public void delete(Long id){
        if (!repository.existsById(id)) {
            throw new ResourceNotFoundException("Không tìm thấy phone có id = " + id);
        }
        repository.deleteById(id);
    }

    @Override
    public Page<PhoneResponse> searchByNameAndPrice(String name, double min, double max, Pageable pageable){
        return repository.findByNameContainingIgnoreCaseAndPriceBetween(name, min, max, pageable)
        .map(mapper::toResponse);
    }

    @Override
    public Page<PhoneResponse> filterByBrandAndPrice(String brand, double min, double max, Pageable pageable){
        return repository.findByBrandAndPriceBetween(brand, min, max, pageable)
        .map(mapper::toResponse);
    }
    
    @Override
    public Page<PhoneResponse> getFeatured(Pageable pageable){
        return repository.findByFeaturedTrue(pageable)
        .map(mapper::toResponse);
    }
}
