package com.example.DACK_QuanLyNhaTro.controller;

import com.example.DACK_QuanLyNhaTro.entity.Phong;
import com.example.DACK_QuanLyNhaTro.entity.Phong.TrangThai; // ⭐ QUAN TRỌNG
import com.example.DACK_QuanLyNhaTro.service.PhongService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;

@Controller
@RequestMapping("/phong")
public class PhongController {

    private final PhongService service;

    public PhongController(PhongService service) {
        this.service = service;
    }

    // Hiển thị danh sách
    @GetMapping
    public String list(Model model) {
        model.addAttribute("list", service.getAll());
        return "phong/list";
    }

    // Form thêm
    @GetMapping("/add")
    public String add(Model model) {
        model.addAttribute("phong", new Phong());
        model.addAttribute("trangThais", TrangThai.values());
        return "phong/add"; // 👉 đổi sang add.html nếu bạn tách riêng
    }

    // Lưu (thêm + sửa)
    @PostMapping("/save")
    public String save(@ModelAttribute Phong phong,
                       @RequestParam("file") MultipartFile file) throws Exception {

        if (!file.isEmpty()) {
            File dir = new File("uploads");
            if (!dir.exists()) dir.mkdirs();

            String fileName = file.getOriginalFilename();
            file.transferTo(new File("uploads/" + fileName));
            phong.setAnh(fileName);
        }

        service.save(phong);
        return "redirect:/phong";
    }

    // Form sửa
    @GetMapping("/edit/{id}")
    public String edit(@PathVariable Integer id, Model model) {
        model.addAttribute("phong", service.getById(id));
        model.addAttribute("trangThais", TrangThai.values());
        return "phong/edit"; // 👉 hoặc "phong/edit" nếu bạn tách riêng
    }

    // Xóa
    @GetMapping("/delete/{id}")
    public String delete(@PathVariable Integer id) {
        service.delete(id);
        return "redirect:/phong";
    }

    // Xem chi tiết
    @GetMapping("/view/{id}")
    public String view(@PathVariable Integer id, Model model) {
        model.addAttribute("phong", service.getById(id));
        return "phong/view";
    }
}