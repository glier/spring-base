package ru.gb.springbase.service;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import ru.gb.springbase.exception.ErrorPageException;
import ru.gb.springbase.exception.NoDataFoundException;
import ru.gb.springbase.model.dtos.CategoryDto;
import ru.gb.springbase.model.entities.Category;
import ru.gb.springbase.repository.CategoryRepository;

@Service
@RequiredArgsConstructor
public class CategoryService {
    private final CategoryRepository categoryRepository;

    public CategoryDto findCategoryById(Long id) {
        return categoryRepository
                .findById(id)
                .map(CategoryDto::new)
                .orElseThrow(() -> new NoDataFoundException("Category id: " + id));
    }

    public Page<CategoryDto> findAll(int page, int pageSize) {
        if (page < 0)
            throw  new ErrorPageException(page, pageSize, null);

        return categoryRepository
                .findAll(PageRequest.of(page - 1, pageSize))
                .map(CategoryDto::new);
    }

    public Category saveOrUpdate(Category category) {
        return categoryRepository.save(category);
    }

    public void deleteById(Long id) {
        categoryRepository.deleteById(id);
    }

}
