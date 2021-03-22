package ru.gb.springbase.model.dtos;

import lombok.Data;

@Data
public class SignUpRequestDto {
    private String login;
    private String password;
}