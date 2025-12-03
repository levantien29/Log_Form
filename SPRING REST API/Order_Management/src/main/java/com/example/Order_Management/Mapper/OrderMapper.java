package com.example.Order_Management.Mapper;

import com.example.Order_Management.Dto.OderResponse;
import com.example.Order_Management.Dto.OrderRequest;
import com.example.Order_Management.Model.Order;

public class OrderMapper {
    // thêm
    public static Order toOrder(OrderRequest request) {
        return new Order(null, request.getName(), request.getEmail(), request.getTotal(), request.getQuantity(),
                request.getPassword());
    }

    // sửa
    public static void toUpdate(OrderRequest request, Order order) {
        order.setName(request.getName());
        order.setEmail(request.getEmail());
        order.setTotal(request.getTotal());
        order.setQuantity(request.getQuantity());
        order.setPassword(request.getPassword());
    }

    // toResponse
    public static OderResponse toResponse(Order order) {
        return new OderResponse(order.getId(), order.getName(), order.getEmail(), order.getTotal(),
                order.getQuantity());
    }
}
