package ru.gb.springbase.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;
import ru.gb.springbase.model.dtos.ProductDto;
import ru.gb.springbase.model.entities.Category;
import ru.gb.springbase.model.entities.Product;
import ru.gb.springbase.repository.specifications.ProductSpecifications;
import ru.gb.springbase.service.ProductService;


@RestController
@RequestMapping("api/v1/products")
public class ProductController {

    @Autowired
    private ProductService service;

    @GetMapping
    public Page<ProductDto> findAllProducts(@RequestParam(required = false, defaultValue = "1") Integer page,
                                            @RequestParam(required = false, defaultValue = "5") Integer pageSize,
                                            @RequestParam MultiValueMap<String, String> params) {
        if (page < 1) page = 1;

        return service.findAll(ProductSpecifications.build(params), page, pageSize);
    }

    @GetMapping(value = "/{id}")
    public ProductDto findProductById(@PathVariable Long id) {
        return service.findById(id);
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ProductDto saveNewProduct(@RequestBody ProductDto productDto) {
        productDto.setId(null);
        return service.saveOrUpdate(Product.builder()
                .cost(productDto.getCost())
                .category(Category.builder()
                        .id(productDto.getCategory().getId())
                        .title(productDto.getCategory().getTitle())
                        .build())
                .title(productDto.getTitle()).build());
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PutMapping
    public ProductDto updateProduct(@RequestBody Product product) {
        return service.saveOrUpdate(product);
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @DeleteMapping("/{id}")
    public void deleteProductById(@PathVariable Long id) {
        service.deleteById(id);
    }



}
