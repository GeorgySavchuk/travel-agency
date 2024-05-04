package org.example.travelagency.controller;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.AllArgsConstructor;
import org.example.travelagency.model.DTO.UserDTO;
import org.example.travelagency.service.AuthService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@AllArgsConstructor
@RestController("/auth")
public class AuthController {
    private final AuthService authorizationService;

    @PostMapping("auth/signup")
    public String signUp(@RequestBody UserDTO userData){
        if(authorizationService.signUp(userData)){
            return "User created";
        }else{
            return "User exists";
        }
    }

    @PostMapping("auth/signin")
    public String signIn(@RequestBody UserDTO userData, HttpServletRequest request, HttpServletResponse response){
        if(authorizationService.signIn(userData, request, response)){
            return "Signed in successfully";
        }else{
            return "Bad credentials";
        }
    }
}
