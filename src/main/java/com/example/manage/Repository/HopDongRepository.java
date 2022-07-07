package com.example.manage.Repository;

import com.example.manage.Entity.HopDong;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface HopDongRepository extends JpaRepository<HopDong, Integer> {
    HopDong findAllByMaHD(Integer maHD);
    List<HopDong> findAllByTenHopDongContaining(String tenHopDong);
    List<HopDong> findAllByMSNV(Integer MSNV);
}
