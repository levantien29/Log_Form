package com.example.Car_Map.specification;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.jpa.domain.Specification;

import com.example.Car_Map.Dto.CarSearchRequest;
import com.example.Car_Map.Model.Car;

import jakarta.persistence.criteria.Predicate;

public class CarSpecification {
    public static Specification<Car> build(CarSearchRequest request) {
        // root : Đại diện cho thực thể (entity Car) trong truy vấn.
        // cb : Là CriteriaBuilder để tạo điều kiện lọc (Predicate)
        return (root, query, cb) -> {
            // danh sách điều kiện lọc động
            List<Predicate> predicates = new ArrayList<>();
            if (request.getName() != null && !request.getName().isBlank()) {
                //cb.lower chuyển giá trị cột name về chữ thường
                //(root.get("name")) truy cập cột name trong Car
                //request.getName().toLowerCase() chuyển name người dùng nhập sang chữ thường
                //% % chứa chuỗi
                //predicates.add thêm điêu kiện vào ds 
                predicates.add(cb.like(cb.lower(root.get("name")), "%" + request.getName().toLowerCase() + "%"));
            }
            if (request.getCode() != null && !request.getCode().isBlank()) {
                predicates.add(cb.like(cb.lower(root.get("code")), "%" + request.getCode().toLowerCase() + "%"));
            }
            if (request.getColor() != null && !request.getColor().isBlank()) {
                predicates.add(cb.like(cb.lower(root.get("color")), "%" + request.getColor().toLowerCase() + "%"));
            }
            if (request.getModel() != null && !request.getModel().isBlank()) {
                predicates.add(cb.like(cb.lower(root.get("model")), "%" + request.getModel().toLowerCase() + "%"));
            }
            if (request.getBrand() != null && !request.getBrand().isBlank()) {
                predicates.add(cb.like(cb.lower(root.get("brand")), "%" + request.getBrand().toLowerCase() + "%"));
            }
            if (request.getMin() != null) {
                predicates.add(cb.greaterThanOrEqualTo(root.get("price"), request.getMin()));
            }
            if (request.getMax() != null) {
                predicates.add(cb.lessThanOrEqualTo(root.get("price"), request.getMax()));
            }
            // kết hợp các điều kiện tạo thành biểu thức tổng hợp
            //cb.and kết hợp các đk kết nối = AND, không nhận list lên phải chuyển sang mảng
            return cb.and(predicates.toArray(new Predicate[0]));
        };
    }
}
