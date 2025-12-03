package com.example.Order_Management.Service;

import java.util.List;

import com.example.Order_Management.Dto.OderResponse;
import com.example.Order_Management.Dto.OrderRequest;

public interface IOderService {
    List<OderResponse> getAll();
    public OderResponse getById(Long id);
    public OderResponse create(OrderRequest request);
    public OderResponse update(OrderRequest request, Long id);
    public void delete(Long id);
}
