package ru.gb.springbase.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import ru.gb.springbase.exception.NoDataFoundException;
import ru.gb.springbase.model.dtos.ProductDto;
import ru.gb.springbase.model.entities.Product;
import ru.gb.springbase.repository.ProductRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class ProductService {

    @Autowired
    private ProductRepository repository;

    public Product saveOrUpdate(Product product) {
        return repository.save(product);
    }

    public Page<ProductDto> findAll(Specification<Product> spec, int page, int pageSize) {
        if(page < 0)
            throw new RuntimeException();
        return repository.findAll(spec, PageRequest.of(page - 1, pageSize)).map(ProductDto::new);
    }

    public ProductDto findById(Long id) {
        return repository.findById(id).map(ProductDto::new).orElseThrow(() -> new NoDataFoundException("Product id: " + id));
    }

    public void deleteById(Long id) {
        repository.deleteById(id);
    }

    private List<Sort.Order> getOrders(Map<String, String> params) {
        List<Sort.Order> orders = new ArrayList<>();

        params.forEach((key, value) -> {
            switch (key) {
                case "orderCost":
                    orders.add(new Sort.Order(Sort.Direction.fromString(value), "cost"));
                    break;
                case "orderTitle":
                    orders.add(new Sort.Order(Sort.Direction.fromString(value), "title"));
                    break;
            }
        });
        return orders;
    }

}
