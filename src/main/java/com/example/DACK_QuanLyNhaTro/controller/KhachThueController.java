package com.example.DACK_QuanLyNhaTro.controller;

import com.example.DACK_QuanLyNhaTro.entity.KhachThue;
import com.example.DACK_QuanLyNhaTro.service.KhachThueService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/khach-thue")
@RequiredArgsConstructor
public class KhachThueController {

    private final KhachThueService khachThueService;

    @GetMapping
    public String hienThiDanhSach(Model model) {
        model.addAttribute("danhSachKhachThue", khachThueService.layTatCa());
        return "khachthue/list";
    }

    @GetMapping("/add")
    public String hienThiFormThem(Model model) {
        model.addAttribute("khachThue", new KhachThue());
        return "khachthue/add";
    }

    @PostMapping("/save")
    public String themKhachThue(@ModelAttribute("khachThue") KhachThue khachThue) {
        khachThueService.taoMoi(khachThue);
        return "redirect:/khach-thue";
    }

    @GetMapping("/edit/{id}")
    public String hienThiFormSua(@PathVariable Long id, Model model) {
        model.addAttribute("khachThue", khachThueService.layTheoId(id));
        return "khachthue/edit";
    }

    @PostMapping("/update/{id}")
    public String capNhatKhachThue(@PathVariable Long id,
                                   @ModelAttribute("khachThue") KhachThue khachThue) {
        khachThueService.capNhat(id, khachThue);
        return "redirect:/khach-thue";
    }

    @GetMapping("/delete/{id}")
    public String xoaKhachThue(@PathVariable Long id) {
        khachThueService.xoa(id);
        return "redirect:/khach-thue";
    }
}