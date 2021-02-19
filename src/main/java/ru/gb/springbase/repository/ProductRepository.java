package ru.gb.springbase.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.gb.springbase.model.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
//    private List<Product> products;
//    private static int sequence = 0;
//
//    public ProductRepository() {
//        this.products = new ArrayList<>();
//    }
//
//    public Product add(Product product) {
//        product.setId(++sequence);
//        products.add(product);
//        return product;
//    }
//
//    public Product get(int id) {
//        return products.stream()
//                .filter(p -> p.getId() == id)
//                .findAny()
//                .orElseThrow(RuntimeException::new);
//    }
//
//    public List<Product> getProducts() {
//        return products;
//    }
//
//    public void update(Product product) {
//        products.stream()
//                .filter(p -> p.getId().equals(product.getId()))
//                .forEach(p -> {
//                    p.setTitle(product.getTitle());
//                    p.setCost(product.getCost());
//                });
//    }
//
//    public void delete(int id) {
//        products.removeIf(product -> product.getId() == id);
//    }

}
