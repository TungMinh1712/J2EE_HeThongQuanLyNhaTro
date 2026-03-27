package com.example.DACK_QuanLyNhaTro.controller;

import com.example.DACK_QuanLyNhaTro.entity.Phong;
import com.example.DACK_QuanLyNhaTro.service.PhongService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/phong")
@RequiredArgsConstructor
public class PhongController {

    private final PhongService phongService;

    @GetMapping
    public String hienThiDanhSach(Model model) {
        model.addAttribute("danhSachPhong", phongService.layTatCa());
        return "phong/list";
    }

    @GetMapping("/add")
    public String hienThiFormThem(Model model) {
        model.addAttribute("phong", new Phong());
        return "phong/add";
    }

    @PostMapping("/save")
    public String themPhong(@ModelAttribute("phong") Phong phong) {
        phongService.taoMoi(phong);
        return "redirect:/phong";
    }

    @GetMapping("/edit/{id}")
    public String hienThiFormSua(@PathVariable Long id, Model model) {
        model.addAttribute("phong", phongService.layTheoId(id));
        return "phong/edit";
    }

    @PostMapping("/update/{id}")
    public String capNhatPhong(@PathVariable Long id,
                               @ModelAttribute("phong") Phong phong) {
        phongService.capNhat(id, phong);
        return "redirect:/phong";
    }

    @GetMapping("/delete/{id}")
    public String xoaPhong(@PathVariable Long id) {
        phongService.xoa(id);
        return "redirect:/phong";
    }

    @GetMapping("/api/{id}")
    @ResponseBody
    public Phong layPhongTheoIdApi(@PathVariable Long id) {
        return phongService.layTheoId(id);
    }
}