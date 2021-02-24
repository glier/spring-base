package ru.gb.springbase.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.gb.springbase.model.Product;
import ru.gb.springbase.service.ProductService;

@Controller
@RequestMapping("/product")
public class ProductController {

    @Autowired
    private ProductService service;

    @GetMapping(value = "/all")
    public String allProducts(Model model) {
        model.addAttribute("products", service.getProducts());
        model.addAttribute("totalProducts", service.totalProducts());
        return "products";
    }

    @GetMapping(value = "/{id}")
    public String allProducts(@PathVariable Long id, Model model) {
        model.addAttribute("product", service.getProduct(id));
        return "product";
    }

    @PostMapping("/add")
    public String addNewProduct(@ModelAttribute Product product) {
        service.add(product);
        return "redirect:/product/all";
    }

    @GetMapping("/remove/{id}")
    public String deleteProductById(@PathVariable Long id) {
        service.deleteById(id);
        return "redirect:/product/all";
    }
}
