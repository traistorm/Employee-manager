package com.example.manage.Repository;

import com.example.manage.Entity.Luong;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface LuongRepository extends JpaRepository<Luong, Integer> {
    List<Luong> findAllByMSNV(Integer MSNV);
    Luong findAllByMaLuong(Integer maLuong);
}
