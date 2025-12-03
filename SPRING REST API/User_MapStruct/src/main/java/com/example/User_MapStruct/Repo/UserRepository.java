package com.example.User_MapStruct.Repo;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.example.User_MapStruct.Model.User;

public interface UserRepository extends JpaRepository<User, Long> {
    // Kiểm tra tồn tại theo tên (thêm mới)
    boolean existsByName(String name);

    boolean existsByEmail(String email);

    // Kiểm tra trùng tên nhưng bỏ qua ID hiện tại (cập nhật)
    boolean existsByNameAndIdNot(String name, Long id);

    boolean existsByEmailAndIdNot(String email, Long id);

    //tìm ngươif nổi bật
    Page<User> findByFeaturedTrue(Pageable pageable);

    //tìm theo tên
    Page<User> findByNameContainingIgnoreCase(String name, Pageable pageable);

    //tìm theo email
    Page<User> findByEmailContainingIgnoreCase(String email, Pageable pageable);
}
