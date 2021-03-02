package ru.gb.springbase.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import ru.gb.springbase.exception.NoDataFoundException;
import ru.gb.springbase.model.Product;
import ru.gb.springbase.repository.ProductRepository;

import java.util.List;

@Service
public class ProductService {

    @Autowired
    private ProductRepository repository;

    public Product add(Product product) {
        return repository.save(product);
    }

    public Product getProduct(Long id) {
        return repository.findById(id).orElseThrow(() -> new NoDataFoundException("Product id: " + id));
    }

    public List<Product> getProducts() {
        return repository.findAll();
    }

    public void update(Product product) {
        repository.save(product);
    }

    public void deleteById(Long id) {
        repository.deleteById(id);
    }

    public int totalProducts() {
        return repository.findAll().size();
    }

    public double averageCost() {
        return repository.findAll().stream()
                .mapToDouble(Product::getCost)
                .average()
                .orElse(Double.NaN);
    }

}
