package ru.gb.springbase.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.gb.springbase.model.CartElement;
import ru.gb.springbase.model.dtos.ProductDto;
import ru.gb.springbase.service.ProductService;

import java.util.*;

@RestController
@RequestMapping("/api/v1/cart")
@RequiredArgsConstructor
public class CartController {

    private final ProductService productService;

    private final List<CartElement> cart = new ArrayList<>();

    @GetMapping("/add/{id}")
    public void addProductToCart(@PathVariable Long id) {
        ProductDto product = productService.findById(id);

        cart.stream()
                .filter(cartElement -> cartElement.product.equals(product))
                .findFirst()
                .ifPresentOrElse(el -> el.count++, () -> cart.add(new CartElement(product, 1)));
    }

    @GetMapping("/remove/{id}")
    public void removeProductFromCart(@PathVariable Long id) {
        ProductDto product = productService.findById(id);

        cart.stream()
                .filter(cartElement -> cartElement.product.equals(product))
                .findFirst()
                .ifPresent(el -> el.count--);
        cart.removeIf(el -> el.count < 1);
    }

    @GetMapping
    public List<CartElement> getCart() {
        return cart;
    }
}
