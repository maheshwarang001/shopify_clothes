package com.example.shopify_clothes.model;


import com.example.shopify_clothes.enumeration.Roles;
import jakarta.persistence.*;

import java.util.UUID;


@Entity
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID userid;

    private String email;

    private String pwd;

    @Enumerated(EnumType.STRING)
    private Roles role;

    public UUID getUserid() {
        return userid;
    }

    public void setUserid(UUID userid) {
        this.userid = userid;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPwd() {
        return pwd;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }

    public Roles getRole() {
        return role;
    }

    public void setRole(Roles role) {
        this.role = role;
    }
}
