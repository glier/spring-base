package ru.gb.springbase.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import ru.gb.springbase.config.jwt.JwtProvider;
import ru.gb.springbase.model.dtos.AuthRequestDto;
import ru.gb.springbase.model.dtos.AuthResponseDto;
import ru.gb.springbase.model.dtos.SignUpRequestDto;
import ru.gb.springbase.model.entities.User;
import ru.gb.springbase.service.UserService;


@RestController
public class AuthController {

    @Autowired
    private UserService userService;

    @Autowired
    private JwtProvider jwtProvider;

    @PostMapping("/register")
    public String registerUser(@RequestBody SignUpRequestDto signUpRequest) {
        User user = new User();
        user.setPassword(signUpRequest.getPassword());
        user.setUsername(signUpRequest.getLogin());
        userService.saveOrUpdateUser(user);
        return "OK";
    }

    @PostMapping("/auth")
    public AuthResponseDto auth(@RequestBody AuthRequestDto request) {
        User user = userService.findByLoginAndPassword(request.getLogin(), request.getPassword());
        String token = jwtProvider.generateToken(user.getUsername());
        return new AuthResponseDto(token);
    }
}