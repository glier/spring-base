package ru.gb.springbase.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import ru.gb.springbase.model.dtos.CategoryDto;
import ru.gb.springbase.model.entities.Category;
import ru.gb.springbase.service.CategoryService;

@RestController
@RequestMapping("/api/v1/categories")
@RequiredArgsConstructor
public class CategoryController {
    private final CategoryService categoryService;

    @GetMapping
    public Page<CategoryDto> findAllCategory(
            @RequestParam(required = false, defaultValue = "1") Integer page,
            @RequestParam(required = false, defaultValue = "5") Integer pageSize
    ) {
        return categoryService.findAll(page, pageSize);
    }

    @GetMapping("/{id}")
    public CategoryDto findCategoryById(@PathVariable Long id) {
        return categoryService.findCategoryById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Category saveNewCategory(@RequestBody Category category) {
        category.setId(null);
        return categoryService.saveOrUpdate(category);
    }

    @PutMapping
    @Transactional
    public Category updateCategory(@RequestBody Category category) {
        return categoryService.saveOrUpdate(category);
    }

    @DeleteMapping("/{id}")
    public void deleteCategory(@PathVariable Long id) {
        categoryService.deleteById(id);
    }

}
