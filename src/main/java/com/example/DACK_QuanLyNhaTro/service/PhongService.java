package com.example.DACK_QuanLyNhaTro.service;

import com.example.DACK_QuanLyNhaTro.entity.Phong;
import com.example.DACK_QuanLyNhaTro.repository.PhongRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PhongService {

    private final PhongRepository phongRepository;

    public List<Phong> layTatCa() {
        return phongRepository.findAll();
    }

    public Phong layTheoId(Long id) {
        return phongRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Không tìm thấy phòng với id: " + id));
    }

    public Phong layTheoMaPhong(String maPhong) {
        return phongRepository.findByMaPhong(maPhong)
                .orElseThrow(() -> new RuntimeException("Không tìm thấy phòng với mã phòng: " + maPhong));
    }

    public List<Phong> layTheoTrangThai(Phong.TrangThaiPhong trangThai) {
        return phongRepository.findByTrangThai(trangThai);
    }

    public List<Phong> timTheoTenPhong(String tenPhong) {
        return phongRepository.findByTenPhongContainingIgnoreCase(tenPhong);
    }

    public Phong taoMoi(Phong phong) {
        if (phongRepository.existsByMaPhong(phong.getMaPhong())) {
            throw new RuntimeException("Mã phòng đã tồn tại");
        }
        return phongRepository.save(phong);
    }

    public Phong capNhat(Long id, Phong phongMoi) {
        Phong phongCu = layTheoId(id);

        if (!phongCu.getMaPhong().equals(phongMoi.getMaPhong())
                && phongRepository.existsByMaPhong(phongMoi.getMaPhong())) {
            throw new RuntimeException("Mã phòng đã tồn tại");
        }

        phongCu.setMaPhong(phongMoi.getMaPhong());
        phongCu.setTenPhong(phongMoi.getTenPhong());
        phongCu.setDienTich(phongMoi.getDienTich());
        phongCu.setGiaThue(phongMoi.getGiaThue());
        phongCu.setTienCoc(phongMoi.getTienCoc());
        phongCu.setSoNguoiToiDa(phongMoi.getSoNguoiToiDa());
        phongCu.setMoTa(phongMoi.getMoTa());
        phongCu.setTrangThai(phongMoi.getTrangThai());

        return phongRepository.save(phongCu);
    }

    public void xoa(Long id) {
        Phong phong = layTheoId(id);
        phongRepository.delete(phong);
    }
}