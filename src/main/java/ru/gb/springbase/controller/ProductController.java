package ru.gb.springbase.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import ru.gb.springbase.model.dtos.CategoryDto;
import ru.gb.springbase.model.dtos.ProductDto;
import ru.gb.springbase.model.entities.Category;
import ru.gb.springbase.model.entities.Product;
import ru.gb.springbase.service.ProductService;


import java.util.Map;
import java.util.stream.Stream;

@RestController
@RequestMapping("api/v1/products")
public class ProductController {

    @Autowired
    private ProductService service;

    @GetMapping
    public Page<ProductDto> findAllProducts(@RequestParam(required = false, defaultValue = Integer.MIN_VALUE + "") Integer costGt,
                                            @RequestParam(required = false, defaultValue = Integer.MAX_VALUE + "") Integer costLt,
                                            @RequestParam(required = false, defaultValue = "") String title,
                                            @RequestParam(required = false, defaultValue = "1") Integer page,
                                            @RequestParam(required = false, defaultValue = "5") Integer pageSize,
                                            @RequestParam Map<String, String> params) {

        return service.findByFilter(page, pageSize, costGt, costLt, title, params);
    }

    @GetMapping(value = "/{id}")
    public ProductDto findProductById(@PathVariable Long id) {
        return service.findById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Product saveNewProduct(@RequestBody ProductDto productDto) {
        productDto.setId(null);
        return service.saveOrUpdate(Product.builder()
                .cost(productDto.getCost())
                .category(Category.builder()
                        .id(productDto.getCategory().getId())
                        .title(productDto.getCategory().getTitle())
                        .build())
                .title(productDto.getTitle()).build());
    }

    @PutMapping
    public Product updateProduct(@RequestBody Product product) {
        return service.saveOrUpdate(product);
    }

    @DeleteMapping("/{id}")
    public void deleteProductById(@PathVariable Long id) {
        service.deleteById(id);
    }



}
