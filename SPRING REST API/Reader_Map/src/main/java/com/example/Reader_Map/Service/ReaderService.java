package com.example.Reader_Map.Service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.example.Reader_Map.DTO.ReaderRequest;
import com.example.Reader_Map.DTO.ReaderResponse;
import com.example.Reader_Map.DTO.ReaderSearchRequest;
import com.example.Reader_Map.Exception.BadRequestException;
import com.example.Reader_Map.Exception.ResourceNotFoundException;
import com.example.Reader_Map.Mapper.ReaderMapper;
import com.example.Reader_Map.Model.Reader;
import com.example.Reader_Map.Repository.ReaderRepository;
import com.example.Reader_Map.Specification.ReaderSpecification;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class ReaderService implements IReaderService {
    private final ReaderRepository repository;
    private final ReaderMapper mapper;

    @Override
    public Page<ReaderResponse> getAll(Pageable pageable) {
        return repository.findAll(pageable).map(mapper::toResponse);
    }

    @Override
    public ReaderResponse getById(Long id) {
        Reader reader = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("id", "Không tìm thấy độc giả có id = " + id));
        return mapper.toResponse(reader);
    }

    @Override
    public ReaderResponse create(ReaderRequest request) {
        if (repository.existsByEmail(request.getEmail())) {
            throw new BadRequestException("email", "Email : " + request.getEmail() + " đã tồn tại");
        }
        if (repository.existsByName(request.getName())) {
            throw new BadRequestException("name", "Tên : " + request.getName() + " đã tồn tại");
        }
        Reader reader = mapper.toEntity(request);
        return mapper.toResponse(repository.save(reader));
    }

    @Override
    public ReaderResponse update(ReaderRequest request, Long id) {
        Reader reader = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("id", "Không tìm thấy độc giả có id = " + id));
        if (repository.existsByNameAndIdNot(request.getName(), id)) {
            throw new BadRequestException("name", "Tên độc giả : " + request.getName() + " đã tồn tại, vui lòng thêm tên khác ");
        }
        if (repository.existsByEmailAndIdNot(request.getEmail(), id)) {
            throw new BadRequestException("email", request.getEmail() + " Đã tồn tại, vui lòng thêm email khác");
        }
        mapper.toUpdate(reader, request);
        return mapper.toResponse(repository.save(reader));
    }

    @Override
    public void delete(Long id) {
        if (!repository.existsById(id)) {
            throw new ResourceNotFoundException("id", "Không tìm thấy độc giả có id : " + id);
        }
        repository.deleteById(id);
    }

    @Override
    public Page<ReaderResponse> searchReader(ReaderSearchRequest request, Pageable pageable) {
        Page<Reader> page = repository.findAll(ReaderSpecification.build(request), pageable);
        return page.map(mapper::toResponse);
    }
}
