package com.example.manage.Repository;

import com.example.manage.Entity.PhongBan;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PhongBanRepository extends JpaRepository<PhongBan, Integer> {
    PhongBan findAllByMaPB(Integer maPB);
    PhongBan findAllByTenPB(String tenPB);
}
