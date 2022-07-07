package com.example.manage.Entity;

import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Data
@Table(name = "chitietchucvu")
@IdClass(IDChiTietChucVu.class)
public class ChiTietChucVu {
    @Id
    private Integer MSNV;
    @Id
    private Integer maCV;
    @Id
    @Column(name = "ngaybatdaucv", columnDefinition = "DATE")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate ngayBatDauCV;
    @Column(name = "ngayketthuccv", columnDefinition = "DATE")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate ngayKetThucCV;
}

