package ru.gb.springbase.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;
import ru.gb.springbase.model.Product;
import ru.gb.springbase.service.ProductService;


import java.util.Map;

@RestController
@RequestMapping("/product")
public class ProductController {

    @Autowired
    private ProductService service;

    @GetMapping(value = "/all")
    public Page<Product> allProducts(@RequestParam(required = false, defaultValue = "1") Integer page,
                                     @RequestParam(required = false, defaultValue = "5") Integer pageSize,
                                     @RequestParam Map<String, String> params) {
        return service.findAll(page, pageSize, params);
    }

    @GetMapping(value = "/{id}")
    public Product getProductById(@PathVariable Long id) {
        return service.findById(id);
    }

    @PostMapping("/add")
    public Product addNewProduct(@RequestBody Product product) {
        return service.save(product);
    }

    @PutMapping("/update")
    public Product updateProduct(@RequestBody Product product) {
        return service.update(product);
    }

    @DeleteMapping("/remove/{id}")
    public void deleteProductById(@PathVariable Long id) {
        service.deleteById(id);
    }

    @DeleteMapping("/remove")
    public void deleteProduct(@RequestBody Product product) {
        service.delete(product);
    }

    @GetMapping
    public Page<Product> getProductByFilter(@RequestParam(required = false, defaultValue = Integer.MIN_VALUE + "") Integer costGt,
                                            @RequestParam(required = false, defaultValue = Integer.MAX_VALUE + "") Integer costLt,
                                            @RequestParam(required = false, defaultValue = "") String title,
                                            @RequestParam(required = false, defaultValue = "1") Integer page,
                                            @RequestParam(required = false, defaultValue = "5") Integer pageSize,
                                            @RequestParam Map<String, String> params) {

        return service.findByFilter(page, pageSize, costGt, costLt, title, params);
    }

}
