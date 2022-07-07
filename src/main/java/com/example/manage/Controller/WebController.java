package com.example.manage.Controller;

import com.example.manage.Entity.*;
import com.example.manage.Repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.*;

@Controller
public class WebController {
    @Autowired
    UserRepository userRepository;
    @Autowired
    StaffRepository staffRepository;
    @Autowired
    LaborContactRepository laborContactRepository;
    @Autowired
    HopDongRepository hopDongRepository;
    @Autowired
    ChucVuRepository chucVuRepository;
    @Autowired
    ChiTietChucVuRepository chiTietChucVuRepository;
    @Autowired
    NhanVienRepository nhanVienRepository;
    @Autowired
    PhongBanRepository phongBanRepository;
    @Autowired
    PasswordEncoder passwordEncoder;
    @Autowired
    LuongRepository luongRepository;
    @RequestMapping(value = { "/homeAdmin"})
    public String homeAdmin() {
        return "homeAdmin"; // Trả về home.html
    }
    @RequestMapping(value = {"/homeUser"})
    public String homeUser() {
        return "homeUser"; // Trả về home.html
    }
    @RequestMapping(value = {"/", "home"})
    public String home1(Model model, HttpServletRequest request, @RequestParam(required = false) String pageView,
                        @RequestParam(required = false) String numberWork,
                        @RequestParam(required = false) String MSNV,
                        @ModelAttribute NhanVien nhanVienNew,
                        @RequestParam(required = false) String tenCVaddStaff,
                        @ModelAttribute("staffTest") Staff staffTest,
                        @RequestParam(required = false) String check,
                        @RequestParam(required = false) String action,
                        @RequestParam(required = false) String MSHD,
                        @RequestParam(required = false) String maHD,
                        @ModelAttribute LaborContact laborContactNew,
                        @ModelAttribute HopDong hopDongEdit,
                        @ModelAttribute HopDong hopDongNew,
                        @ModelAttribute ChiTietChucVu chiTietChucVuNew,
                        @RequestParam(required = false) String keywordSearch,
                        @RequestParam(required = false) String keywordSearchLC,
                        @RequestParam(required = false) String keywordSearchSalary,
                        @RequestParam(required = false) String keywordSearchSalaryInfo,
                        @ModelAttribute NhanVien nhanVienEdit,
                        @RequestParam(required = false) String MSNVDelete,
                        @ModelAttribute User userEdit,
                        @ModelAttribute User userNew,
                        @ModelAttribute ChiTietChucVu chiTietChucVuEdit,
                        @ModelAttribute Luong luongNew,
                        @ModelAttribute Luong luongEdit,
                        @ModelAttribute ChucVu chucVuNew,
                        @ModelAttribute ChucVu chucVuEdit,
                        @ModelAttribute PhongBan phongBanNew,
                        @ModelAttribute PhongBan phongBanEdit,
                        @RequestParam(required = false) String MSNVUserNew,
                        @RequestParam(required = false) String MSNVUserDelete,
                        @RequestParam(required = false) String addEmployeeConfirm,
                        @RequestParam(required = false) String editInfoStaffConfirm,
                        @RequestParam(required = false) String editSalaryConfirm,
                        @RequestParam(required = false) String changeDepartmentStaffConfirm,
                        @RequestParam(required = false) String deleteOfficeDetailConfirm,
                        @RequestParam(required = false) String addOfficeDetailNewConfirm,
                        @RequestParam(required = false) String addOfficeNewConfirm,
                        @RequestParam(required = false) String changeInfoOfficeConfirm,
                        @RequestParam(required = false) MultipartFile avatarEdit,
                        @RequestParam(required = false) MultipartFile avatarStaffNew,
                        @RequestParam(required = false) String addLaborContractConfirm,
                        @RequestParam(required = false) String addDepartmentConfirm,
                        @RequestParam(required = false) String deleteDepartmentConfirm,
                        @RequestParam(required = false) String usernameNew,
                        @RequestParam(required = false) String usernameCurrent,
                        @RequestParam(required = false) String passwordNew,
                        @RequestParam(required = false) String passwordCurrent,
                        @RequestParam(required = false) String passwordNewConfirm,
                        @RequestParam(required = false) String changeInfoLoginStaffConfirm,
                        @RequestParam(required = false) String editLaborContractConfirm,
                        @RequestParam(required = false) String editOfficeDepartmentConfirm,
                        @RequestParam(required = false) String changeInfoSalaryConfirm,
                        @RequestParam(required = false) String addSalaryConfirm,
                        @RequestParam(required = false) String deleteStaffConfirm,
                        @RequestParam(required = false) String deleteLaborContractConfirm,
                        @RequestParam(required = false) String deleteSalaryDetailConfirm,
                        @RequestParam(required = false) String deleteOfficeConfirm,
                        @RequestParam(required = false) String changeInfoDepartmentConfirm,
                        @RequestParam(required = false) String check1,
                        @RequestParam(required = false) String check2,
                        @RequestParam(required = false) String searchNhanVien,
                        @RequestParam(required = false) String searchChamCongNhanVien,
                        @RequestParam(required = false) String searchHopDongNhanVien,
                        @RequestParam(required = false) String searchLuongNhanVien,
                        @RequestParam(required = false) String hoTen,
                        @RequestParam(required = false) String email,
                        @RequestParam(required = false) String SDT,
                        @RequestParam(required = false) String danToc,
                        @RequestParam(required = false) String queQuan,
                        @RequestParam(required = false) String diaChiHienTai,
                        @RequestParam(required = false) String congViecHienTai,
                        @RequestParam(required = false) String gioiTinh,
                        @RequestParam(required = false) String phongBanNhanVien,
                        @RequestParam(required = false) String chucVuNhanVien,
                        @RequestParam(required = false) String ngaySinh,
                        @RequestParam(required = false) String soNgayLamViec,
                        @RequestParam(required = false) String addStaffConfirm,
                        @RequestParam(required = false) String maCVNhanVienNew,
                        @RequestParam(required = false) String maPBNhanVienNew,
                        @RequestParam(required = false) String gioiTinhNhanVienNew,
                        @RequestParam(required = false) String luongCoBan,
                        @RequestParam(required = false) String heSoLuong,
                        @RequestParam(required = false) String heSoPhuCap,
                        @RequestParam(required = false) String maCV,
                        @RequestParam(required = false) String ngayBatDauCV,
                        @RequestParam(required = false) String maLuong,
                        @RequestParam(required = false) String maPB,
                        @RequestParam(required = false) String maPBCurrent,
                        @RequestParam(required = false) String maCVCurrent) {
        String employeeManagerPage = "1";
        String salaryManagerPage = "2";
        String laborContractPage = "3";
        String errorMessage = "";
        String message = "";
        boolean isAdmin = request.isUserInRole("ROLE_ADMIN");
        boolean isUser = request.isUserInRole("ROLE_USER");
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        CustomUserDetails userDetails = (CustomUserDetails) authentication.getPrincipal();
        List<User> userList;
        List<Staff> staffList;
        List<LaborContact> laborContactList;
        List<Staff> staffListSearch;
        List<LaborContact> laborContractListSearch = new ArrayList<>();
        List<LaborContact> laborContractSalaryInfoListSearch = new ArrayList<>();
        System.out.println("Present Project Directory : "+ System.getProperty("user.dir"));
        List<HopDong> hopDongList = new ArrayList<>();
        List<NhanVien> nhanVienList = new ArrayList<>();
        List<ChucVu> chucVuList = new ArrayList<>();
        List<PhongBan> phongBanList = new ArrayList<>();
        List<NhanVien> nhanVienListSearch = new ArrayList<>();
        if (avatarEdit != null)
        {
            String fileName = StringUtils.cleanPath(avatarEdit.getOriginalFilename());
            System.out.println(fileName);
        }
        //System.out.println("MSNV : " + userDetails.getMSNV());
        if (isAdmin)
        {
            model.addAttribute("isSearch", "no");
            if (keywordSearch != null) // Tìm kiếm thông tin nhân viên
            {
                //System.out.println("Search");
                model.addAttribute("isSearch", "yes");
                nhanVienListSearch = nhanVienRepository.findAllByHoTenContaining(keywordSearch);
                if (nhanVienListSearch.size() == 0)
                {
                    //System.out.println("Check");
                    message = "Không có nhân viên nào phù hợp được tìm thấy";
                }
                else
                {
                    message = "Có " + nhanVienListSearch.size() + " nhân viên được tìm thấy";
                }
                model.addAttribute("nhanVienListSearch", nhanVienListSearch);
            }
            if (searchNhanVien != null)
            {
                Integer MSNVValue;
                String gioiTinhValue;
                Integer maCVValue;
                Integer maPBValue;
                if (MSNV.equals(""))
                {
                    MSNVValue = null;
                }
                else
                {
                    MSNVValue = Integer.parseInt(MSNV);
                }
                if (gioiTinh.equals("All"))
                {
                    gioiTinhValue = null;
                }
                else
                {
                    gioiTinhValue = gioiTinh;
                }
                LocalDate date;
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM-MMM-yyyy");
                if (ngaySinh.equals(""))
                {
                    date = null;
                }
                else
                {
                    date = LocalDate.parse(ngaySinh);
                }
                model.addAttribute("isSearch", "yes");
                nhanVienListSearch = nhanVienRepository.search(MSNVValue, hoTen, email, SDT, danToc, queQuan, diaChiHienTai, gioiTinhValue, date);
                model.addAttribute("nhanVienListSearch", nhanVienListSearch);
                if (nhanVienListSearch.size() != 0)
                {
                    message = "Có " + nhanVienListSearch.size() + " nhân viênđược tìm thấy";
                }
                else
                {
                    message = "Không có nhân viên nào phù hợp được tìm thấy";
                }
                System.out.println(nhanVienListSearch);
            }
            if (searchChamCongNhanVien != null)
            {
                Integer MSNVValue;
                Integer soNgayLamViecValue;
                if (MSNV.equals(""))
                {
                    MSNVValue = null;
                }
                else
                {
                    MSNVValue = Integer.parseInt(MSNV);
                }
                model.addAttribute("isSearch", "yes");
                nhanVienListSearch = nhanVienRepository.searchChamCong(MSNVValue, email, SDT, hoTen);
                model.addAttribute("nhanVienListSearch", nhanVienListSearch);
                if (nhanVienListSearch.size() != 0)
                {
                    message = "Có " + nhanVienListSearch.size() + " nhân viên được tìm thấy";
                }
                else
                {
                    message = "Không có nhân viên nào phù hợp được tìm thấy";
                }
            }
            if (searchHopDongNhanVien != null)
            {
                model.addAttribute("isSearch", "yes");
                List<HopDong> hopDongListSearch = new ArrayList<>();
                Integer MSNVValue;
                if (MSNV.equals(""))
                {
                    MSNVValue = null;
                }
                else
                {
                    MSNVValue = Integer.parseInt(MSNV);
                }
                model.addAttribute("isSearch", "yes");
                nhanVienListSearch = nhanVienRepository.searchHopDong(MSNVValue, email, SDT, hoTen);
                model.addAttribute("nhanVienListSearch", nhanVienListSearch);
                System.out.println("So nhan vien tim thay : " + nhanVienListSearch.size());
                for (NhanVien nhanVien : nhanVienListSearch)
                {
                    List<HopDong> hopDongListFound = hopDongRepository.findAllByMSNV(nhanVien.getMSNV());
                    if (hopDongListFound != null)
                    {
                        for (HopDong hopDong : hopDongListFound)
                        {
                            hopDongListSearch.add(hopDong);
                        }
                    }
                }
                model.addAttribute("hopDongListSearch", hopDongListSearch);
                if (hopDongListSearch.size() != 0)
                {
                    message = "Có " + hopDongListSearch.size() + " hợp đồng được tìm thấy";
                }
                else
                {
                    message = "Không có hợp đồng nào phù hợp được tìm thấy";
                }
            }
            if (searchLuongNhanVien != null)
            {
                model.addAttribute("isSearch", "yes");
                List<HopDong> hopDongListSearch = new ArrayList<>();
                Integer MSNVValue;
                if (MSNV.equals(""))
                {
                    MSNVValue = null;
                }
                else
                {
                    MSNVValue = Integer.parseInt(MSNV);
                }
                nhanVienListSearch = nhanVienRepository.searchHopDong(MSNVValue, email, SDT, hoTen);
                model.addAttribute("nhanVienListSearch", nhanVienListSearch);
                System.out.println("So nhan vien tim thay : " + nhanVienListSearch.size());
                System.out.println("Checked");
                for (NhanVien nhanVien : nhanVienListSearch)
                {
                    List<HopDong> hopDongFoundList = hopDongRepository.findAllByMSNV(nhanVien.getMSNV());
                    if (hopDongFoundList.size() != 0)
                    {
                        Integer maHDChoose = hopDongFoundList.get(0).getMaHD();
                        LocalDate newestAdd = hopDongFoundList.get(0).getThoiGianThemHD();
                        for (int i = 0; i < hopDongFoundList.size(); i++)
                        {
                            if (newestAdd.isBefore(hopDongFoundList.get(i).getThoiGianThemHD()))
                            {
                                newestAdd = hopDongFoundList.get(i).getThoiGianThemHD();
                                maHDChoose = hopDongFoundList.get(i).getMaHD();
                            }
                        }
                        hopDongListSearch.add(hopDongRepository.findAllByMaHD(maHDChoose));
                    }
                }
                //System.out.println("So hop dong tim thay : ");
                model.addAttribute("hopDongListSearch", hopDongListSearch);
                if (hopDongListSearch.size() != 0)
                {
                    message = "Có " + hopDongListSearch.size() + " nhân viên được tìm thấy";
                }
                else
                {
                    message = "Không có nhân viên nào phù hợp được tìm thấy";
                }
            }
            if (pageView != null)
            {
                model.addAttribute("pageView", "" + pageView);
            }
            else
            {
                model.addAttribute("pageView", "1");
            }
            if (nhanVienNew != null && addStaffConfirm != null) // Thêm nhân viên mới
            {
                if (nhanVienNew.getMSNV() != null)
                {
                    ChucVu chucVuFound = chucVuRepository.findAllByMaCV(Integer.parseInt(maCVNhanVienNew));
                    PhongBan phongBanFound = phongBanRepository.findAllByMaPB(Integer.parseInt(maPBNhanVienNew));
                    //System.out.println(staff.getMSNV());
                    LocalDate localDateNow = LocalDate.now();
                    if (localDateNow.isBefore(nhanVienNew.getNgaySinh()))
                    {
                        errorMessage = "Ngày sinh không hợp lệ do sau ngày hiện tại";
                    }
                    else
                    {
                        NhanVien nhanVienFound = nhanVienRepository.findAllByMSNV(nhanVienNew.getMSNV()); // Kiểm tra mã số nhân viên mới thêm đã tồn tại chưa
                        if (nhanVienFound == null)
                        {
                            if (!avatarStaffNew.isEmpty())
                            {
                                String filePathSave = System.getProperty("user.dir") + "\\src\\main\\resources\\static\\img\\" + StringUtils.cleanPath(avatarStaffNew.getOriginalFilename());
                                try {
                                    byte[] bytes  = avatarStaffNew.getBytes();
                                    Files.write(Paths.get(filePathSave), bytes);
                                    nhanVienNew.setAnhDaiDien(StringUtils.cleanPath(avatarStaffNew.getOriginalFilename()));
                                }
                                catch (Exception e)
                                {
                                    errorMessage = "Không thể cập nhật ảnh của nhân viên";
                                }
                            }
                            if (phongBanFound != null)
                            {
                                nhanVienNew.setMaPB(Integer.parseInt(maPBNhanVienNew));
                            }
                            nhanVienRepository.save(nhanVienNew);
                            message = "Thêm nhân viên thành công";
                            if (chucVuFound != null)
                            {
                                ChiTietChucVu chiTietChucVuNew1 = new ChiTietChucVu();
                                chiTietChucVuNew1.setMaCV(Integer.parseInt(maCVNhanVienNew));
                                chiTietChucVuNew1.setMSNV(nhanVienNew.getMSNV());
                                chiTietChucVuNew1.setNgayBatDauCV(LocalDate.now());
                                chiTietChucVuRepository.save(chiTietChucVuNew1);
                                List<ChiTietChucVu> chiTietChucVuList = chiTietChucVuRepository.findAllByMSNVOrderByNgayBatDauCVDesc(nhanVienNew.getMSNV());
                                if (chiTietChucVuList.size() > 0)
                                {
                                    if (chiTietChucVuList.get(0).getNgayKetThucCV() == null)
                                    {
                                        chiTietChucVuList.get(0).setNgayKetThucCV(LocalDate.now());
                                    }
                                }
                            }
                        }
                        else
                        {
                            errorMessage = "Mã số nhân viên đã tồn tại, hãy sử dụng mã số sinh viên khác";
                        }
                    }
                }
            }
            if (hopDongNew != null && addLaborContractConfirm != null)
            {
                if (MSNV != null)
                {
                    NhanVien nhanVienFound = nhanVienRepository.findAllByMSNV(Integer.parseInt(MSNV));
                    if (nhanVienFound != null)
                    {
                        System.out.println("Check");
                        HopDong hopDongFound = hopDongRepository.findAllByMaHD(hopDongNew.getMaHD());
                        if (hopDongFound != null)
                        {
                            errorMessage = "Mã số hợp đồng đã tồn tại";
                        }
                        else
                        {
                            if (!hopDongNew.getNgayBatDauHD().isAfter(hopDongNew.getNgayKetThucHD()))
                            {
                                hopDongNew.setThoiGianThemHD(LocalDate.now());
                                hopDongRepository.save(hopDongNew);
                                message = "Hợp đồng lao động đã được thêm";
                            }
                            else
                            {
                                errorMessage = "Không thể thêm hợp đồng, thời gian kết thúc hợp đồng trước thời gian bắt đầu hợp đồng";
                            }
                        }
                    }
                }
            }
            if (action != null && MSNVDelete != null) // Xoá nhân viên
            {
                if (action.equals("deleteStaff"))
                {
                    System.out.println("Checked");
                    NhanVien nhanVienFound = nhanVienRepository.findAllByMSNV(Integer.parseInt(MSNVDelete));
                    if (nhanVienFound != null)
                    {
                        List<ChiTietChucVu> chiTietChucVuListFound = chiTietChucVuRepository.findAllByMSNV(nhanVienFound.getMSNV());
                        List<HopDong> hopDongListFound = hopDongRepository.findAllByMSNV(nhanVienFound.getMSNV());
                        List<Luong> luongListFound = luongRepository.findAllByMSNV(nhanVienFound.getMSNV());
                        for (ChiTietChucVu chiTietChucVu : chiTietChucVuListFound) // Xoá các thông tin liên quan tới nhân viên này trong bảng Chi Tiet Chuc Vu
                        {
                            chiTietChucVuRepository.delete(chiTietChucVu);
                        }
                        for (HopDong hopDong : hopDongListFound) // Xoá các thông tin về hợp đồng
                        {
                            hopDongRepository.delete(hopDong);
                        }
                        for (Luong luong : luongListFound)
                        {
                            luongRepository.delete(luong);
                        }
                        nhanVienRepository.delete(nhanVienFound);
                        message = "Xoá nhân viên thành công";
                    }
                }
            }
            if (action != null && MSNVUserDelete != null) // Xoá thông tin đăng nhập của nhân viên
            {
                if (action.equals("deleteUser"))
                {
                    NhanVien nhanVienFound = nhanVienRepository.findAllByMSNVAndVaiTroTaiKhoan(Integer.parseInt(MSNVUserDelete), "USER");
                    if (nhanVienFound != null)
                    {
                        nhanVienFound.setTenDangNhap("");
                        nhanVienFound.setMatKhau("");
                        nhanVienRepository.save(nhanVienFound);
                        message = "Xoá thông tin đăng nhập thành công";
                    }
                }
            }
            if (action != null && MSHD != null && MSNV != null)
            {
                if (action.equals("deleteLaborContract")) // Xoá hợp đồng lao động
                {
                    HopDong hopDongFound = hopDongRepository.findAllByMaHD(Integer.parseInt(MSHD));
                    if (hopDongFound != null)
                    {
                        hopDongRepository.delete(hopDongFound);
                        message = "Xoá hợp đồng lao động thành công";
                    }
                    else
                    {
                        message = "Không tìm thấy mã hợp đồng cần xoá";
                    }
                }

            }
            if (hopDongEdit != null && editLaborContractConfirm != null)
            {
                HopDong hopDongFound = hopDongRepository.findAllByMaHD(hopDongEdit.getMaHD());
                if (hopDongFound != null)
                {
                    hopDongRepository.save(hopDongEdit);
                    message = "Chỉnh sửa hợp đồng lao động thành công";
                }
            }
            if (chiTietChucVuEdit != null && editOfficeDepartmentConfirm != null) // Chỉnh sửa thông tin chi tiết chức vụ
            {
                chiTietChucVuRepository.save(chiTietChucVuEdit);
                message = "Chỉnh sửa thông tin chi tiết chức vụ thành công";
            }
            if (nhanVienEdit != null && editInfoStaffConfirm != null) // Chỉnh sửa nhân viên
            {
                if (nhanVienEdit.getHoTen() != null)
                {
                    if (avatarEdit != null)
                    {
                        if (!avatarEdit.isEmpty())
                        {
                            NhanVien nhanVienFound = nhanVienRepository.findAllByMSNV(nhanVienEdit.getMSNV());
                            String filePathSave = System.getProperty("user.dir") + "\\src\\main\\resources\\static\\img\\" + StringUtils.cleanPath(avatarEdit.getOriginalFilename());
                            try {
                                byte[] bytes  = avatarEdit.getBytes();
                                Files.write(Paths.get(filePathSave), bytes);
                                System.out.println(StringUtils.cleanPath(avatarEdit.getOriginalFilename()));
                                nhanVienEdit.setAnhDaiDien(StringUtils.cleanPath(avatarEdit.getOriginalFilename()));
                                nhanVienEdit.setTenDangNhap(nhanVienFound.getTenDangNhap());
                                nhanVienEdit.setMatKhau(nhanVienFound.getMatKhau());
                                nhanVienRepository.save(nhanVienEdit);
                                message = "Đã lưu thông tin nhân viên sau chỉnh sửa, ảnh đại diện đã được cập nhật";
                            }
                            catch (Exception e)
                            {
                                message = "Không thể lưu ảnh của nhân viên";
                            }
                        }
                        else
                        {
                            NhanVien nhanVienFound = nhanVienRepository.findAllByMSNV(nhanVienEdit.getMSNV());
                            if (nhanVienFound != null)
                            {
                                String avatarName = nhanVienFound.getAnhDaiDien();
                                nhanVienEdit.setAnhDaiDien(avatarName);
                                nhanVienEdit.setTenDangNhap(nhanVienFound.getTenDangNhap());
                                nhanVienEdit.setMatKhau(nhanVienFound.getMatKhau());
                                nhanVienRepository.save(nhanVienEdit);
                                message = "Đã lưu thông tin nhân viên sau chỉnh sửa";
                            }
                            else
                            {
                                errorMessage = "Mã số nhân viên không tồn tại";
                            }
                        }
                    }
                }
            }
            if (changeInfoLoginStaffConfirm != null)
            {
                NhanVien nhanVienFound = nhanVienRepository.findAllByMSNV(Integer.parseInt(MSNV));
                if (nhanVienFound != null)
                {

                    if (usernameNew.matches("^[A-Za-z0-9]*$") && passwordNew.matches("^[A-Za-z0-9]*$"))
                    {
                        NhanVien nhanVienFound1 = nhanVienRepository.findAllByTenDangNhap(usernameNew);
                        if (nhanVienFound1 == null)
                        {
                            nhanVienFound.setTenDangNhap(usernameNew);
                            nhanVienFound.setMatKhau(passwordEncoder.encode(passwordNew));
                            nhanVienRepository.save(nhanVienFound);
                            message = "Chỉnh sửa thông tin đăng nhập thành công";
                        }
                        else
                        {
                            if (nhanVienFound.getMSNV() == Integer.parseInt(MSNV))
                            {
                                errorMessage = "Têm đăng nhập trùng tên đăng nhập cũ";
                            }
                            else
                            {
                                errorMessage = "Tên đăng nhập đã tồn tại!";
                            }
                        }
                    }
                }
                else
                {
                    errorMessage = "Tên đănh nhập, mật khẩu chỉ gồm các kí tự a-z, A-Z hoặc 0-9";
                }
            }
            if (nhanVienEdit != null && action != null) // Chỉnh sửa thông tin đăng nhập
            {
                /*if (nhanVienEdit.getMSNV() != null && action.equals("editUserConfirm"))
                {
                    NhanVien nhanVienFound = nhanVienRepository.findAllByMSNV(nhanVienEdit.getMSNV());
                    if (nhanVienFound != null)
                    {
                        nhanVienFound.setTenDangNhap(nhanVienEdit.getTenDangNhap());
                        nhanVienFound.setMatKhau(passwordEncoder.encode(nhanVienEdit.getMatKhau()));
                        nhanVienRepository.save(nhanVienFound);
                        message = "Chỉnh sửa thông tin đăng nhập thành công";
                    }
                    else
                    {
                        errorMessage = "Mã số nhân viên không tồn tại";
                    }
                }*/
            }
            if (usernameNew != null && passwordNew != null) // Đổi mật khẩu Admin
            {
                List<NhanVien> nhanVienFound = nhanVienRepository.findAllByVaiTroTaiKhoan("ADMIN");
                if (passwordNew.equals(passwordCurrent))
                {
                    errorMessage = "Mật khẩu trùng mật khẩu cũ";
                }
                else
                {
                    if (!passwordNew.equals(passwordNewConfirm))
                    {
                        errorMessage  ="Mật khẩu xác nhận không trùng khớp";
                    }
                    else
                    {
                        if (usernameNew.equals(usernameCurrent))
                        {
                            if (nhanVienFound.get(0) != null)
                            {
                                if (usernameNew.matches("^[A-Za-z0-9]*$") && passwordNew.matches("^[A-Za-z0-9]*$"))
                                {
                                    NhanVien nhanVienFound1 = nhanVienRepository.findAllByTenDangNhap(usernameNew);
                                    if (nhanVienFound1 != null)
                                    {
                                        nhanVienFound.get(0).setTenDangNhap(usernameNew);
                                        nhanVienFound.get(0).setTenDangNhap(passwordEncoder.encode(passwordNew));
                                        message = "Đổi mật khẩu thành công!";
                                    }
                                }
                                else
                                {
                                    errorMessage = "Tên đăng nhập và mật khẩu chỉ gồm các kí tự a-z, A-Z, 0-9";
                                }
                            }
                        }
                        else
                        {
                            if (nhanVienFound.get(0) != null)
                            {
                                NhanVien nhanVienFound1 = nhanVienRepository.findAllByTenDangNhap(usernameNew);
                                if (nhanVienFound1 != null)
                                {
                                    errorMessage = "Tên đăng nhập đã tồn tại";
                                }
                            }
                        }
                    }
                }
            }
            if (changeInfoSalaryConfirm != null) // Chỉnh sửa thông tin lương nhân viên
            {
                NhanVien nhanVienFound = nhanVienRepository.findAllByMSNV(Integer.parseInt(MSNV));
                if (nhanVienFound != null)
                {
                    if (Integer.parseInt(luongCoBan) > 0 && Float.parseFloat(heSoLuong) >= 0 && Float.parseFloat(heSoPhuCap) >= 0)
                    {
                        nhanVienFound.setLuongCoBan(Integer.parseInt(luongCoBan));
                        nhanVienFound.setHeSoLuong(Float.parseFloat(heSoLuong));
                        nhanVienFound.setHeSoPhuCap(Float.parseFloat(heSoPhuCap));
                        nhanVienRepository.save(nhanVienFound);
                        message = "Đã chỉnh sửa thông tin lương";
                    }
                }
            }
            if (luongNew != null && addSalaryConfirm != null)
            {
                if (luongNew.getMSNV() != null)
                {
                    NhanVien nhanVienFound = nhanVienRepository.findAllByMSNV(luongNew.getMSNV());
                    Luong luongFound = luongRepository.findAllByMaLuong(luongNew.getMaLuong());
                    if (luongFound != null)
                    {
                        errorMessage = "Mã lương đã tồn tại, hãy sử dụng mã lương khác";
                    }
                    else
                    {
                        if (nhanVienFound != null)
                        {
                            luongNew.setNgayThanhToan(LocalDate.now());
                            luongRepository.save(luongNew);
                            message = "Đã thêm thông tin thanh toán lương";
                        }
                    }
                }
            }
            if (luongEdit != null && editSalaryConfirm != null)
            {
                if (luongEdit.getMaLuong() != null)
                {
                    if (luongEdit.getMaLuong() > 0)
                    {
                        if (luongEdit.getLuongCoBan() > 0 && luongEdit.getHeSoLuong() >=0 && luongEdit.getHeSoPhuCap() >= 0)
                        {
                            luongRepository.save(luongEdit);
                            message = "Chỉnh sửa thông tin chi tiết lương thành công";
                        }
                        else
                        {
                            errorMessage = "Lương cơ bản, hệ số lương, hệ số phụ cấp phải là số nguyên dương";
                        }
                    }
                    else
                    {
                        errorMessage = "Mã lương phải là số nguyên dương";
                    }
                }
            }
            if (deleteStaffConfirm != null && MSNV != null) // Xoá nhân viên
            {
                System.out.println("Delete");
                NhanVien nhanVienFound = nhanVienRepository.findAllByMSNV(Integer.parseInt(MSNV));
                List<Luong> luongListFound = luongRepository.findAllByMSNV(Integer.parseInt(MSNV));
                if (nhanVienFound != null)
                {
                    List<ChiTietChucVu> chiTietChucVuFound = chiTietChucVuRepository.findAllByMSNV(Integer.parseInt(MSNV));
                    for (ChiTietChucVu chiTietChucVu : chiTietChucVuFound)
                    {
                        chiTietChucVuRepository.delete(chiTietChucVu); // Xoá các chức vụ liên quan tới nhân viên
                    }
                    for (Luong luong : luongListFound)
                    {
                        luongRepository.delete(luong); // Xoá các thông tin lương liên quan
                    }
                    nhanVienRepository.delete(nhanVienFound);
                }
            }
            if (deleteLaborContractConfirm != null)
            {
                HopDong hopDongFound = hopDongRepository.findAllByMaHD(Integer.parseInt(maHD));
                if (hopDongFound != null)
                {
                    hopDongRepository.delete(hopDongFound);
                }
            }
            if (deleteOfficeConfirm != null && maCV != null)
            {
                ChucVu chucVuFound = chucVuRepository.findAllByMaCV(Integer.parseInt(maCV));
                if (chucVuFound != null)
                {
                    chucVuRepository.delete(chucVuFound);
                    message = "Xoá chức vụ thành công";
                }
            }
            if (deleteSalaryDetailConfirm != null && maLuong != null) // Xoá chi tiết lương nhân viên
            {
                NhanVien nhanVienFound = nhanVienRepository.findAllByMSNV(Integer.parseInt(MSNV));
                if (nhanVienFound != null)
                {
                    Luong luongFound = luongRepository.findAllByMaLuong(Integer.parseInt(maLuong));
                    if (luongFound != null)
                    {
                        luongRepository.delete(luongFound);
                        message = "Đã xoá thông tin chi tiết lương";
                    }
                }
            }
            if (phongBanNew != null && addDepartmentConfirm != null)
            {
                PhongBan phongBanFound = phongBanRepository.findAllByMaPB(phongBanNew.getMaPB());
                if (phongBanFound != null)
                {
                    errorMessage = "Mã số phòng ban đã tồn tại, vui lòng sử dụng mã phòng ban khác";
                }
                else
                {
                    phongBanRepository.save(phongBanNew);
                    message = "Thêm phòng ban thành công";
                }
            }
            if (chiTietChucVuNew != null && addOfficeDetailNewConfirm != null)
            {
                NhanVien nhanVienFound = nhanVienRepository.findAllByMSNV(Integer.parseInt(MSNV));
                if (nhanVienFound != null)
                {
                    chiTietChucVuNew.setNgayBatDauCV(LocalDate.now());
                    chiTietChucVuRepository.save(chiTietChucVuNew);
                    message = "Thêm chức vụ mới thành công";
                }
            }
            if (chucVuNew != null && addOfficeNewConfirm != null)
            {
                ChucVu chucVuFound = chucVuRepository.findAllByMaCV(chucVuNew.getMaCV());
                if (chucVuFound != null)
                {
                    errorMessage = "Mã chức vụ đã tồn tại, hãy sử dụng mã chức vụ khác";
                }
                else
                {
                    chucVuRepository.save(chucVuNew);
                    message = "Thêm chức vụ thành công";
                }
            }
            if (deleteOfficeDetailConfirm != null && maCV != null && ngayBatDauCV != null)
            {
                LocalDate ngayBatDauCVFound = LocalDate.parse(ngayBatDauCV);
                ChiTietChucVu chiTietChucVuFound = chiTietChucVuRepository.findAllByMSNVAndMaCVAndNgayBatDauCV(Integer.parseInt(MSNV), Integer.parseInt(maCV), ngayBatDauCVFound);
                if (chiTietChucVuFound != null)
                {
                    chiTietChucVuRepository.delete(chiTietChucVuFound);
                    message = "Xoá chi tiết chức vụ thành công";
                }
            }
            if (chucVuEdit != null && changeInfoOfficeConfirm != null)
            {
                ChucVu chucVuFound = chucVuRepository.findAllByMaCV(chucVuEdit.getMaCV());
                if (chucVuFound != null)
                {
                    errorMessage = "Không thể chỉnh sửa mã chức vụ thành " + chucVuFound.getMaCV() + ", mã chức vụ này đã tồn tại";
                }
                else
                {
                    chucVuRepository.save(chucVuEdit);
                    message = "Chỉnh sửa thông tin chức vụ thành công";
                }
            }
            if (phongBanEdit != null && changeInfoDepartmentConfirm != null)
            {
                if (phongBanEdit.getMaPB() == Integer.parseInt(maPBCurrent))
                {
                    phongBanRepository.save(phongBanEdit);
                    message = "Chỉnh sửa thông tin phòng ban thành công";
                }
                else
                {
                    PhongBan phongBanFound = phongBanRepository.findAllByMaPB(phongBanEdit.getMaPB());
                    if (phongBanFound != null)
                    {
                        errorMessage = "Không thể chỉnh sửa mã phòng ban thành " + phongBanFound.getMaPB() + ", mã phòng ban này đã tồn tại";
                    }
                    else
                    {
                        message = "Chỉnh sửa thông tin phòng ban thành công";
                    }
                }
            }
            if (deleteDepartmentConfirm != null && maPB != null)
            {
                PhongBan phongBanFound = phongBanRepository.findAllByMaPB(Integer.parseInt(maPB));
                if (phongBanFound != null)
                {
                    phongBanRepository.delete(phongBanFound);
                    message = "Xoá phòng ban thành công";
                }
                else
                {
                    errorMessage = "Mã số phòng ban không tồn tại";
                }
            }
            if (userNew != null && action != null)
            {
                if (userNew.getMSTK() != null && MSNVUserNew != null && action.equals("addUserConfirm"))
                {
                    if (userNew.getUsername().toLowerCase().equals("admin"))
                    {
                        errorMessage = "Tên đăng nhập trùng với tên đăng nhập của tài khoản mặc định";
                    }
                    else
                    {
                        Integer MSNVInt = Integer.parseInt(MSNVUserNew);
                        if (MSNVInt <= 0)
                        {
                            errorMessage = "Mã số nhân viên phải là số dương";
                        }
                        else
                        {
                            Staff staffFound = staffRepository.findAllByMSNV(MSNVInt);
                            if (staffFound == null)
                            {
                                System.out.println("Mã số nhân viên không tồn tại");
                            }
                            else
                            {
                                User userFound = userRepository.findByUsername(userNew.getUsername());
                                if (userFound != null)
                                {
                                    errorMessage = "Tên đăng nhập đã tồn tại";
                                }
                                else
                                {
                                    if (!userNew.getUsername().matches("[a-zA-Z0-9]")) // Kiểm tra nếu tên đăng nhập chỉ chứa a-z, A-Z, 0-9
                                    {
                                        errorMessage = "Tên đăng nhập chỉ chứa các kí tự 0-9, a-z, A-Z";
                                    }
                                    else
                                    {
                                        userNew.setRole("USER");
                                        staffFound.setMSTK(userNew.getMSTK());
                                        staffRepository.save(staffFound);
                                        userRepository.save(userNew);
                                        message = "Tài khoản đã được thêm thành công";
                                    }
                                }
                            }
                        }
                    }
                }
            }
            userList = userRepository.findAll();
            List<User> userListFilter = new ArrayList<>();
            for (User userValue : userList)
            {
                if (userValue != null)
                {
                    if (!userValue.getUsername().equals("admin"))
                    {
                        userListFilter.add(userValue);
                    }
                }
            }
            System.out.println(userListFilter.size());
            staffList = staffRepository.findAll();
            laborContactList = laborContactRepository.findAll();
            nhanVienList = nhanVienRepository.findAllByVaiTroTaiKhoan("USER");
            chucVuList = chucVuRepository.findAll();
            List<HopDong> hopDongFilterList = new ArrayList<>();
            model.addAttribute("userList", userListFilter);
            model.addAttribute("username", authentication.getName());
            model.addAttribute("staffList", staffList);
            model.addAttribute("laborContactList", laborContactList);
            model.addAttribute("localDate", LocalDate.now());
            model.addAttribute("staffNew", new Staff());
            model.addAttribute("laborContactNew", new LaborContact());
            model.addAttribute("check", 1);
            model.addAttribute("userNew", new User());
            model.addAttribute("errorMessage", errorMessage);
            model.addAttribute("message", message);
            model.addAttribute("nhanVienList", nhanVienList);
            model.addAttribute("nhanVienNew", new NhanVien());
            model.addAttribute("chucVuList", chucVuList);
            model.addAttribute("phongBanList", phongBanRepository.findAll());
            model.addAttribute("hopDongList", hopDongRepository.findAll());
            model.addAttribute("hopDongFilterList", hopDongFilterList);
            model.addAttribute("chucVuEdit", new ChucVu());
            model.addAttribute("chucVuNew", new ChucVu());
            model.addAttribute("phongBanEdit", new PhongBan());
            model.addAttribute("phongBanNew", new PhongBan());
            return "homeAdmin";
        }
        else if (isUser) // Nếu người sử dụng là User
        {
            if (nhanVienEdit != null && action != null)
            {
                if (nhanVienEdit.getMSNV() != null && action.equals("editInfoUserConfirm"))
                {
                    NhanVien nhanVienFound = nhanVienRepository.findAllByMSNV(nhanVienEdit.getMSNV());
                    if (!nhanVienEdit.getMatKhau().equals(""))
                    {
                        nhanVienEdit.setMatKhau(passwordEncoder.encode(nhanVienEdit.getMatKhau()));
                        nhanVienFound.setSDT(nhanVienEdit.getSDT());
                        nhanVienFound.setEmail(nhanVienEdit.getEmail());
                        nhanVienFound.setDiaChiHienTai(nhanVienEdit.getDiaChiHienTai());
                        nhanVienFound.setQueQuan(nhanVienEdit.getQueQuan());
                        nhanVienFound.setNgaySinh(nhanVienEdit.getNgaySinh());
                        nhanVienFound.setGioiTinh(nhanVienEdit.getGioiTinh());
                        nhanVienFound.setDanToc(nhanVienEdit.getDanToc());
                        nhanVienFound.setSoThich(nhanVienEdit.getSoThich());
                    }
                    else
                    {
                        nhanVienFound.setSDT(nhanVienEdit.getSDT());
                        nhanVienFound.setEmail(nhanVienEdit.getEmail());
                        nhanVienFound.setDiaChiHienTai(nhanVienEdit.getDiaChiHienTai());
                        nhanVienFound.setQueQuan(nhanVienEdit.getQueQuan());
                        nhanVienFound.setNgaySinh(nhanVienEdit.getNgaySinh());
                        nhanVienFound.setGioiTinh(nhanVienEdit.getGioiTinh());
                        nhanVienFound.setDanToc(nhanVienEdit.getDanToc());
                        nhanVienFound.setSoThich(nhanVienEdit.getSoThich());
                    }
                    if (avatarEdit != null) // Nếu nhân viên cập nhật ảnh đại diện
                    {
                        if (!avatarEdit.isEmpty())
                        {
                            String filePathSave = System.getProperty("user.dir") + "\\src\\main\\resources\\static\\img\\" + StringUtils.cleanPath(avatarEdit.getOriginalFilename());
                            try {
                                byte[] bytes  = avatarEdit.getBytes();
                                Files.write(Paths.get(filePathSave), bytes);
                                System.out.println(StringUtils.cleanPath(avatarEdit.getOriginalFilename()));
                                nhanVienFound.setAnhDaiDien(StringUtils.cleanPath(avatarEdit.getOriginalFilename()));
                                nhanVienRepository.save(nhanVienFound);
                                message = "Đã lưu thông tin nhân viên sau chỉnh sửa, ảnh đại diện đã được cập nhật";
                            }
                            catch (Exception e)
                            {
                                message = "Không thể lưu ảnh của nhân viên";
                            }
                        }
                    }
                    nhanVienRepository.save(nhanVienFound);
                }
            }
            Integer MSNVUSER = userDetails.getMSNV();
            NhanVien nhanVien = nhanVienRepository.findAllByMSNV(MSNVUSER);
            model.addAttribute("hoTen", nhanVien.getHoTen());
            model.addAttribute("congViecHienTai", nhanVien.getCongViecHienTai());
            model.addAttribute("email", nhanVien.getEmail());
            model.addAttribute("SDT", nhanVien.getSDT());
            model.addAttribute("diaChiHienTai", nhanVien.getDiaChiHienTai());
            model.addAttribute("queQuan", nhanVien.getQueQuan());
            model.addAttribute("ngaySinh", nhanVien.getNgaySinh());
            model.addAttribute("gioiTinh", nhanVien.getGioiTinh());
            model.addAttribute("soThich", nhanVien.getSoThich());
            model.addAttribute("danToc", nhanVien.getDanToc());
            model.addAttribute("CMT", nhanVien.getCMT());
            //model.addAttribute("soNgayLamViec", nhanVien.getSoNgayLamViec());
            model.addAttribute("tenDangNhap", authentication.getName());
            model.addAttribute("MSNV", MSNVUSER);
            model.addAttribute("anhDaiDien", nhanVien.getAnhDaiDien());
            List<Luong> luongNhanVienList = luongRepository.findAllByMSNV(userDetails.getMSNV());
            List<PhongBan> phongBanList1 = phongBanRepository.findAll();
            List<ChucVu> chucVuList1 = chucVuRepository.findAll();
            List<ChiTietChucVu> chiTietChucVuList1 = chiTietChucVuRepository.findAllByMSNVOrderByNgayBatDauCVDesc(userDetails.getMSNV());
            List<HopDong> hopDongFoundList;
            ChucVu chucVu = new ChucVu();
            PhongBan phongBan = new PhongBan();

            for (PhongBan phongBan1 : phongBanList1)
            {
                if (phongBan1.getMaPB() == nhanVien.getMaPB())
                {
                    phongBan = phongBan1;
                    break;
                }
            }
            hopDongFoundList = hopDongRepository.findAllByMSNV(nhanVien.getMSNV());
            HopDong hopDongChoose = new HopDong();
            if (hopDongFoundList != null)
            {
                if (hopDongFoundList.size() != 0)
                {
                    model.addAttribute("noHopDong", "no");
                    Integer maHDChoose = hopDongFoundList.get(0).getMaHD();
                    LocalDate newestAdd = hopDongFoundList.get(0).getThoiGianThemHD();
                    for (int i = 0; i < hopDongFoundList.size(); i++)
                    {
                        if (newestAdd.isBefore(hopDongFoundList.get(i).getThoiGianThemHD()))
                        {
                            newestAdd = hopDongFoundList.get(i).getThoiGianThemHD();
                            maHDChoose = hopDongFoundList.get(i).getMaHD();
                        }
                    }
                    hopDongChoose = hopDongRepository.findAllByMaHD(maHDChoose);
                    if (hopDongChoose.getMaHD() != null)
                    {
                        if (hopDongChoose.getNgayKetThucHD().isBefore(LocalDate.now()))
                        {
                            model.addAttribute("hopDongIsExpired", "yes");
                        }
                        else
                        {
                            model.addAttribute("hopDongIsExpired", "no");
                        }
                        model.addAttribute("hopDong", hopDongChoose);
                    }
                }
            }
            else
            {
                model.addAttribute("noHopDong", "yes");
            }
            NhanVien nhanVien1 = nhanVienRepository.findAllByMSNV(userDetails.getMSNV());
            model.addAttribute("nhanVien", nhanVien1);
            model.addAttribute("luongNhanVienList", luongNhanVienList);
            model.addAttribute("phongBan", phongBan);
            model.addAttribute("chucVu", chucVu);
            model.addAttribute("chucVuList", chucVuList1);
            model.addAttribute("chiTietChucVuList", chiTietChucVuList1);
            //model.addAttribute("luong", nhanVien.getSoNgayLamViec());
            LaborContact laborContract = laborContactRepository.findAllByMSNV(MSNVUSER);
            List<HopDong> hopDongList1 = hopDongRepository.findAllByMSNV(nhanVien.getMSNV());
            model.addAttribute("hopDongList", hopDongList1);
            model.addAttribute("pageView", "" + 1);
            if (pageView != null)
            {
                model.addAttribute("pageView", "" + pageView);
            }
            return "homeUser";
        }
        return "login";
    }
    @RequestMapping(value = {"/editLaborContact"})
    public String editLaborContact(Model model, HttpServletRequest request, @RequestParam(required = false) String MSHD,
                                   @RequestParam(required = false) String MSNV)
    {
        boolean isAdmin = request.isUserInRole("ROLE_ADMIN");
        boolean isUser = request.isUserInRole("ROLE_USER");
        List<Staff> staffList;
        List<LaborContact> laborContactList;

        if (isAdmin)
        {
            if (MSNV != null && MSHD != null)
            {
                model.addAttribute("hopDong", hopDongRepository.findAllByMaHD(Integer.parseInt(MSHD)));
                model.addAttribute("nhanVien", nhanVienRepository.findAllByMSNV(Integer.parseInt(MSNV)));
                model.addAttribute("hopDongEdit", new HopDong());
                return "editLaborContact";
            }
            return "editLaborContact";
        }
        else
        {
            return "403";
        }
    }
    @RequestMapping(value = {"/addStaff"})
    public String addStaff(Model model, HttpServletRequest request,
                            @ModelAttribute Staff staffEdit)
    {
        boolean isAdmin = request.isUserInRole("ROLE_ADMIN");
        boolean isUser = request.isUserInRole("ROLE_USER");
        Staff staff;
        List<ChucVu> chucVuList = chucVuRepository.findAll();
        List<PhongBan> phongBanList = phongBanRepository.findAll();
        if (isAdmin)
        {
            model.addAttribute("nhanVienNew", new NhanVien());
            model.addAttribute("chucVuList", chucVuList);
            model.addAttribute("phongBanList", phongBanList);
        }
        return "addStaff";
    }
    @RequestMapping(value = {"/loginInfo"})
    public String loginInfo(Model model, HttpServletRequest request,
                            @RequestParam(required = false) String MSNV)
    {
        boolean isAdmin = request.isUserInRole("ROLE_ADMIN");
        boolean isUser = request.isUserInRole("ROLE_USER");
        if (isAdmin)
        {
            if (MSNV != null)
            {
                NhanVien nhanVienFound = nhanVienRepository.findAllByMSNV(Integer.parseInt(MSNV));
                if (nhanVienFound != null)
                {
                    model.addAttribute("nhanVien", nhanVienFound);
                }
            }
            //model.addAttribute("hopDongNew", new HopDong());
            return "loginInfo";
        }
        return "loginInfo";
    }
    @RequestMapping(value = {"/officeDepartmentInfo"})
    public String officeDepartmentInfo(Model model, HttpServletRequest request,
                            @RequestParam(required = false) String MSNV)
    {
        boolean isAdmin = request.isUserInRole("ROLE_ADMIN");
        boolean isUser = request.isUserInRole("ROLE_USER");
        if (isAdmin)
        {
            if (MSNV != null)
            {
                NhanVien nhanVienFound = nhanVienRepository.findAllByMSNV(Integer.parseInt(MSNV));
                List<ChucVu> chucVuList = chucVuRepository.findAll();
                if (nhanVienFound != null)
                {
                    model.addAttribute("nhanVien", nhanVienFound);
                }
                List<ChiTietChucVu> chiTietChucVuList = chiTietChucVuRepository.findAllByMSNVOrderByNgayBatDauCVDesc(nhanVienFound.getMSNV());
                PhongBan phongBan = phongBanRepository.findAllByMaPB(nhanVienFound.getMaPB());
                List<PhongBan> phongBanList = phongBanRepository.findAll();
                model.addAttribute("chiTietChucVuNhanVienList", chiTietChucVuList);
                model.addAttribute("phongBanNhanVien", phongBan);
                model.addAttribute("chucVuList", chucVuList);
                model.addAttribute("chiTietChucVuEdit", new ChiTietChucVu());
                model.addAttribute("phongBanEdit", new PhongBan());
                model.addAttribute("phongBanList", phongBanList);
                if (chiTietChucVuList != null)
                {
                    if (chiTietChucVuList.size() > 0)
                    {
                        model.addAttribute("chucVuFound", "yes");
                        model.addAttribute("chiTietChucVuHienTai", chiTietChucVuList.get(0));
                    }
                    else
                    {
                        model.addAttribute("chucVuFound", "no");
                    }
                }
                model.addAttribute("chiTietChucVuNew", new ChiTietChucVu());
            }
            //model.addAttribute("hopDongNew", new HopDong());
            return "officeDepartmentInfo";
        }
        return "officeDepartmentInfo";
    }
    @RequestMapping(value = {"/salaryInfo"})
    public String salaryInfo(Model model, HttpServletRequest request,
                                       @RequestParam(required = false) String MSNV)
    {
        boolean isAdmin = request.isUserInRole("ROLE_ADMIN");
        boolean isUser = request.isUserInRole("ROLE_USER");
        if (isAdmin)
        {
            if (MSNV != null)
            {
                NhanVien nhanVienFound = nhanVienRepository.findAllByMSNV(Integer.parseInt(MSNV));
                List<ChucVu> chucVuList = chucVuRepository.findAll();
                List<Luong> luongList = luongRepository.findAllByMSNV(Integer.parseInt(MSNV));
                if (nhanVienFound != null)
                {
                    model.addAttribute("nhanVien", nhanVienFound);
                }
                List<ChiTietChucVu> chiTietChucVuList = chiTietChucVuRepository.findAllByMSNVOrderByNgayBatDauCVDesc(nhanVienFound.getMSNV());
                PhongBan phongBan = phongBanRepository.findAllByMaPB(nhanVienFound.getMaPB());
                model.addAttribute("chiTietChucVuNhanVienList", chiTietChucVuList);
                model.addAttribute("phongBanNhanVien", phongBan);
                model.addAttribute("chucVuList", chucVuList);
                model.addAttribute("chiTietChucVuEdit", new ChiTietChucVu());
                if (luongList != null)
                {
                    if (luongList.size() > 0)
                    {
                        model.addAttribute("luongFound", "yes");
                        model.addAttribute("luongNhanVienList", luongList);
                    }
                    else
                    {
                        model.addAttribute("luongFound", "no");
                    }
                }
                model.addAttribute("luongEdit", new Luong());
                model.addAttribute("luongNew", new Luong());
            }
            //model.addAttribute("hopDongNew", new HopDong());
            return "salaryInfo";
        }
        return "salaryInfo";
    }
    @RequestMapping(value = {"/contractInfo"})
    public String contractInfo(Model model, HttpServletRequest request,
                               @RequestParam(required = false) String MSNV)
    {
        boolean isAdmin = request.isUserInRole("ROLE_ADMIN");
        boolean isUser = request.isUserInRole("ROLE_USER");
        if (isAdmin)
        {
            if (MSNV != null)
            {
                NhanVien nhanVienFound = nhanVienRepository.findAllByMSNV(Integer.parseInt(MSNV));
                if (nhanVienFound != null)
                {
                    model.addAttribute("nhanVien", nhanVienFound);
                    List<HopDong> hopDongList = hopDongRepository.findAllByMSNV(nhanVienFound.getMSNV());
                    if (hopDongList != null)
                    {
                        model.addAttribute("hopDongFound", "yes");
                        model.addAttribute("hopDongNhanVienList", hopDongList);
                    }
                    else
                    {
                        model.addAttribute("hopDongFound", "no");
                    }
                    model.addAttribute("hopDongEdit", new HopDong());
                    model.addAttribute("hopDongNew", new HopDong());
                }
            }
            //model.addAttribute("hopDongNew", new HopDong());
            return "contractInfo";
        }
        return "contractInfo";
    }
    @RequestMapping(value = {"/staffInfo"})
    public String staffInfo(Model model, HttpServletRequest request,
                            @RequestParam(required = false) String MSNV,
                            @ModelAttribute Staff staffEdit)
    {
        boolean isAdmin = request.isUserInRole("ROLE_ADMIN");
        boolean isUser = request.isUserInRole("ROLE_USER");
        Staff staff;
        if (isAdmin)
        {
            if (MSNV != null)
            {
                Integer MSNVInteger = Integer.parseInt(MSNV);
                NhanVien nhanVien = nhanVienRepository.findAllByMSNV(MSNVInteger);
                List<PhongBan> phongBanList = phongBanRepository.findAll();
                List<ChucVu> chucVuList = chucVuRepository.findAll();
                if (nhanVien != null)
                {
                    model.addAttribute("nhanVien", nhanVien);
                    Staff staffEditValue = new Staff();
                    model.addAttribute("nhanVienEdit", new NhanVien());
                    //ChucVu chucVuFound = chucVuRepository.findAllByMaCV(nhanVien.getMaCV());
                    PhongBan phongBanFound = phongBanRepository.findAllByMaPB(nhanVien.getMaPB());
                    List<ChiTietChucVu> chiTietChucVuList = chiTietChucVuRepository.findAllByMSNVOrderByNgayBatDauCVDesc(MSNVInteger);
                    ChucVu chucVuNhanVien = chucVuRepository.findAllByMaCV(chiTietChucVuList.get(0).getMaCV());
                    //model.addAttribute("chucVuNhanVien", chucVuFound);
                    model.addAttribute("phongBanNhanVien", phongBanFound);
                    model.addAttribute("phongBanList", phongBanList);
                    model.addAttribute("chucVuList", chucVuList);
                    model.addAttribute("chucVuNhanVien", chucVuNhanVien);
                }
            }

        }
        return "staffInfo";
    }
    @RequestMapping(value = {"/editUser"})
    public String editUser(Model model, HttpServletRequest request,
                            @RequestParam(required = false) String MSNV)
    {
        boolean isAdmin = request.isUserInRole("ROLE_ADMIN");
        boolean isUser = request.isUserInRole("ROLE_USER");
        if (isAdmin)
        {
            if (MSNV != null)
            {
                NhanVien nhanVien = nhanVienRepository.findAllByMSNV(Integer.parseInt(MSNV));
                model.addAttribute("nhanVienEdit", nhanVien);
                model.addAttribute("isAdmin", "yes");
                //System.out.println(MSTKInt);
            }
        }
        else if (isUser)
        {
            if (MSNV != null)
            {
                NhanVien nhanVien = nhanVienRepository.findAllByMSNV(Integer.parseInt(MSNV));
                model.addAttribute("nhanVienEdit", nhanVien);
                model.addAttribute("isAdmin", "no");
                //System.out.println(MSTKInt);
            }
        }
        return "editUser";
    }
    @RequestMapping(value = {"/staffDetail"})
    public String staffDetail(Model model, HttpServletRequest request,
                              @RequestParam(required = false) String MSNV,
                              @RequestParam(required = false) String maCVDelete,
                              @RequestParam(required = false) String ngayBatDauCV)
    {
        boolean isAdmin = request.isUserInRole("ROLE_ADMIN");
        boolean isUser = request.isUserInRole("ROLE_USER");
        if (isAdmin)
        {
            NhanVien nhanVienFound = nhanVienRepository.findAllByMSNV(Integer.parseInt(MSNV));
            if (nhanVienFound != null)
            {
                if (maCVDelete != null & ngayBatDauCV != null)
                {
                    ChiTietChucVu chiTietChucVuFound = chiTietChucVuRepository.findAllByMSNVAndMaCVAndNgayBatDauCV(Integer.parseInt(MSNV), Integer.parseInt(maCVDelete), LocalDate.parse(ngayBatDauCV));
                    if (chiTietChucVuFound != null)
                    {
                        chiTietChucVuRepository.delete(chiTietChucVuFound);
                    }
                }
                model.addAttribute("nhanVien", nhanVienFound);
                //ChucVu chucVuFound = chucVuRepository.findAllByMaCV(nhanVienFound.getMaCV());
                PhongBan phongBanFound = phongBanRepository.findAllByMaPB(nhanVienFound.getMaPB());
                List<ChiTietChucVu> chiTietChucVuFoundList = chiTietChucVuRepository.findAllByMSNVOrderByNgayBatDauCVDesc(Integer.parseInt(MSNV));
                ChiTietChucVu chiTietChucVuFound = chiTietChucVuRepository.findAllByMSNVOrderByNgayBatDauCVDesc(Integer.parseInt(MSNV)).get(0);
                List<ChucVu> chucVuList = chucVuRepository.findAll();
                /*if (chucVuFound != null)
                {
                    model.addAttribute("chucVuNhanVien", chucVuFound);
                }*/
                if (phongBanFound != null)
                {
                    model.addAttribute("phongBanNhanVien", phongBanFound);
                }
                if (chiTietChucVuFound != null)
                {
                    model.addAttribute("chiTietChucVuNhanVien", chiTietChucVuFound);
                }
                model.addAttribute("chucVuList", chucVuList);
                model.addAttribute("chiTietChucVuNhanVienList", chiTietChucVuFoundList);
            }
        }
        return "staffInfo";
    }
    @RequestMapping(value = {"/salaryReport"})
    public String salaryReport(Model model, HttpServletRequest request)
    {
        boolean isAdmin = request.isUserInRole("ROLE_ADMIN");
        boolean isUser = request.isUserInRole("ROLE_USER");
        List<NhanVien> nhanVienListFound = nhanVienRepository.findAllByVaiTroTaiKhoan("USER");
        List<HopDong> hopDongFoundList;
        List<HopDong> hopDongFilterList = new ArrayList<>();
        float tongLuong = 0;
        if (isAdmin)
        {
            for (NhanVien nhanVien : nhanVienListFound)
            {
                tongLuong += nhanVien.getLuongCoBan() * (nhanVien.getHeSoLuong() + nhanVien.getHeSoPhuCap());
            }
            model.addAttribute("hopDongFilterList", hopDongFilterList);
            model.addAttribute("nhanVienList", nhanVienListFound);
            model.addAttribute("tongLuong", tongLuong);
            Date date = new Date();
            LocalDate localDate = date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
            model.addAttribute("month", localDate.getMonthValue());
            model.addAttribute("day", localDate.getDayOfMonth());
            model.addAttribute("year", localDate.getYear());
        }
        return "salaryReport";
    }
    @RequestMapping(value = {"/laborContractReport"})
    public String laborContractReport(Model model, HttpServletRequest request)
    {
        boolean isAdmin = request.isUserInRole("ROLE_ADMIN");
        boolean isUser = request.isUserInRole("ROLE_USER");
        List<Staff> staffList;
        List<LaborContact> laborContactList;
        List<Staff> staffNoLC = new ArrayList<>();
        List<Staff> staffHaveLCList = new ArrayList<>();
        List<NhanVien> nhanVienList = new ArrayList<>();
        List<ChucVu> chucVuList = chucVuRepository.findAll();
        List<HopDong> hopDongFilterList = new ArrayList<>();
        List<HopDong> hopDongExpiredList = new ArrayList<>();
        List<NhanVien> nhanVienNoHopDongList = new ArrayList<>();
        List<PhongBan> phongBanList = phongBanRepository.findAll();
        if (isAdmin)
        {
            nhanVienList = nhanVienRepository.findAllByVaiTroTaiKhoan("USER");
            chucVuList = chucVuRepository.findAll();
            for (NhanVien nhanVien : nhanVienList)
            {
                List<HopDong> hopDongFoundList = hopDongRepository.findAllByMSNV(nhanVien.getMSNV());
                if (hopDongFoundList.size() != 0)
                {
                    boolean isExpired = false;
                    Integer maHDChoose = hopDongFoundList.get(0).getMaHD();
                    LocalDate newestAdd = hopDongFoundList.get(0).getThoiGianThemHD();
                    if (hopDongFoundList.get(0).getNgayKetThucHD().isBefore(LocalDate.now()))
                    {
                        isExpired = true;
                    }
                    for (int i = 0; i < hopDongFoundList.size(); i++)
                    {
                        if (newestAdd.isBefore(hopDongFoundList.get(i).getThoiGianThemHD()))
                        {
                            newestAdd = hopDongFoundList.get(i).getThoiGianThemHD();
                            maHDChoose = hopDongFoundList.get(i).getMaHD();
                        }
                    }
                    HopDong hopDongChoose = hopDongRepository.findAllByMaHD(maHDChoose);
                    if (hopDongChoose.getNgayKetThucHD().isBefore(LocalDate.now()))
                    {
                        hopDongExpiredList.add(hopDongChoose);
                    }
                    else
                    {
                        hopDongFilterList.add(hopDongChoose);
                    }
                }
                else
                {
                    nhanVienNoHopDongList.add(nhanVien);
                }
            }
            Date date = new Date();
            LocalDate localDate = date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
            model.addAttribute("month", localDate.getMonthValue());
            model.addAttribute("day", localDate.getDayOfMonth());
            model.addAttribute("year", localDate.getYear());
            model.addAttribute("hopDongFilterList", hopDongFilterList);
            model.addAttribute("hopDongExpiredList", hopDongExpiredList);
            model.addAttribute("nhanVienNoHopDongList", nhanVienNoHopDongList);
            model.addAttribute("nhanVienList", nhanVienList);
            model.addAttribute("chucVuList", chucVuList);
            model.addAttribute("phongBanList", phongBanList);
        }
        return "laborContractReport";
    }
    @RequestMapping(value = {"/addLaborContract"})
    public String addLaborContract(Model model, HttpServletRequest request)
    {
        boolean isAdmin = request.isUserInRole("ROLE_ADMIN");
        boolean isUser = request.isUserInRole("ROLE_USER");
        if (isAdmin)
        {
            model.addAttribute("hopDongNew", new HopDong());
            return "addLaborContract";
        }
        return "addLaborContract";
    }
    @RequestMapping(value = {"/changePasswordAdmin"})
    public String changePasswordAdmin(Model model, HttpServletRequest request)
    {
        boolean isAdmin = request.isUserInRole("ROLE_ADMIN");
        boolean isUser = request.isUserInRole("ROLE_USER");
        List<NhanVien> admin = nhanVienRepository.findAllByVaiTroTaiKhoan("ADMIN");
        if (isAdmin)
        {
            model.addAttribute("hopDongNew", new HopDong());
            model.addAttribute("admin", admin.get(0));
            return "changePasswordAdmin";
        }
        return "changePasswordAdmin";
    }
    @RequestMapping(value = {"/diligenceReport"})
    public String diligenceReport(Model model, HttpServletRequest request)
    {
        boolean isAdmin = request.isUserInRole("ROLE_ADMIN");
        boolean isUser = request.isUserInRole("ROLE_USER");
        List<NhanVien> nhanVienList = nhanVienRepository.findAllByVaiTroTaiKhoan("USER");
        List<ChucVu> chucVuList = chucVuRepository.findAll();
        List<PhongBan> phongBanList = phongBanRepository.findAll();
        if (isAdmin)
        {
            Date date = new Date();
            LocalDate localDate = date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
            model.addAttribute("nhanVienList", nhanVienList);
            model.addAttribute("chucVuList", chucVuList);
            model.addAttribute("phongBanList", phongBanList);
            model.addAttribute("month", localDate.getMonthValue());
            model.addAttribute("day", localDate.getDayOfMonth());
            model.addAttribute("year", localDate.getYear());
        }
        return "diligenceReport";
    }
    @RequestMapping(value = {"/editUserOU"})
    public String editUserOU(Model model, HttpServletRequest request)
    {
        boolean isAdmin = request.isUserInRole("ROLE_ADMIN");
        boolean isUser = request.isUserInRole("ROLE_USER");
        if (isUser)
        {

        }
        return "editUserOU";
    }
    @GetMapping("/hello")
    public String hello() {
        return "hello"; // Trả về hello.html
    }
    @RequestMapping("/user")
    public String user() {
        return "user";
    }
    @RequestMapping("/403")
    public String accessDenied() {
        return "403";
    }
    @RequestMapping("/login")
    public String login(Model model, HttpServletRequest request) {
        return "login";
    }
    @RequestMapping("/login-error")
    public String loginError(Model model) {
        model.addAttribute("loginError", "Sai tên đăng nhập hoặc mât khẩu");
        return "login";
    }
    @RequestMapping("/welcome")
    public String logout(Model model) {
        System.out.println("logout");
        return "welcome";
    }
    /*@ExceptionHandler({Exception.class})
    public String handleCarException(Exception e) {
        return "error";
    }*/
}
