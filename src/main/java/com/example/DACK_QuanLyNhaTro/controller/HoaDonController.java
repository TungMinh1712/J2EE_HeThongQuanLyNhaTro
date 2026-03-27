package com.example.DACK_QuanLyNhaTro.controller;

import com.example.DACK_QuanLyNhaTro.entity.HoaDon;
import com.example.DACK_QuanLyNhaTro.service.HoaDonService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;

@RestController
@RequestMapping("/api/hoa-don")
@RequiredArgsConstructor
public class HoaDonController {

    private final HoaDonService hoaDonService;

    @GetMapping
    public List<HoaDon> layTatCa() {
        return hoaDonService.layTatCa();
    }

    @GetMapping("/{id}")
    public HoaDon layTheoId(@PathVariable Long id) {
        return hoaDonService.layTheoId(id);
    }

    @GetMapping("/phong/{phongId}")
    public List<HoaDon> layTheoPhong(@PathVariable Long phongId) {
        return hoaDonService.layTheoPhong(phongId);
    }

    @GetMapping("/hop-dong/{hopDongId}")
    public List<HoaDon> layTheoHopDong(@PathVariable Long hopDongId) {
        return hoaDonService.layTheoHopDong(hopDongId);
    }

    @GetMapping("/thang/{thangLap}")
    public List<HoaDon> layTheoThangLap(@PathVariable String thangLap) {
        return hoaDonService.layTheoThangLap(thangLap);
    }

    @GetMapping("/trang-thai/{trangThai}")
    public List<HoaDon> layTheoTrangThai(@PathVariable HoaDon.TrangThaiHoaDon trangThai) {
        return hoaDonService.layTheoTrangThai(trangThai);
    }

    @PostMapping
    public HoaDon taoMoi(@RequestBody HoaDon hoaDon) {
        return hoaDonService.taoMoi(hoaDon);
    }

    @PutMapping("/{id}")
    public HoaDon capNhat(@PathVariable Long id, @RequestBody HoaDon hoaDon) {
        return hoaDonService.capNhat(id, hoaDon);
    }

    @PatchMapping("/{id}/thanh-toan")
    public HoaDon capNhatThanhToan(@PathVariable Long id, @RequestParam BigDecimal soTienThanhToanThem) {
        return hoaDonService.capNhatThanhToan(id, soTienThanhToanThem);
    }

    @DeleteMapping("/{id}")
    public String xoa(@PathVariable Long id) {
        hoaDonService.xoa(id);
        return "Xóa hóa đơn thành công";
    }
}