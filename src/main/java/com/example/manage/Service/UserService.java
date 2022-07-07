package com.example.manage.Service;

import com.example.manage.Entity.CustomUserDetails;
import com.example.manage.Entity.NhanVien;
import com.example.manage.Repository.NhanVienRepository;
import com.example.manage.Repository.StaffRepository;
import com.example.manage.Repository.UserRepository;
import com.example.manage.Entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private StaffRepository staffRepository;
    @Autowired
    private NhanVienRepository nhanVienRepository;
    @Override
    public UserDetails loadUserByUsername(String username) {
        // Kiểm tra xem user có tồn tại trong database không?
        NhanVien nhanVien = nhanVienRepository.findAllByTenDangNhap(username);
        if (nhanVien == null) {
            throw new UsernameNotFoundException(username);
        }
        return new CustomUserDetails(nhanVien, staffRepository);
    }


}