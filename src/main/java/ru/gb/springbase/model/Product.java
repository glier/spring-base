package ru.gb.springbase.model;

import lombok.*;

import java.math.BigDecimal;

@Data
@Builder
public class Product {
    private Integer id;
    private String title;
    private int cost;

    @Override
    public String toString() {
        return String.format("%4d | %20s | %6d", id, title, cost);
    }
}
