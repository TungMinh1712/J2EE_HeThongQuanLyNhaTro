package com.example.DACK_QuanLyNhaTro.controller;

import com.example.DACK_QuanLyNhaTro.entity.ChiSoDienNuoc;
import com.example.DACK_QuanLyNhaTro.service.ChiSoDienNuocService;
import com.example.DACK_QuanLyNhaTro.service.PhongService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/chi-so-dien-nuoc")
@RequiredArgsConstructor
public class ChiSoDienNuocController {

    private final ChiSoDienNuocService chiSoDienNuocService;
    private final PhongService phongService;

    @GetMapping
    public String hienThiDanhSach(Model model) {
        model.addAttribute("danhSachChiSoDienNuoc", chiSoDienNuocService.layTatCa());
        return "diennuoc/list";
    }

    @GetMapping("/add")
    public String hienThiFormThem(Model model) {
        ChiSoDienNuoc chiSoDienNuoc = new ChiSoDienNuoc();
        model.addAttribute("chiSoDienNuoc", chiSoDienNuoc);
        model.addAttribute("danhSachPhong", phongService.layTatCa());
        return "diennuoc/add";
    }

    @PostMapping("/save")
    public String themChiSo(@ModelAttribute("chiSoDienNuoc") ChiSoDienNuoc chiSoDienNuoc) {
        chiSoDienNuocService.taoMoi(chiSoDienNuoc);
        return "redirect:/chi-so-dien-nuoc";
    }

    @GetMapping("/edit/{id}")
    public String hienThiFormSua(@PathVariable Long id, Model model) {
        model.addAttribute("chiSoDienNuoc", chiSoDienNuocService.layTheoId(id));
        model.addAttribute("danhSachPhong", phongService.layTatCa());
        return "diennuoc/edit";
    }

    @PostMapping("/update/{id}")
    public String capNhatChiSo(@PathVariable Long id,
                               @ModelAttribute("chiSoDienNuoc") ChiSoDienNuoc chiSoDienNuoc) {
        chiSoDienNuocService.capNhat(id, chiSoDienNuoc);
        return "redirect:/chi-so-dien-nuoc";
    }

    @GetMapping("/delete/{id}")
    public String xoaChiSo(@PathVariable Long id) {
        chiSoDienNuocService.xoa(id);
        return "redirect:/chi-so-dien-nuoc";
    }
}