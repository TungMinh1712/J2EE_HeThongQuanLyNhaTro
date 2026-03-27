package com.example.DACK_QuanLyNhaTro.service;

import com.example.DACK_QuanLyNhaTro.entity.ChiSoDienNuoc;
import com.example.DACK_QuanLyNhaTro.entity.Phong;
import com.example.DACK_QuanLyNhaTro.repository.ChiSoDienNuocRepository;
import com.example.DACK_QuanLyNhaTro.repository.PhongRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ChiSoDienNuocService {

    private final ChiSoDienNuocRepository chiSoDienNuocRepository;
    private final PhongRepository phongRepository;

    public List<ChiSoDienNuoc> layTatCa() {
        return chiSoDienNuocRepository.findAll();
    }

    public ChiSoDienNuoc layTheoId(Long id) {
        return chiSoDienNuocRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Không tìm thấy chỉ số điện nước với id: " + id));
    }

    public List<ChiSoDienNuoc> layTheoPhong(Long phongId) {
        return chiSoDienNuocRepository.findByPhongId(phongId);
    }

    public List<ChiSoDienNuoc> layTheoThangNam(String thangNam) {
        return chiSoDienNuocRepository.findByThangNam(thangNam);
    }

    public List<ChiSoDienNuoc> layTheoPhongVaThang(Long phongId, String thangNam) {
        return chiSoDienNuocRepository.findByPhongIdAndThangNam(phongId, thangNam);
    }

    public ChiSoDienNuoc taoMoi(ChiSoDienNuoc chiSoDienNuoc) {
        Phong phong = phongRepository.findById(chiSoDienNuoc.getPhong().getId())
                .orElseThrow(() -> new RuntimeException("Không tìm thấy phòng"));

        chiSoDienNuocRepository.findByPhongIdAndThangNamAndLoaiDichVu(
                phong.getId(),
                chiSoDienNuoc.getThangNam(),
                chiSoDienNuoc.getLoaiDichVu()
        ).ifPresent(cs -> {
            throw new RuntimeException("Đã tồn tại chỉ số cho phòng, tháng và loại dịch vụ này");
        });

        if (chiSoDienNuoc.getChiSoMoi() < chiSoDienNuoc.getChiSoCu()) {
            throw new RuntimeException("Chỉ số mới phải lớn hơn hoặc bằng chỉ số cũ");
        }

        chiSoDienNuoc.setPhong(phong);
        chiSoDienNuoc.setSoLuongTieuThu(chiSoDienNuoc.getChiSoMoi() - chiSoDienNuoc.getChiSoCu());
        chiSoDienNuoc.setThanhTien(
                chiSoDienNuoc.getDonGia().multiply(java.math.BigDecimal.valueOf(chiSoDienNuoc.getSoLuongTieuThu()))
        );

        return chiSoDienNuocRepository.save(chiSoDienNuoc);
    }

    public ChiSoDienNuoc capNhat(Long id, ChiSoDienNuoc chiSoMoi) {
        ChiSoDienNuoc chiSoCu = layTheoId(id);

        Phong phong = phongRepository.findById(chiSoMoi.getPhong().getId())
                .orElseThrow(() -> new RuntimeException("Không tìm thấy phòng"));

        if (chiSoMoi.getChiSoMoi() < chiSoMoi.getChiSoCu()) {
            throw new RuntimeException("Chỉ số mới phải lớn hơn hoặc bằng chỉ số cũ");
        }

        chiSoCu.setPhong(phong);
        chiSoCu.setThangNam(chiSoMoi.getThangNam());
        chiSoCu.setLoaiDichVu(chiSoMoi.getLoaiDichVu());
        chiSoCu.setChiSoCu(chiSoMoi.getChiSoCu());
        chiSoCu.setChiSoMoi(chiSoMoi.getChiSoMoi());
        chiSoCu.setSoLuongTieuThu(chiSoMoi.getChiSoMoi() - chiSoMoi.getChiSoCu());
        chiSoCu.setDonGia(chiSoMoi.getDonGia());
        chiSoCu.setThanhTien(
                chiSoMoi.getDonGia().multiply(java.math.BigDecimal.valueOf(chiSoCu.getSoLuongTieuThu()))
        );
        chiSoCu.setGhiChu(chiSoMoi.getGhiChu());

        return chiSoDienNuocRepository.save(chiSoCu);
    }

    public void xoa(Long id) {
        ChiSoDienNuoc chiSoDienNuoc = layTheoId(id);
        chiSoDienNuocRepository.delete(chiSoDienNuoc);
    }
}