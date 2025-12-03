package com.example.Phone_MapStruct.Service;
import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.example.Phone_MapStruct.Dto.PhoneRequest;
import com.example.Phone_MapStruct.Dto.PhoneResponse;

public interface IPhoneService {
    public List<PhoneResponse> getAll();
    public Page<PhoneResponse> getAll(Pageable pageable);
    public PhoneResponse getById(Long id);
    public PhoneResponse create(PhoneRequest request);
    public PhoneResponse update(PhoneRequest request, Long id);
    public void  delete(Long id);
    public Page<PhoneResponse> searchByNameAndPrice(String name, double min, double max, Pageable pageable);
    public Page<PhoneResponse> filterByBrandAndPrice(String brand, double min, double max, Pageable pageable);
    public Page<PhoneResponse> getFeatured(Pageable pageable);
}
