package com.IT334G4.Mathminds.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.IT334G4.Mathminds.Entity.UserEntity;
import com.IT334G4.Mathminds.Repository.UserRepository;

@Service

public class UserService {

    @Autowired
	UserRepository srepo;

    public UserEntity insertUser(UserEntity user){
        return srepo.save(user);
    }
}
