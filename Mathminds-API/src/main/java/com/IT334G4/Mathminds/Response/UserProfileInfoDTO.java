package com.IT334G4.Mathminds.Response;

// UserProfileInfoDTO.java
public class UserProfileInfoDTO {
    private String fname;
    private String lname;
    private String email;

    // Constructors
    public UserProfileInfoDTO() {
        super();    
		
    }

    public UserProfileInfoDTO(String fname, String lname, String email) {
        this.fname = fname;
        this.lname = lname;
        this.email = email;
    }

    // Getters and setters
    public String getFname() {
        return fname;
    }

    public void setFname(String fname) {
        this.fname = fname;
    }

    public String getLname() {
        return lname;
    }

    public void setLname(String lname) {
        this.lname = lname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}

