package com.ankursinha.springbootrest.service;

import com.ankursinha.springbootrest.entity.Product;
import com.ankursinha.springbootrest.repo.ProductRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {

    @Autowired
    private ProductRepo repo;

    public Product createProduct(Product product) {
        return repo.save(product);
    }

    public List<Product> getAllProducts() {
        return repo.findAll();
    }

    public Product getProductById(Long id) {
        Product product = repo.findById(id).orElse(null);
        if (product == null) {
            throw new RuntimeException("Product not found with id: " + id);
        }
        return product;
    }

    public Product updateProduct(Long id, Product updatedProduct) {
        Product existingProduct = repo.findById(id).orElse(null);
        if (existingProduct == null) {
            throw new RuntimeException("Product not found with id: " + id);
        }
        existingProduct.setName(updatedProduct.getName());
        existingProduct.setPrice(updatedProduct.getPrice());
        return repo.save(existingProduct);
    }

    public void deleteProduct(Long id) {
        Product product = repo.findById(id).orElse(null);
        if (product == null) {
            throw new RuntimeException("Product not found with id: " + id);
        }
        repo.delete(product);
    }

    public List<Product> getTopProducts(int val) {
        return repo.findTopByPriceRange(15, 30, val);
    }

}
