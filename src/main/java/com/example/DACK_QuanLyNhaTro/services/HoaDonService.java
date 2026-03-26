package com.example.DACK_QuanLyNhaTro.services;

import com.example.DACK_QuanLyNhaTro.entity.HoaDon;
import com.example.DACK_QuanLyNhaTro.repository.HoaDonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class HoaDonService {

    @Autowired
    private HoaDonRepository hoaDonRepository;

    public List<HoaDon> getAll() {
        return hoaDonRepository.findAll();
    }

    public HoaDon save(HoaDon hoaDon) {
        hoaDon.setNgayTao(LocalDate.now());
        hoaDon.setTrangThai("Chưa thanh toán");
        return hoaDonRepository.save(hoaDon);
    }

    public void delete(Long id) {
        hoaDonRepository.deleteById(id);
    }

    public HoaDon getById(Long id) {
        return hoaDonRepository.findById(id).orElse(null);
    }

    public void thanhToan(Long id) {
        HoaDon hd = getById(id);
        if (hd != null) {
            hd.setTrangThai("Đã thanh toán");
            hd.setNgayThanhToan(LocalDate.now());
            hoaDonRepository.save(hd);
        }
    }
}