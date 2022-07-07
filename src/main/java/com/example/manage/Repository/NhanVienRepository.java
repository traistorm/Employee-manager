package com.example.manage.Repository;

import com.example.manage.Entity.NhanVien;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

public interface NhanVienRepository extends JpaRepository<NhanVien, Integer> {
    List<NhanVien> findAllByHoTenContaining(String nameSearch);
    NhanVien findAllByMSNV(Integer MSNV);
    NhanVien findAllByMSNVAndVaiTroTaiKhoan(Integer MSNV, String vaiTroTaiKhoan);
    List<NhanVien> findAllByVaiTroTaiKhoan(String role);
    NhanVien findAllByTenDangNhap(String tenDangNhap);
    @Query("select n from NhanVien n where (?1 is null or ?1 = n.MSNV) and (?2 is null or n.hoTen like %?2%) and " +
            "(?3 is null or n.email like %?3%) and " +
            "(?4 is null or n.SDT like %?4%) and " +
            "(?5 is null or n.danToc like %?5%) and " +
            "(?6 is null or n.queQuan like %?6%) and " +
            "(?7 is null or n.diaChiHienTai like %?7%) and " +
            "(?8 is null or n.gioiTinh like %?8%) and " +
            "(?9 is null or ?9 = n.ngaySinh)")
    List<NhanVien> search(Integer MSNV, String hoTen, String email, String SDT, String danToc, String queQuan, String diaChiHienTai, String gioiTinh, LocalDate ngaySinh);
    @Query("select n from NhanVien n where (?1 is null or ?1 = n.MSNV) and (?2 is null or n.email like %?2%) and " +
            "(?3 is null or n.SDT like %?3%) and " +
            "(?4 is null or n.hoTen like %?4%)")
    List<NhanVien> searchChamCong(Integer MSNV, String email, String SDT, String hoTen);
    @Query("select n from NhanVien n where (?1 is null or ?1 = n.MSNV) and (?2 is null or n.email like %?2%) and " +
            "(?3 is null or n.SDT like %?3%) and " +
            "(?4 is null or n.hoTen like %?4%)")
    List<NhanVien> searchHopDong(Integer MSNV, String email, String SDT, String hoTen);
}
