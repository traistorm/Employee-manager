package com.example.manage.Repository;

import com.example.manage.Entity.ChucVu;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ChucVuRepository extends JpaRepository<ChucVu, Integer>
{
    ChucVu findAllByTenChucVu(String tenChucVu);
    ChucVu findAllByMaCV(Integer maCV);
}
