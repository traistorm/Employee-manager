package com.example.manage.Entity;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Date;

@Data
@Table(name = "staff")
@Entity
public class Staff
{
    @Id
    private Integer MSNV;
    @Column(nullable = false, unique = true)
    private String name;
    private String phone;
    private String email;
    private String address;
    private String office;
    @Column(name = "birthdate", columnDefinition = "DATE") //ten cot phai viet chu thuong
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate dateOfBird;
    @Column(name = "numberwork")
    private int numberWork;
    private Integer MSTK;
}
