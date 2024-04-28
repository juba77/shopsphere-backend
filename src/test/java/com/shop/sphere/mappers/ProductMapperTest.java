package com.shop.sphere.mappers;

import com.shop.sphere.api.model.ProductDTO;
import com.shop.sphere.models.Product;
import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class ProductMapperTest {

    private final ProductMapper productMapper = Mappers.getMapper(ProductMapper.class);

    @Test
    void productToProductDto(){
        Product product = new Product();
        product.setDescription("description test");
        product.setId(1L);
        product.setPhoto("https://fefef.fzfzfz.com");
        product.setPrice(22.5);
        product.setTitle("Titre");
        product.setQuantity(85);

        ProductDTO productDTO = productMapper.productToProductDto(product);

        assertEquals(product.getId(), productDTO.getId());
        assertEquals(product.getDescription(), productDTO.getDescription());
        assertEquals(product.getPhoto(), productDTO.getPhoto());
        assertEquals(product.getPrice(), productDTO.getPrice());
        assertEquals(product.getTitle(), productDTO.getTitle());
        assertEquals(product.getQuantity(), productDTO.getQuantity());
    }

    @Test
    void productDtoToProduct(){
        ProductDTO productDTO = new ProductDTO();
        productDTO.setDescription("description test");
        productDTO.setId(1L);
        productDTO.setPhoto("https://fefef.fzfzfz.com");
        productDTO.setPrice(22.5);
        productDTO.setTitle("Titre");
        productDTO.setQuantity(85);

        Product product = productMapper.productDtoToProdcut(productDTO);

        assertEquals(productDTO.getId(), product.getId());
        assertEquals(productDTO.getDescription(), product.getDescription());
        assertEquals(productDTO.getPhoto(), product.getPhoto());
        assertEquals(productDTO.getPrice(), product.getPrice());
        assertEquals(productDTO.getTitle(), product.getTitle());
        assertEquals(productDTO.getQuantity(), product.getQuantity());
    }

}
