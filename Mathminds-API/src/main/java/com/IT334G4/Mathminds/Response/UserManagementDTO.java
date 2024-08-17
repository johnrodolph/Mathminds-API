package com.IT334G4.Mathminds.Response;

public class UserManagementDTO {
    private String uid;
    private String fname;
    private String lname;
    private String email;
    private String status;
    private String userType;

    // Constructors
    public UserManagementDTO() {
        super();    
    }

    public UserManagementDTO(String uid, String fname, String lname, String email, String status, String userType) {
        this.uid = uid;
        this.fname = fname;
        this.lname = lname;
        this.email = email;
        this.status = status;
        this.userType = userType;
    }

    // Getters and setters
    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

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

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getUserType() {
        return userType;
    }

    public void setUserType(String userType) {
        this.userType = userType;
    }
}
