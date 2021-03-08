package ru.gb.springbase.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.gb.springbase.model.entities.Category;

public interface CategoryRepository extends JpaRepository<Category, Long> {
}
