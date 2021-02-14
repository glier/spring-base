package ru.gb.springbase.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.gb.springbase.model.Product;
import ru.gb.springbase.repository.ProductRepository;

import java.util.List;

@Component
public class ProductService {

    @Autowired
    private ProductRepository repository;

    public Product add(Product product) {
        return repository.add(product);
    }

    public Product getProduct(int id) {
        return repository.get(id);
    }

    public List<Product> getProducts() {
        return repository.getProducts();
    }

    public void update(Product product) {
        repository.update(product);
    }

    public void delete(int id) {
        repository.delete(id);
    }

    public int totalProducts() {
        return repository.getProducts().size();
    }

    public double averageCost() {
        return repository.getProducts().stream()
                .mapToDouble(Product::getCost)
                .average()
                .orElse(Double.NaN);
    }

}
