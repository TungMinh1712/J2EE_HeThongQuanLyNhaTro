package com.example.DACK_QuanLyNhaTro.controller;

import com.example.DACK_QuanLyNhaTro.entity.HopDong;
import com.example.DACK_QuanLyNhaTro.entity.TrangThaiHopDong;
import com.example.DACK_QuanLyNhaTro.service.HopDongService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;

@Controller
@RequestMapping("/hop-dong")
public class HopDongController {

    private final HopDongService hopDongService;

    public HopDongController(HopDongService hopDongService) {
        this.hopDongService = hopDongService;
    }

    @GetMapping
    public String list(Model model) {
        model.addAttribute("dsHopDong", hopDongService.getAll());
        return "hopdong/list";
    }

    @GetMapping("/them")
    public String showCreateForm(Model model) {
        model.addAttribute("hopDong", new HopDong());
        model.addAttribute("dsTrangThai", Arrays.asList(TrangThaiHopDong.values()));
        model.addAttribute("action", "/hop-dong/luu");
        model.addAttribute("title", "Thêm hợp đồng");
        return "hopdong/form";
    }

    @PostMapping("/luu")
    public String save(@ModelAttribute("hopDong") HopDong hopDong, Model model) {
        try {
            hopDongService.save(hopDong);
            return "redirect:/hop-dong";
        } catch (Exception e) {
            model.addAttribute("hopDong", hopDong);
            model.addAttribute("dsTrangThai", Arrays.asList(TrangThaiHopDong.values()));
            model.addAttribute("action", "/hop-dong/luu");
            model.addAttribute("title", "Thêm hợp đồng");
            model.addAttribute("error", e.getMessage());
            return "hopdong/form";
        }
    }

    @GetMapping("/sua/{id}")
    public String showEditForm(@PathVariable Long id, Model model) {
        model.addAttribute("hopDong", hopDongService.getById(id));
        model.addAttribute("dsTrangThai", Arrays.asList(TrangThaiHopDong.values()));
        model.addAttribute("action", "/hop-dong/cap-nhat/" + id);
        model.addAttribute("title", "Sửa hợp đồng");
        return "hopdong/form";
    }

    @PostMapping("/cap-nhat/{id}")
    public String update(@PathVariable Long id,
                         @ModelAttribute("hopDong") HopDong hopDong,
                         Model model) {
        try {
            hopDongService.update(id, hopDong);
            return "redirect:/hop-dong";
        } catch (Exception e) {
            model.addAttribute("hopDong", hopDong);
            model.addAttribute("dsTrangThai", Arrays.asList(TrangThaiHopDong.values()));
            model.addAttribute("action", "/hop-dong/cap-nhat/" + id);
            model.addAttribute("title", "Sửa hợp đồng");
            model.addAttribute("error", e.getMessage());
            return "hopdong/form";
        }
    }

    @GetMapping("/xoa/{id}")
    public String delete(@PathVariable Long id) {
        hopDongService.delete(id);
        return "redirect:/hop-dong";
    }
}