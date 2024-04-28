package com.shop.sphere.controllers;

import com.shop.sphere.api.LoginApi;
import com.shop.sphere.api.model.LoginDTO;
import com.shop.sphere.api.model.UserConnectedDTO;
import com.shop.sphere.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LoginController implements LoginApi {

    @Autowired
    private LoginService loginService;

    @Override
    public ResponseEntity<UserConnectedDTO> login(LoginDTO loginDTO) {
        return loginService.login(loginDTO.getEmail(),loginDTO.getPassword());
    }

}
