package ru.gb.springbase.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import ru.gb.springbase.exception.ErrorPageException;
import ru.gb.springbase.exception.NoDataFoundException;
import ru.gb.springbase.model.Product;
import ru.gb.springbase.repository.ProductRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class ProductService {

    @Autowired
    private ProductRepository repository;

    public Product save(Product product) {
        return repository.save(product);
    }

    public Page<Product> findAll(Integer page, Integer pageSize, Map<String, String> params) {
        return repository.findAll(PageRequest.of(page, pageSize, Sort.by(getOrders(params))));
    }

    public Product findById(Long id) {
        return repository.findById(id).orElseThrow(() -> new NoDataFoundException("Product id: " + id));
    }

    public Product update(Product product) {
        return repository.save(product);
    }

    public void deleteById(Long id) {
        repository.deleteById(id);
    }

    public void delete(Product product) {
        repository.delete(product);
    }

    public Page<Product> findByFilter(Integer page, Integer pageSize, Integer costGt, Integer costLt, String title, Map<String, String> params) {
        if (page < 1 || pageSize < 1)
            throw new ErrorPageException(page, pageSize, null);

        List<Sort.Order> orders = getOrders(params);

        Page<Product> result = repository.findProductsByCostBetweenAndTitleContainsIgnoreCase(costGt, costLt, title, PageRequest.of(page-1, pageSize, Sort.by(orders)));

        if (result.isEmpty())
            throw new NoDataFoundException("");

        return result;
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
