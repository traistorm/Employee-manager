package com.example.manage.Repository;

import com.example.manage.Entity.ChiTietChucVu;
import com.example.manage.Entity.IDChiTietChucVu;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;

public interface ChiTietChucVuRepository extends JpaRepository<ChiTietChucVu, IDChiTietChucVu> {
    List<ChiTietChucVu> findAllByMSNV(Integer MSNV);
    List<ChiTietChucVu> findAllByMSNVOrderByNgayBatDauCVDesc(Integer MSNV);
    ChiTietChucVu findByMSNVOrderByNgayBatDauCVDesc(Integer MSNV);
    ChiTietChucVu findAllByMSNVAndMaCVAndNgayBatDauCV(Integer MSNV, Integer maCv, LocalDate ngayBatDauCv);
}
