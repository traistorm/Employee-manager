package com.example.manage.Entity;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Data
@Table(name = "phongban")
public class PhongBan
{
    @Id
    private Integer maPB;
    @Column(name = "tenpb")
    private String tenPB;
    @Column(name = "sdtpb")
    private String SDTPB;
    @Column(name = "diachipb")
    private String diaChiPB;
    @Column(name = "emailpb")
    private String emailPB;
}
