package ru.gb.springbase.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;
import ru.gb.springbase.model.Product;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long>, PagingAndSortingRepository<Product, Long> {
    List<Product> findProductsByCostLessThanEqual(Integer cost);
    List<Product> findProductsByCostGreaterThanEqual(Integer cost);
    List<Product> findProductsByCostBetween(Integer costMin, Integer costMax);
    List<Product> findProductsByTitleContainsIgnoreCase(String title);
    Page<Product> findProductsByCostBetweenAndTitleContainsIgnoreCase(Integer costMin, Integer costMax, String title, Pageable pageable);
}
