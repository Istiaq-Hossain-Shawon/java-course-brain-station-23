package com.icc.application.model;

import com.icc.applicaiton.enums.Role;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "tbl_user")
public class User implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "id")
    private long id;
    @Column(name = "username")
    private String username;
    @Column(name = "Name")
    private String Name;   
	@Column(name = "password", length = 512)
    private String password;
    @Enumerated(EnumType.STRING)
    @Column(name = "role")
    private Role role;    
    @Column(name = "Age")
    private int Age;
    @Column(name = "DOB")
    private Date DOB;
    public int getAge() {
		return Age;
	}
	public void setAge(int age) {
		Age = age;
	}
    public Date getDOB() {
		return DOB;
	}
	public void setDOB(Date dOB) {
		DOB = dOB;
	}
	public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }
    public String getName() {
		return Name;
	}

	public void setName(String name) {
		Name = name;
	}

}
