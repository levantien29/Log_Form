package com.example.Phone_MapStruct.Repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.example.Phone_MapStruct.Model.Phone;

public interface PhoneRepository extends JpaRepository<Phone, Long> {

    //Tìm kiếm theo tên chứa (không phân biệt hoa thường) và khoảng giá
    Page<Phone> findByNameContainingIgnoreCaseAndPriceBetween(String name, double min, double max, Pageable pageable);

    // Lọc theo hãng và khoảng giá
    Page<Phone> findByBrandAndPriceBetween(String brand, double min, double max, Pageable pageable);

    // 🌟 Lấy điện thoại nổi bật
    Page<Phone> findByFeaturedTrue(Pageable pageable);

    //Kiểm tra tồn tại theo tên (thêm mới)
    boolean existsByName(String name);

    // Kiểm tra trùng tên nhưng bỏ qua ID hiện tại (cập nhật)
    boolean existsByNameAndIdNot(String name, Long id);
}
