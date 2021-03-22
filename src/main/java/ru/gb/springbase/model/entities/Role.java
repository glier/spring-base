package ru.gb.springbase.model.entities;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
@Table(name = "role_table")
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "name")
    private String name;
}
