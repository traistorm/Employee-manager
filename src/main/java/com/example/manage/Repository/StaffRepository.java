package com.example.manage.Repository;

import com.example.manage.Entity.Staff;
import com.example.manage.Entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StaffRepository extends JpaRepository<Staff, Integer>
{
    Staff findAllByMSNV(Integer MSNV);
    List<Staff> findAllByName(String staffName);
    List<Staff> findAllByNameLike(String staffName);
    List<Staff> findAllByNameContains(String staffName);
    Staff findByMSTK(Integer MSTK);
}
