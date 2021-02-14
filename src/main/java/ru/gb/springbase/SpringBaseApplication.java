package ru.gb.springbase;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import ru.gb.springbase.model.Product;
import ru.gb.springbase.service.ProductService;

import java.util.Scanner;

@SpringBootApplication
public class SpringBaseApplication implements CommandLineRunner {
    private ApplicationContext context;

    public static void main(String[] args) {
        SpringApplication.run(SpringBaseApplication.class, args);
    }

    @Override
    public void run(String... args) {
        ProductService service = context.getBean("productService", ProductService.class);

        Scanner scanner = new Scanner(System.in);
        String command;

        while ((command = scanner.next()) != null ) {
            if (command.equals("/end")) return;

            String[] commands = command.split("__");

            switch (commands[0]) {
                case "/products":
                {
                    service.getProducts().forEach(System.out::println);
                    break;
                }
                case "/product":
                    System.out.println(service.getProduct(Integer.parseInt(commands[1])));
                    break;
                case "/total":
                {
                    System.out.println(service.totalProducts());
                    break;
                }
                case "/average_cost":
                {
                    System.out.println(service.averageCost());
                    break;
                }
                case "/add":
                {
                    service.add(Product.builder()
                            .title(commands[1])
                            .cost(Integer.parseInt(commands[2]))
                            .build());
                    service.getProducts().forEach(System.out::println);
                    break;
                }
                case "/delete":
                {
                    service.delete(Integer.parseInt(commands[1]));
                    service.getProducts().forEach(System.out::println);
                    break;
                }
                case "/update":
                    service.update(Product.builder()
                            .id(Integer.parseInt(commands[1]))
                            .title(commands[2])
                            .cost(Integer.parseInt(commands[3]))
                            .build());
                    service.getProducts().forEach(System.out::println);
                    break;
            }
        }

    }

    @Autowired
    public void context(ApplicationContext context) {
        this.context = context;
    }

}
