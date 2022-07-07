package com.example.manage.Repository;

import com.example.manage.Entity.LaborContact;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LaborContactRepository extends JpaRepository<LaborContact, Integer> {
    LaborContact findAllByMSHD(Integer MSHD);
    LaborContact findAllByMSNV(Integer MSNV);
    LaborContact deleteAllByMSNV(Integer MSNV);
    LaborContact deleteAllByMSHD(Integer MSHD);
}
