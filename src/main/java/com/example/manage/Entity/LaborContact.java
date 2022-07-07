package com.example.manage.Entity;

import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Date;

@Data
@Table(name = "laborcontact")
@Entity
public class LaborContact {
    @Id
    private Integer MSHD;
    @Column(name = "thoigianbatdau", columnDefinition = "DATE")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate thoiGianBatDau;
    @Column(name = "thoigianketthuc", columnDefinition = "DATE")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate thoiGianKetThuc;
    @Column(name = "luongngay")
    private Integer luong;
    @Column(name = "msnv")
    private Integer MSNV;
}
