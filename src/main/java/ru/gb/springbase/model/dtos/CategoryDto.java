package ru.gb.springbase.model.dtos;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.gb.springbase.model.entities.Category;


@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CategoryDto {
    private Long id;
    private String title;

    public CategoryDto(Category c) {
        this.id = c.getId();
        this.title = c.getTitle();
    }
}
