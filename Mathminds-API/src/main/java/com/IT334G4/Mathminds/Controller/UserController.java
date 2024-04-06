package com.IT334G4.Mathminds.Controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.IT334G4.Mathminds.Entity.UserEntity;
import com.IT334G4.Mathminds.Service.UserService;

@RestController
@RequestMapping("/user")
public class UserController {

    UserService sserv;
    @GetMapping("print")
    public String printHello(){
        return "Hello, User Ngekngok";
    }

    @PostMapping("/insertUser")
    public UserEntity insertUser(@RequestBody UserEntity user){
        return sserv.insertUser(user);
    }
}
