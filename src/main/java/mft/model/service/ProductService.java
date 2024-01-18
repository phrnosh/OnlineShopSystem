package mft.model.service;

import lombok.extern.log4j.Log4j;
import mft.model.entity.Products;
import mft.model.repository.ProductRepository;

import java.util.List;

@Log4j
public class ProductService {

    private static ProductService service = new ProductService();

    private ProductService() {
    }

    public static ProductService getService() {
        return service;
    }

    public Products save(Products products) throws Exception {
        try (ProductRepository productRepository = new ProductRepository()) {
            return productRepository.save(products);
        }
    }

    public Products edit(Products products) throws Exception {
        try (ProductRepository productRepository = new ProductRepository()) {
            return productRepository.edit(products);
        }
    }

    public Products remove(int id) throws Exception {
        try (ProductRepository productRepository = new ProductRepository()) {
            Products products = productRepository.findById(id);
            if (products != null) {
                productRepository.remove(id);
                return products;
            } else {
                return null;
            }
        }
    }

    public List<Products> findAll() throws Exception {
        try (ProductRepository productRepository = new ProductRepository()) {
            return productRepository.findAll();
        }
    }

    public List<Products> findByAll(String searchText) throws Exception {
        try (ProductRepository productRepository = new ProductRepository()) {
            return productRepository.findByAll(searchText);
        }
    }

    public Products findById(int id) throws Exception {
        try (ProductRepository productRepository = new ProductRepository()) {
            return productRepository.findById(id);
        }
    }
}
