package ru.gb.springbase.config;

import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import ru.gb.springbase.model.Product;
import ru.gb.springbase.repository.ProductRepository;
import ru.gb.springbase.service.ProductService;


@Configuration
@ComponentScan("ru.gb.springbase")
public class AppConfig {

    @Bean
    public ProductRepository productRepository() {
        ProductRepository repository = new ProductRepository();
        repository.add(Product.builder().title("Apple").cost(50).build());
        repository.add(Product.builder().title("Banana").cost(40).build());
        repository.add(Product.builder().title("Orange").cost(99).build());
        repository.add(Product.builder().title("Pear").cost(45).build());
        repository.add(Product.builder().title("Grapes").cost(87).build());
        return repository;
    }

//    @Bean
//    public ProductService productService() {
//        return new ProductService();
//    }
}
