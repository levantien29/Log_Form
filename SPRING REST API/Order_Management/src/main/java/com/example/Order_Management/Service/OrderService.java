package com.example.Order_Management.Service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.example.Order_Management.Dto.OderResponse;
import com.example.Order_Management.Dto.OrderRequest;
import com.example.Order_Management.Exception.BadRequestException;
import com.example.Order_Management.Exception.ResourceNotFoundException;
import com.example.Order_Management.Mapper.OrderMapper;
import com.example.Order_Management.Model.Order;
import com.example.Order_Management.Repository.OrderRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class OrderService implements IOderService {
    private final OrderRepository repository;

    // lay danh sach
    public List<OderResponse> getAll() {
        return repository.findAll()
                .stream()
                .map(OrderMapper::toResponse)
                .collect(Collectors.toList());
    }

    // tìm kiếm theo id
    public OderResponse getById(Long id) {
        Order order = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Không tìm thấy đơn hàng có id = " + id));
        return OrderMapper.toResponse(order);
    }

    // thêm đơn hàng
    public OderResponse create(OrderRequest request) {
        if (repository.existsByName(request.getName())) {
            throw new BadRequestException("Đơn hàng đã tồn tại, Vui lòng thêm đơn hàng mới");
        }
        Order order = OrderMapper.toOrder(request);
        return OrderMapper.toResponse(repository.save(order));
    }

    // update
    public OderResponse update(OrderRequest request, Long id) {
        Order order = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Không tìm thấy đơn hàng có id = " + id));
        if (repository.existsByNameAndIdNot(request.getName(), id)) {
            throw new BadRequestException("Đơn hàng đã tồn tại, Vui lòng thêm đơn hàng mới");
        }
        if (repository.existsByEmailAndIdNot(request.getEmail(), id)) {
            throw new BadRequestException("Email đã tồn tại, Vui lòng thêm email mới");
        }
        OrderMapper.toUpdate(request, order);
        return OrderMapper.toResponse(repository.save(order));
    }

    // delete
    public void delete(Long id) {
        if (!repository.existsById(id)) {
            throw new ResourceNotFoundException("Không có đơn hàng có id = " + id);
        }
        repository.deleteById(id);
    }
}
