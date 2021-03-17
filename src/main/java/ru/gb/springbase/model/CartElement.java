package ru.gb.springbase.model;

import lombok.AllArgsConstructor;
import ru.gb.springbase.model.dtos.ProductDto;

import java.io.Serializable;

@AllArgsConstructor
public class CartElement implements Serializable {
    public ProductDto product;
    public Integer count;
}
