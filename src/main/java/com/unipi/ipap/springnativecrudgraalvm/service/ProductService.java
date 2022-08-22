package com.unipi.ipap.springnativecrudgraalvm.service;

import com.unipi.ipap.springnativecrudgraalvm.entity.Product;
import com.unipi.ipap.springnativecrudgraalvm.repository.ProductRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class ProductService {

    private final ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public Product saveProduct(Product product) {
        return productRepository.save(product);
    }

    public List<Product> saveProductsFromDto(List<Product> products) {
        return productRepository.saveAll(products);
    }

    public List<Product> saveProducts(List<Product> products) {
        return productRepository.saveAll(products);
    }

    public Product addProduct(Product product) {
        return productRepository.save(product);
    }

    public Optional<Product> getProductById(UUID uuid) {
        return productRepository.findById(uuid);
    }

    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    @Transactional
    public Product modifyProduct(UUID id, Product product) {
        return productRepository.findById(id).map(prd -> {
            prd.setDescription(product.getDescription());
            prd.setQuantity(product.getQuantity());
            prd.setPrice(product.getPrice());
            return productRepository.save(prd);
        }).orElseThrow(EntityNotFoundException::new);
    }

    @Transactional
    public void deleteProductById(UUID uuid) {
        productRepository.deleteById(uuid);
    }
}
