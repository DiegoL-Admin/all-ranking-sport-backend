package com.allrankingsport.backend.services;

import com.allrankingsport.backend.dto.ProductDto;
import com.allrankingsport.backend.entities.Product;
import com.allrankingsport.backend.repositories.ProductRepository;
import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProductService {

    private final ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @PostConstruct
    public void init() {
        // Initialize with mock data if the repository is empty
        if (productRepository.count() == 0) {
            List<Product> mockProducts = Arrays.asList(
                    new Product(null, "Basketball", "A high-quality basketball for indoor and outdoor use.", 29.99, "assets/basketball.jpg"),
                    new Product(null, "Running Shoes", "Lightweight and comfortable running shoes.", 89.99, "assets/runningshoes.jpg"),
                    new Product(null, "Soccer Ball", "Durable soccer ball for all weather conditions.", 24.99, "assets/soccerball.jpg"),
                    new Product(null, "Yoga Mat", "Eco-friendly and non-slip yoga mat.", 39.99, "assets/yoga.jpg"),
                    new Product(null, "Dumbbells Set", "Set of 2 dumbbells, 5kg each.", 49.99, "assets/dumbbells.jpg"),
                    new Product(null, "Tennis Racket", "Professional tennis racket for all skill levels.", 79.99, "assets/racket.jpg")
            );
            productRepository.saveAll(mockProducts);
        }
    }

    public List<ProductDto> getAllProducts() {
        return productRepository.findAll().stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    private ProductDto convertToDto(Product product) {
        return new ProductDto(product.getId(), product.getName(), product.getDescription(), product.getPrice(), product.getImageUrl());
    }
}
