package com.IT334G4.Mathminds.Entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "tblUser")

public class UserEntity {

	@Id
	@Column(name = "user_id")
	private String uid;

	@Column(name = "firstname")
	private String firstName;

	@Column(name = "lastname")
	private String lname;

	private String email;

	private String userType;
	
	private String status;

	public UserEntity() {
		super();
		this.status = "Active";
		this.userType = "Student";
	}

	public UserEntity(String uid, String fname, String lname, String email) {
		this.uid = uid;
		this.fname = fname;
		this.lname = lname;
		this.email = email;
	}

	public String getUid() {
		return uid;
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

	public String getUserType() {
        return userType;
    }

    public void setUserType(String userType) {
        this.userType = userType;
    }

	public String getStatus(){
		return status;
	}

	public void setStatus(String status){
		this.status = status;
	}

}