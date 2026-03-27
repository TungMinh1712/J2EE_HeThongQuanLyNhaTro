package com.example.DACK_QuanLyNhaTro.controller;

import com.example.DACK_QuanLyNhaTro.entity.NguoiDung;
import com.example.DACK_QuanLyNhaTro.service.NguoiDungService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/nguoi-dung")
@RequiredArgsConstructor
public class NguoiDungController {

    private final NguoiDungService nguoiDungService;

    @GetMapping
    public String hienThiDanhSach(Model model) {
        model.addAttribute("danhSachNguoiDung", nguoiDungService.layTatCa());
        return "nguoidung/list";
    }

    @GetMapping("/add")
    public String hienThiFormThem(Model model) {
        model.addAttribute("nguoiDung", new NguoiDung());
        return "nguoidung/add";
    }

    @PostMapping("/save")
    public String themNguoiDung(@ModelAttribute("nguoiDung") NguoiDung nguoiDung) {
        nguoiDungService.taoMoi(nguoiDung);
        return "redirect:/nguoi-dung";
    }

    @GetMapping("/edit/{id}")
    public String hienThiFormSua(@PathVariable Long id, Model model) {
        model.addAttribute("nguoiDung", nguoiDungService.layTheoId(id));
        return "nguoidung/edit";
    }

    @PostMapping("/update/{id}")
    public String capNhatNguoiDung(@PathVariable Long id,
                                   @ModelAttribute("nguoiDung") NguoiDung nguoiDung) {
        nguoiDungService.capNhat(id, nguoiDung);
        return "redirect:/nguoi-dung";
    }

    @GetMapping("/delete/{id}")
    public String xoaNguoiDung(@PathVariable Long id) {
        nguoiDungService.xoa(id);
        return "redirect:/nguoi-dung";
    }
}