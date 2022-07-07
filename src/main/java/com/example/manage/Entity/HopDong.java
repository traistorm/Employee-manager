package com.example.manage.Entity;

import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Data
@Table(name = "hopdong")
public class HopDong
{
    @Id
    private Integer maHD;
    @Column(name = "tenHD")
    private String tenHopDong;
    @Column(name = "ngaybatdauhd", columnDefinition = "DATE")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate ngayBatDauHD;
    @Column(name = "ngayketthuchd", columnDefinition = "DATE")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate ngayKetThucHD;
    @Column(name = "thoigianthemHD", columnDefinition = "DATE")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate thoiGianThemHD;
    @Column(name = "msnv")
    private Integer MSNV;
}
