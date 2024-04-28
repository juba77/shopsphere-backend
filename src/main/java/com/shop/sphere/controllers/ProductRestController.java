package com.shop.sphere.controllers;

import com.shop.sphere.api.ProductsApi;
import com.shop.sphere.api.model.ProductDTO;
import com.shop.sphere.dao.ProductRepository;
import com.shop.sphere.mappers.ProductMapper;
import com.shop.sphere.models.Product;
import org.mapstruct.factory.Mappers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
public class ProductRestController implements ProductsApi {

    @Autowired
    private ProductRepository productRepository;

    private final ProductMapper productMapper = Mappers.getMapper(ProductMapper.class);

    @Override
    public ResponseEntity<ProductDTO> getProduct(Long idProduct) {
        Optional<Product> product = productRepository.findById(idProduct);
        return product.map(value -> ResponseEntity.ok(productMapper.productToProductDto(value))).orElseGet(() -> ResponseEntity.noContent().build());
    }

    @Override
    public ResponseEntity<List<ProductDTO>> getProducts() {
        Iterable<Product> products = productRepository.findAll();
        if(!products.iterator().hasNext())
            return ResponseEntity.status(204).build();
        List<ProductDTO> productsDTO = new ArrayList<>();
        for(Product product: products)
            productsDTO.add(productMapper.productToProductDto(product));
        return ResponseEntity.ok(productsDTO);
    }

    @Override
    public ResponseEntity<Void> updateProduct(@Valid ProductDTO productDTO) {
        if(productRepository.findById(productDTO.getId()) != null){
            Product product = productMapper.productDtoToProdcut(productDTO);
            if(productRepository.save(product) != null)
                return ResponseEntity.status(204).build();
        }
        return ResponseEntity.status(400).build();
    }

}
