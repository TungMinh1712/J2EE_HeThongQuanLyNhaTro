package com.example.DACK_QuanLyNhaTro.service;

import com.example.DACK_QuanLyNhaTro.entity.KhachThue;
import com.example.DACK_QuanLyNhaTro.repository.KhachThueRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class KhachThueService {

    private final KhachThueRepository khachThueRepository;

    public List<KhachThue> layTatCa() {
        return khachThueRepository.findAll();
    }

    public KhachThue layTheoId(Long id) {
        return khachThueRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Không tìm thấy khách thuê với id: " + id));
    }

    public KhachThue layTheoCccd(String cccd) {
        return khachThueRepository.findByCccd(cccd)
                .orElseThrow(() -> new RuntimeException("Không tìm thấy khách thuê với CCCD: " + cccd));
    }

    public List<KhachThue> timTheoHoTen(String hoTen) {
        return khachThueRepository.findByHoTenContainingIgnoreCase(hoTen);
    }

    public KhachThue taoMoi(KhachThue khachThue) {
        if (khachThueRepository.existsByCccd(khachThue.getCccd())) {
            throw new RuntimeException("CCCD đã tồn tại");
        }
        return khachThueRepository.save(khachThue);
    }

    public KhachThue capNhat(Long id, KhachThue khachThueMoi) {
        KhachThue khachThueCu = layTheoId(id);

        if (!khachThueCu.getCccd().equals(khachThueMoi.getCccd())
                && khachThueRepository.existsByCccd(khachThueMoi.getCccd())) {
            throw new RuntimeException("CCCD đã tồn tại");
        }

        khachThueCu.setHoTen(khachThueMoi.getHoTen());
        khachThueCu.setCccd(khachThueMoi.getCccd());
        khachThueCu.setSoDienThoai(khachThueMoi.getSoDienThoai());
        khachThueCu.setEmail(khachThueMoi.getEmail());
        khachThueCu.setNgaySinh(khachThueMoi.getNgaySinh());
        khachThueCu.setGioiTinh(khachThueMoi.getGioiTinh());
        khachThueCu.setDiaChi(khachThueMoi.getDiaChi());
        khachThueCu.setGhiChu(khachThueMoi.getGhiChu());

        return khachThueRepository.save(khachThueCu);
    }

    public void xoa(Long id) {
        KhachThue khachThue = layTheoId(id);
        khachThueRepository.delete(khachThue);
    }
}
