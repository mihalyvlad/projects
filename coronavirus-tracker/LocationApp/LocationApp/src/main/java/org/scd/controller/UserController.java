package org.scd.controller;

import org.scd.config.exception.BusinessException;
import org.scd.model.User;
import org.scd.model.dto.UserLoginDTO;
import org.scd.model.dto.UserRegisterDTO;
import org.scd.model.security.CustomUserDetails;
import org.scd.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController()
@RequestMapping("/users")
public class UserController {
    @Autowired
    private UserService userService;

    @GetMapping("/all")
    public ResponseEntity<List<User>> getUsers() {
        return ResponseEntity.ok(userService.getUsers());
    }

    @GetMapping(path = "/me")
    public ResponseEntity<User> getCurrentUser() {
        return ResponseEntity.ok(((CustomUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getUser());
    }

    @PostMapping(path = "/login")
    public ResponseEntity<User> loginUser(@RequestBody final UserLoginDTO userLoginDTO) throws BusinessException{
        return ResponseEntity.ok(userService.login(userLoginDTO));
    }
    @PostMapping(path="/register")
    public ResponseEntity<User> registerUser(@RequestBody final UserRegisterDTO userRegisterDTO) throws BusinessException{
        return ResponseEntity.ok(userService.register((userRegisterDTO)));
    }


}
