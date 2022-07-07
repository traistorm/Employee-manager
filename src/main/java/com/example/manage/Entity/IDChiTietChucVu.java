package com.example.manage.Entity;

import lombok.Data;

import java.io.Serializable;
import java.time.LocalDate;

@Data
public class IDChiTietChucVu implements Serializable {
    private Integer MSNV;
    private Integer maCV;
    private LocalDate ngayBatDauCV;
}
