package ru.gb.springbase.model.dtos;

import lombok.Data;
import lombok.NoArgsConstructor;
import ru.gb.springbase.model.entities.Product;

@Data
@NoArgsConstructor
public class ProductDto {
    private Long id;
    private String title;
    private int cost;
    private CategoryDto category;

    public ProductDto(Product p) {
        this.id = p.getId();
        this.title = p.getTitle();
        this.cost = p.getCost();
        this.category = new CategoryDto(p.getCategory());
    }
}
