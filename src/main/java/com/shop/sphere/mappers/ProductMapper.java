package com.shop.sphere.mappers;

import com.shop.sphere.api.model.ProductDTO;
import com.shop.sphere.models.Product;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ProductMapper {

    ProductDTO productToProductDto(Product product);

    Product productDtoToProdcut(ProductDTO productDto);

}
