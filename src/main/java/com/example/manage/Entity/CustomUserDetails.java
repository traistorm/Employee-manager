package com.example.manage.Entity;

import com.example.manage.Entity.User;
import com.example.manage.Repository.StaffRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

@Data
@AllArgsConstructor
public class CustomUserDetails implements UserDetails
{
    NhanVien nhanVien;
    @Autowired
    StaffRepository staffRepository;
    //private String rolePrefix = "ROLE_";
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        String rolePrefix = "ROLE_";
        // Mặc định mình sẽ để tất cả là ROLE_USER. Để demo cho đơn giản.
        List<GrantedAuthority> listRole = new ArrayList<GrantedAuthority>();
        String role = nhanVien.getVaiTroTaiKhoan();
        listRole.add(new SimpleGrantedAuthority(rolePrefix + role));
        return listRole;
    }
    @Override
    public String getPassword() {
        return nhanVien.getMatKhau();
    }
    @Override
    public String getUsername() {
        return nhanVien.getTenDangNhap();
    }
    public Integer getMSNV()
    {
        return nhanVien.getMSNV();
    }
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
