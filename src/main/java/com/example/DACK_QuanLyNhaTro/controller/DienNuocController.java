package com.example.DACK_QuanLyNhaTro.controller;

import com.example.DACK_QuanLyNhaTro.entity.DienNuoc;
import com.example.DACK_QuanLyNhaTro.service.DienNuocService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/dien-nuoc")
public class DienNuocController {

    private final DienNuocService dienNuocService;

    public DienNuocController(DienNuocService dienNuocService) {
        this.dienNuocService = dienNuocService;
    }

    @GetMapping
    public String list(Model model) {
        model.addAttribute("dsDienNuoc", dienNuocService.getAll());
        return "diennuoc/list";
    }

    @GetMapping("/them")
    public String showCreateForm(Model model) {
        model.addAttribute("dienNuoc", new DienNuoc());
        model.addAttribute("dsPhong", dienNuocService.getAllPhong());
        model.addAttribute("action", "/dien-nuoc/luu");
        model.addAttribute("title", "Thêm điện nước - dịch vụ");
        return "diennuoc/form";
    }

    @PostMapping("/luu")
    public String save(@ModelAttribute("dienNuoc") DienNuoc dienNuoc,
                       @RequestParam("phongId") Long phongId,
                       Model model) {
        try {
            dienNuocService.save(dienNuoc, phongId);
            return "redirect:/dien-nuoc";
        } catch (Exception e) {
            model.addAttribute("dienNuoc", dienNuoc);
            model.addAttribute("dsPhong", dienNuocService.getAllPhong());
            model.addAttribute("action", "/dien-nuoc/luu");
            model.addAttribute("title", "Thêm điện nước - dịch vụ");
            model.addAttribute("error", e.getMessage());
            return "diennuoc/form";
        }
    }

    @GetMapping("/sua/{id}")
    public String showEditForm(@PathVariable Long id, Model model) {
        model.addAttribute("dienNuoc", dienNuocService.getById(id));
        model.addAttribute("dsPhong", dienNuocService.getAllPhong());
        model.addAttribute("action", "/dien-nuoc/cap-nhat/" + id);
        model.addAttribute("title", "Sửa điện nước - dịch vụ");
        return "diennuoc/form";
    }

    @PostMapping("/cap-nhat/{id}")
    public String update(@PathVariable Long id,
                         @ModelAttribute("dienNuoc") DienNuoc dienNuoc,
                         @RequestParam("phongId") Long phongId,
                         Model model) {
        try {
            dienNuocService.update(id, dienNuoc, phongId);
            return "redirect:/dien-nuoc";
        } catch (Exception e) {
            model.addAttribute("dienNuoc", dienNuoc);
            model.addAttribute("dsPhong", dienNuocService.getAllPhong());
            model.addAttribute("action", "/dien-nuoc/cap-nhat/" + id);
            model.addAttribute("title", "Sửa điện nước - dịch vụ");
            model.addAttribute("error", e.getMessage());
            return "diennuoc/form";
        }
    }

    @GetMapping("/xoa/{id}")
    public String delete(@PathVariable Long id) {
        dienNuocService.delete(id);
        return "redirect:/dien-nuoc";
    }
}