package com.allrankingsport.backend.services;

import com.allrankingsport.backend.dto.ProductDto;
import com.allrankingsport.backend.entities.Product;
import com.allrankingsport.backend.repositories.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ProductService {

    private final ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public List<ProductDto> getAllProducts() {
        return productRepository.findAll().stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    public Optional<ProductDto> getProductById(Long id) {
        return productRepository.findById(id).map(this::convertToDto);
    }

    public ProductDto createProduct(ProductDto productDto) {
        Product product = convertToEntity(productDto);
        return convertToDto(productRepository.save(product));
    }

    public Optional<ProductDto> updateProduct(Long id, ProductDto productDto) {
        return productRepository.findById(id).map(existingProduct -> {
            existingProduct.setName(productDto.getName());
            existingProduct.setDescription(productDto.getDescription());
            existingProduct.setPrice(productDto.getPrice());
            existingProduct.setImageUrl(productDto.getImageUrl());
            return convertToDto(productRepository.save(existingProduct));
        });
    }

    public boolean deleteProduct(Long id) {
        if (productRepository.existsById(id)) {
            productRepository.deleteById(id);
            return true;
        }
        return false;
    }

    private ProductDto convertToDto(Product product) {
        return new ProductDto(product.getId(), product.getName(), product.getDescription(), product.getPrice(), product.getImageUrl());
    }

    private Product convertToEntity(ProductDto productDto) {
        return new Product(productDto.getId(), productDto.getName(), productDto.getDescription(), productDto.getPrice(), productDto.getImageUrl());
    }
}