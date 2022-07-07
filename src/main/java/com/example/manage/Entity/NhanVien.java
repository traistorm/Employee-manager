package com.example.manage.Entity;

import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.time.LocalDate;

@Entity
@Data
@Table(name = "nhanvien")
public class NhanVien
{
    @Id
    private Integer MSNV;
    @Column(name = "hoten")
    private String hoTen;
    @Column(name = "gioitinh")
    private String gioiTinh;
    @Column(name = "ngaysinh", columnDefinition = "DATE")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate ngaySinh;
    @Column(name = "sdt")
    private String SDT;
    @Column(name = "email")
    private String email;
    @Column(name = "quequan")
    private String queQuan;
    @Column(name = "diachihientai")
    private String diaChiHienTai;
    @Column(name = "dantoc")
    private String danToc;
    @Column(name = "tendangnhap")
    private String tenDangNhap;
    @Column(name = "matkhau")
    private String matKhau;
    @Column(name = "vaitrotaikhoan")
    private String vaiTroTaiKhoan;
    @Column(name = "anhdaidien")
    private String anhDaiDien;
    @Column(name = "cmt")
    private String CMT;
    @Column(name = "sothich")
    private String soThich;
    @Column(name = "congviechientai")
    private String congViecHienTai;
    @Column(name = "mapb")
    private Integer maPB;
    @Column(name = "luongcoban")
    private Integer luongCoBan;
    @Column(name = "hesoluong")
    private Float heSoLuong;
    @Column(name = "hesophucap")
    private Float heSoPhuCap;

}
