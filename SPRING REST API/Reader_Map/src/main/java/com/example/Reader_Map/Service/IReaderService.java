package com.example.Reader_Map.Service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.example.Reader_Map.DTO.ReaderRequest;
import com.example.Reader_Map.DTO.ReaderResponse;
import com.example.Reader_Map.DTO.ReaderSearchRequest;

public interface IReaderService {
    public Page<ReaderResponse> getAll(Pageable pageable);

    public ReaderResponse getById(Long id);

    public ReaderResponse create(ReaderRequest request);

    public ReaderResponse update(ReaderRequest request, Long id);

    public void delete(Long id);

    public Page<ReaderResponse> searchReader(ReaderSearchRequest request, Pageable pageable);

}
