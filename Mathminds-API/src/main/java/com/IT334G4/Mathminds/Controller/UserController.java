package com.IT334G4.Mathminds.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.IT334G4.Mathminds.Entity.UserEntity;
import com.IT334G4.Mathminds.Response.UserProfileInfoDTO;
import com.IT334G4.Mathminds.Service.UserService;

@RestController
@RequestMapping("mathminds/user")
@CrossOrigin
public class UserController {
    @Autowired
    UserService userService;

    @GetMapping("print")
    public String printHello(){
        return "Working!";
    }

    // C - Create a user record
    @PostMapping("/insertUser")
    public UserEntity insertUser(@RequestBody UserEntity user){
        return userService.insertUser(user);
    }
    
    // R - Read all records in tbl_users
    @GetMapping("/getAllUsers")
    public List<UserEntity> getAllUser(){
        return userService.getAllUsers();
    }

    // U - Update a user record
    @PutMapping("/updateUser")
    public UserEntity updateUser(@RequestParam String uid, @RequestBody UserEntity newUserDetails){
        return userService.updateUser(uid, newUserDetails);
    }

    @PutMapping("/disableUser/{uid}")
    public String disableUser(@PathVariable String uid){
        return userService.disableUser(uid);
    }

    @GetMapping("/getUserByUid")
    public UserEntity getUserByUid(@RequestParam String uid){
        return userService.getUserByUid(uid);
    }
    
    @GetMapping("/getUserProfileInfo")
    public UserProfileInfoDTO getUserProfileInfo(@RequestParam String uid) {
        return userService.getUserProfileInfo(uid);
    }

    @GetMapping("/getUserRole")
    public String getUserRole(@RequestParam String uid) {
        return userService.getUserRole(uid);
    }

  
}
