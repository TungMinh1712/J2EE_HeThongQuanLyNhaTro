package com.example.DACK_QuanLyNhaTro.controller;

import com.example.DACK_QuanLyNhaTro.entity.HopDong;
import com.example.DACK_QuanLyNhaTro.entity.KhachThue;
import com.example.DACK_QuanLyNhaTro.service.HopDongService;
import com.example.DACK_QuanLyNhaTro.service.KhachThueService;
import com.example.DACK_QuanLyNhaTro.service.PhongService;
import com.example.DACK_QuanLyNhaTro.entity.Phong;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/hop-dong")
@RequiredArgsConstructor
public class HopDongController {

    private final HopDongService hopDongService;
    private final PhongService phongService;
    private final KhachThueService khachThueService;

    @GetMapping
    public String hienThiDanhSach(Model model) {
        model.addAttribute("danhSachHopDong", hopDongService.layTatCa());
        return "hopdong/list";
    }


    @GetMapping("/add")
    public String hienThiFormThem(Model model) {
        HopDong hopDong = new HopDong();
        hopDong.setPhong(new Phong());
        hopDong.setKhachThue(new KhachThue());

        model.addAttribute("hopDong", hopDong);
        model.addAttribute("danhSachPhong",
                phongService.layTheoTrangThai(Phong.TrangThaiPhong.CON_TRONG));
        model.addAttribute("danhSachKhachThue", khachThueService.layTatCa());
        return "hopdong/add";
    }

    @PostMapping("/save")
    public String themHopDong(@ModelAttribute("hopDong") HopDong hopDong) {
        hopDongService.taoMoi(hopDong);
        return "redirect:/hop-dong";
    }

    @GetMapping("/edit/{id}")
    public String hienThiFormSua(@PathVariable Long id, Model model) {
        model.addAttribute("hopDong", hopDongService.layTheoId(id));
        model.addAttribute("danhSachPhong", phongService.layTatCa());
        model.addAttribute("danhSachKhachThue", khachThueService.layTatCa());
        return "hopdong/edit";
    }

    @PostMapping("/update/{id}")
    public String capNhatHopDong(@PathVariable Long id,
                                 @ModelAttribute("hopDong") HopDong hopDong) {
        hopDongService.capNhat(id, hopDong);
        return "redirect:/hop-dong";
    }

    @GetMapping("/delete/{id}")
    public String xoaHopDong(@PathVariable Long id) {
        hopDongService.xoa(id);
        return "redirect:/hop-dong";
    }
}