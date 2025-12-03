package com.example.Blog_Service.Service;

import com.example.Blog_Service.Dto.BlogRequest;
import com.example.Blog_Service.Dto.BlogResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface IBlogService {
    public List<BlogResponse> getAll();

    public Page<BlogResponse> getAll(Pageable pageable);

    public BlogResponse getById(Long id);

    public BlogResponse create(BlogRequest request);

    public BlogResponse update(BlogRequest request, Long id);

    public void delete(Long id);

    public Page<BlogResponse> findByTitle(String title, Pageable pageable);

    public Page<BlogResponse> findByAuthor(String author, Pageable pageable);

    public Page<BlogResponse> findByCatetory(String catetory, Pageable pageable);

    public Page<BlogResponse> findByFeatued(Pageable pageable);

    public Page<BlogResponse> findByPrice(double min, double max, Pageable pageable);

}
