package com.IT334G4.Mathminds.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.IT334G4.Mathminds.Entity.UserEntity;
import com.IT334G4.Mathminds.Repository.UserRepository;
import com.IT334G4.Mathminds.Response.UserManagementDTO;
import com.IT334G4.Mathminds.Response.UserProfileInfoDTO;

@Service
public class UserService {

    @Autowired
	UserRepository userRepo;

    // C - Create or insert user record in tbl_users
    public UserEntity insertUser(UserEntity user){
        return userRepo.save(user);
    }

    // R - Read all records in tbl_users
    public List<UserEntity> getAllUsers(){
        return userRepo.findAll();
    }

    // U - Update user info
    @SuppressWarnings("finally")
    public UserEntity updateUser(String uid, UserEntity newUserDetails){
        UserEntity user = new UserEntity();
        try{
            //1.) Search the UID number of the user to be updated
            user = userRepo.findById(uid).get();

            //2.) Update
            user.setFname(newUserDetails.getFname());
            user.setLname(newUserDetails.getLname());
            user.setEmail(newUserDetails.getEmail());
            user.setUserType(newUserDetails.getUserType());
            user.setProfilePictureUrl(newUserDetails.getProfilePictureUrl());

        }catch(NoSuchElementException ex){
            throw new NoSuchElementException("User" + uid + "does not exist");
        }finally{
            return userRepo.save(user);
        }
    }

    // D - Disable or change user status (TEMP ONLY, TO BE REVIEWED  BY EVERY1 LATER)
    public String disableUser(String uid){
        UserEntity user = new UserEntity();
        String message = "User " + uid + " does not exist."; //default value
        try{

            user = userRepo.findById(uid).get();

            user.setStatus("Disabled");
            message = "User " + uid + " is successfully deleted.";
            userRepo.save(user);
        }catch(NoSuchElementException ex){
            return message; 
        }
        
        return message;
    }

    // For User Profile Page (GET USER INFO)
    public UserEntity getUserByUid(String uid){
        UserEntity user = userRepo.findById(uid).orElseThrow(() ->new NoSuchElementException("User " + uid + " does not exist."));
		return user;
    }

    public UserProfileInfoDTO getUserProfileInfo(String uid) {
        UserEntity user = userRepo.findById(uid).orElseThrow(() ->new NoSuchElementException("User " + uid + " does not exist."));

        UserProfileInfoDTO userResponseProfileInfo = new UserProfileInfoDTO();
        userResponseProfileInfo.setFname(user.getFname());
        userResponseProfileInfo.setLname(user.getLname());
        userResponseProfileInfo.setEmail(user.getEmail());
        userResponseProfileInfo.setProfilePictureUrl(user.getProfilePictureUrl());
        return userResponseProfileInfo;
    }

    // Get user role
    public String getUserRole(String uid){
        UserEntity user = userRepo.findById(uid).orElseThrow(() ->new NoSuchElementException("User " + uid + " does not exist."));
        return user.getUserType();
    }

    // New method to change user role
    public UserManagementDTO changeUserRole(String uid, String newRole) {
        UserEntity user = userRepo.findById(uid)
                .orElseThrow(() -> new NoSuchElementException("User " + uid + " does not exist."));
        
        // Update the user's role
        user.setUserType(newRole);
        
        // Save the updated user entity
        UserEntity updatedUser = userRepo.save(user);
        
        // Convert the updated UserEntity to UserManagementDTO
        return new UserManagementDTO(
            updatedUser.getUid(),
            updatedUser.getFname(),
            updatedUser.getLname(),
            updatedUser.getEmail(),
            updatedUser.getStatus(),
            updatedUser.getUserType()
        );
    }

    //get all users for admin
    public List<UserManagementDTO> getAllUsersForAdmin() {
        List<UserEntity> users = userRepo.findAll();
        return users.stream().map(user -> new UserManagementDTO(
            user.getUid(),
            user.getFname(),
            user.getLname(),
            user.getEmail(),
            user.getStatus(),
            user.getUserType()
        )).collect(Collectors.toList());
    }
}
