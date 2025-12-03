package com.example.Product_Mapstruct.Mappper;

import java.util.List;

import org.mapstruct.BeanMapping;
import org.mapstruct.MapperConfig;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

import com.example.Product_Mapstruct.Dto.ProductRequest;
import com.example.Product_Mapstruct.Dto.ProductResponse;
import com.example.Product_Mapstruct.Model.Product;

@MapperConfig(componentModel = "spring")
public interface ProductMapper {

    // thêm
    public Product toProduct(ProductRequest request);

    // sửa
    // nếu trường nào null thì bỏ qua không ghi đè
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    // MappingTarget chỉ định mapstruct ghi đè chứ khoong tạo mới
    public void toUpdate(@MappingTarget Product product, ProductRequest request);

    // toResponse
    public ProductResponse toResponse(Product product);

    // list
    public List<ProductResponse> toList(List<Product> products);
}
