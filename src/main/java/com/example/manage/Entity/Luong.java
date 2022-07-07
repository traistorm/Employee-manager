package com.example.manage.Entity;

import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.time.LocalDate;

@Data
@Table
@Entity
public class Luong {
    @Id
    @Column(name = "maluong")
    private Integer maLuong;
    @Column(name = "msnv")
    private Integer MSNV;
    @Column(name = "luongcoban")
    private Integer luongCoBan;
    @Column(name = "hesoluong")
    private Float heSoLuong;
    @Column(name = "hesophucap")
    private Float heSoPhuCap;
    @Column(name = "ngaythanhtoan", columnDefinition = "DATE")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate ngayThanhToan;

}
