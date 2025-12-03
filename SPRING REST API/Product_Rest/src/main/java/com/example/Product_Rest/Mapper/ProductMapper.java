package com.example.Product_Rest.Mapper;

import com.example.Product_Rest.Dto.ProductRequest;
import com.example.Product_Rest.Dto.ProductResponse;
import com.example.Product_Rest.Model.Product;

public class ProductMapper {
    // chuyeenr tuw resquest sang entity 
    //HÀM CREATE
    public static Product toProduct(ProductRequest request) {
        return new Product(null, request.getName(), request.getPrice(), request.getQuantity(), request.getAtomic());
    }

    // update
    public static void toUpdate(Product product, ProductRequest request) {
        product.setName(request.getName());
        product.setPrice(request.getPrice());
        product.setQuantity(request.getQuantity());
        product.setAtomic(request.getAtomic());
    }

    // toResponse
    public static ProductResponse toResponse(Product product){
        return new ProductResponse(product.getId(), product.getName(), product.getPrice(), product.getQuantity());
    }
}
