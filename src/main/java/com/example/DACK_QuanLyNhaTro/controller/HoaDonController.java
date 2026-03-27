package com.example.DACK_QuanLyNhaTro.controller;

import com.example.DACK_QuanLyNhaTro.entity.HoaDon;
import com.example.DACK_QuanLyNhaTro.services.HoaDonService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class HoaDonController {

    private final HoaDonService service;
    public HoaDonController(HoaDonService service) { this.service = service; }

    // ==========================================
    // PHẦN DÀNH CHO ADMIN
    // ==========================================

    @GetMapping("/admin/hoadon")
    public String adminList(Model model) {
        model.addAttribute("list", service.getAll());
        return "admin/hoadon-list";
    }

    @GetMapping("/admin/hoadon/add")
    public String showAddForm(Model model) {
        HoaDon hoadon = new HoaDon();
        hoadon.setNam(2026);
        model.addAttribute("hoadon", hoadon);
        return "admin/hoadon-add";
    }

    @PostMapping("/admin/hoadon/save")
    public String saveHoaDon(@ModelAttribute("hoadon") HoaDon hoadon) {
        service.save(hoadon);
        return "redirect:/admin/hoadon";
    }

    @GetMapping("/admin/hoadon/detail/{id}")
    public String adminDetail(@PathVariable Long id, Model model) {
        model.addAttribute("hd", service.findById(id));
        return "admin/hoadon-detail";
    }

    @GetMapping("/admin/hoadon/delete/{id}")
    public String deleteHoaDon(@PathVariable Long id) {
        service.deleteById(id);
        return "redirect:/admin/hoadon";
    }

    @GetMapping("/admin/hoadon/update-status/{id}")
    public String updateStatus(@PathVariable Long id) {
        service.capNhatTrangThai(id);
        return "redirect:/admin/hoadon";
    }

    // ==========================================
    // PHẦN DÀNH CHO USER (NGƯỜI THUÊ)
    // ==========================================

    // 1. Danh sách hóa đơn cho User (URL: /hoadon/user)
    @GetMapping("/hoadon/user")
    public String userList(Model model) {
        model.addAttribute("list", service.getAll());
        return "user/hoadon-list";
    }

    // 2. Trang quét mã QR thanh toán (URL: /hoadon/user/payment/{id})
    @GetMapping("/hoadon/user/payment/{id}")
    public String userPayment(@PathVariable Long id, Model model) {
        HoaDon hd = service.findById(id);
        if (hd == null) return "redirect:/hoadon/user";
        model.addAttribute("hd", hd);
        return "user/hoadon-payment";
    }

    // 3. Xử lý khi User bấm "Xác nhận đã chuyển khoản" (URL: /hoadon/user/pay/{id})
    @GetMapping("/hoadon/user/pay/{id}")
    public String userConfirmPay(@PathVariable Long id) {
        service.capNhatTrangThai(id);
        return "redirect:/hoadon/user";
    }
}