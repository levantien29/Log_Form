
import com.example.Blog_Service.Dto.BlogRequest;
import com.example.Blog_Service.Dto.BlogResponse;
import com.example.Blog_Service.Model.Blog;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

import java.util.List;
@Mapper(componentModel = "spring")
public interface BlogMapper {
    //thêm
    public Blog toEntity(BlogRequest request);
    //list
    public List<BlogResponse> toList(List<Blog> blogs);

    //toResponse
    public BlogResponse toResponse(Blog blog);

    //update
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    public void toUpdate(@MappingTarget Blog blog, BlogRequest request);
}
