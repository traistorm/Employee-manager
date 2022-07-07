package com.example.manage.Entity;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Data
@Table(name = "chucvu")
public class ChucVu
{
    @Id
    private Integer maCV;
    @Column(name = "tenchucvu")
    private String tenChucVu;
}
