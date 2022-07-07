package com.example.manage.Entity;

import javax.persistence.*;

import lombok.Data;

@Entity
@Table(name = "user")
@Data // lombok
public class User
{
    @Id
    private Integer MSTK;
    @Column(nullable = false, unique = true)
    private String username;
    private String password;
    private String role;
}
