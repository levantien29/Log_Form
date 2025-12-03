package com.example.Blog_Service.Repository;

import com.example.Blog_Service.Model.Blog;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BlogRepository extends JpaRepository<Blog, Long> {
    boolean existsByTitle(String title);
    boolean existsByTitleAndIdNot(String title, Long id);

    Page<Blog> findByTitleIgnoreCase(String title, Pageable pageable);
    Page<Blog> findByAuthorIgnoreCase(String author, Pageable pageable);
    Page<Blog> findByAddressIgnoreCase(String address, Pageable pageable);
    Page<Blog> findByFeaturedTrue(Pageable pageable);
    Page<Blog> findByPriceBetween(Double minPrice, Double maxPrice, Pageable pageable);}
