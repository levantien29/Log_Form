package com.example.Reader_Map.Specification;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.jpa.domain.Specification;

import com.example.Reader_Map.DTO.ReaderSearchRequest;
import com.example.Reader_Map.Model.Reader;

import jakarta.persistence.criteria.Predicate;

public class ReaderSpecification {
    public static Specification<Reader> build(ReaderSearchRequest request) {
        return (root, query, cb) -> {
            List<Predicate> predicates = new ArrayList<>();
            if (request.getName() != null && !request.getName().isBlank()) {
                predicates.add(cb.like(cb.lower(root.get("name")), "%" + request.getName().toLowerCase() + "%"));
            }
            if (request.getEmail() != null && !request.getEmail().isBlank()) {
                predicates.add(cb.like(cb.lower(root.get("email")), "%" + request.getEmail().toLowerCase() + "%"));
            }
            if (request.getAddress() != null && !request.getAddress().isBlank()) {
                predicates.add(cb.like(cb.lower(root.get("address")), "%" + request.getAddress().toLowerCase() + "%"));
            }
            if (request.getPhone() != null) {
                predicates.add(cb.like(root.get("phone"), "%" + request.getPhone() + "%"));
            }
            if (request.getRegisteredDate() != null) {
                predicates.add(cb.equal(root.get("registeredDate"), request.getRegisteredDate()));
            }
            if (request.getStatus() != null && !request.getStatus().isBlank()) {
                predicates.add(cb.like(cb.lower(root.get("status")), "%" + request.getStatus().toLowerCase() + "%"));
            }
            return cb.and(predicates.toArray(new Predicate[0]));
        };
    }
}
