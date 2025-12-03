package com.example.Blog_Service.Service;

import com.example.Blog_Service.Dto.BlogRequest;
import com.example.Blog_Service.Dto.BlogResponse;
import com.example.Blog_Service.Exception.BadRequestException;
import com.example.Blog_Service.Exception.ResourceNotFoundException;
import com.example.Blog_Service.Mapper.BlogMapper;
import com.example.Blog_Service.Model.Blog;
import com.example.Blog_Service.Repository.BlogRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@Service
public class BlogService implements IBlogService {
    private final BlogRepository repository;
    private final BlogMapper mapper;

    @Override
    public List<BlogResponse> getAll() {
        return mapper.toList(repository.findAll());
    }

    @Override
    public Page<BlogResponse> getAll(Pageable pageable) {
        return repository.findAll(pageable).map(mapper::toResponse);
    }

    @Override
    public BlogResponse getById(Long id) {
        Blog blog = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Không tìm thấy Blog có id = " + id));
        return mapper.toResponse(blog);
    }

    @Override
    public BlogResponse create(BlogRequest request) {
        if (repository.existsByTitle(request.getTitle())) {
            throw new ResourceNotFoundException("Tên đã tồn tại");
        }
        Blog blog = mapper.toEntity(request);
        repository.save(blog);
        return mapper.toResponse(blog);
    }

    @Override
    public BlogResponse update(BlogRequest request, Long id) {
        Blog blog = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Không tìm thấy Blog có id = " + id));
        if (repository.existsByTitleAndIdNot(request.getTitle(), id)) {
            throw new ResourceNotFoundException("Blog đã tồn tại !");
        }
        mapper.toUpdate(blog, request);
        return mapper.toResponse(blog);
    }

    @Override
    public void delete(Long id) {
        if (!repository.existsById(id)) {
            throw new ResourceNotFoundException("Blog không tồn tại");
        }
        repository.deleteById(id);
    }

    @Override
    public Page<BlogResponse> findByTitle(String title, Pageable pageable) {
        Page<Blog> page = repository.findByTitleIgnoreCase(title, pageable);
        if (page.isEmpty()) {
            throw new BadRequestException("Title không tồn tại");
        }
        return page.map(mapper::toResponse);
    }

    @Override
    public Page<BlogResponse> findByAuthor(String author, Pageable pageable) {
        Page<Blog> page = repository.findByAuthorIgnoreCase(author, pageable);
        if (page.isEmpty()) {
            throw new BadRequestException("Tác giả không tồn tại");
        }
        return page.map(mapper::toResponse);
    }

    @Override
    public Page<BlogResponse> findByCatetory(String catetory, Pageable pageable) {
        Page<Blog> page = repository.findByCatetoryIgnoreCase(catetory, pageable);
        if (page.isEmpty()) {
            throw new BadRequestException("Thể loại không tồn tại");
        }
        return page.map(mapper::toResponse);
    }

    @Override
    public Page<BlogResponse> findByFeatued(Pageable pageable) {
        return repository.findByFeaturedTrue(pageable).map(mapper::toResponse);
    }

    @Override
    public Page<BlogResponse> findByPrice(double min, double max, Pageable pageable) {
        Page<Blog> page = repository.findByPriceBetween(min, max, pageable);
        if (page.isEmpty()) {
            throw new BadRequestException("Không có  Blog trong khoảng giá từ : " + min + "-" + max);
        }
        return page.map(mapper::toResponse);
    }
}
