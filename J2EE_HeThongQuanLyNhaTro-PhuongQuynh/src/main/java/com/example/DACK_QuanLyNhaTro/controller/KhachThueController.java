package com.example.DACK_QuanLyNhaTro.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.example.DACK_QuanLyNhaTro.Entity.KhachThue;
import com.example.DACK_QuanLyNhaTro.service.KhachThueService;

@Controller
@RequestMapping("/khach-thue")
public class KhachThueController {
    @Autowired
    private KhachThueService service;

    @GetMapping
    public String index(Model model) {
        model.addAttribute("listKhach", service.getAll());
        return "khach/index"; // Trả về file templates/khach/index.html
    }

    @GetMapping("/create")
    public String create(Model model) {
        model.addAttribute("khach", new KhachThue());
        return "khach/add";
    }

    @PostMapping("/save")
    public String save(@ModelAttribute KhachThue khach, 
                       @RequestParam("file") MultipartFile file) {
        service.save(khach, file);
        return "redirect:/khach-thue";
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable Long id) {
        service.delete(id);
        return "redirect:/khach-thue";
    }
}
