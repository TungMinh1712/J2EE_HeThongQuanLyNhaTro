package com.example.DACK_QuanLyNhaTro.service;

import com.example.DACK_QuanLyNhaTro.entity.HopDong;
import com.example.DACK_QuanLyNhaTro.entity.KhachThue;
import com.example.DACK_QuanLyNhaTro.entity.Phong;
import com.example.DACK_QuanLyNhaTro.repository.HopDongRepository;
import com.example.DACK_QuanLyNhaTro.repository.KhachThueRepository;
import com.example.DACK_QuanLyNhaTro.repository.PhongRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import com.example.DACK_QuanLyNhaTro.entity.Phong;
import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
public class HopDongService {

    private final HopDongRepository hopDongRepository;
    private final PhongRepository phongRepository;
    private final KhachThueRepository khachThueRepository;

    public List<HopDong> layTatCa() {
        return hopDongRepository.findAll();
    }

    public HopDong layTheoId(Long id) {
        return hopDongRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Không tìm thấy hợp đồng với id: " + id));
    }

    public List<HopDong> layTheoPhong(Long phongId) {
        return hopDongRepository.findByPhongId(phongId);
    }

    public List<HopDong> layTheoKhachThue(Long khachThueId) {
        return hopDongRepository.findByKhachThueId(khachThueId);
    }

    public List<HopDong> layTheoTrangThai(HopDong.TrangThaiHopDong trangThai) {
        return hopDongRepository.findByTrangThai(trangThai);
    }

    public List<HopDong> layHopDongConHieuLucHomNay() {
        LocalDate homNay = LocalDate.now();
        return hopDongRepository.findByNgayBatDauLessThanEqualAndNgayKetThucGreaterThanEqual(homNay, homNay);
    }

    public HopDong taoMoi(HopDong hopDong) {
        Phong phong = phongRepository.findById(hopDong.getPhong().getId())
                .orElseThrow(() -> new RuntimeException("Không tìm thấy phòng"));

        KhachThue khachThue = khachThueRepository.findById(hopDong.getKhachThue().getId())
                .orElseThrow(() -> new RuntimeException("Không tìm thấy khách thuê"));

        if (hopDong.getNgayBatDau().isAfter(hopDong.getNgayKetThuc())) {
            throw new RuntimeException("Ngày bắt đầu không được lớn hơn ngày kết thúc");
        }

        if (phong.getTrangThai() != Phong.TrangThaiPhong.CON_TRONG) {
            throw new RuntimeException("Chỉ được tạo hợp đồng cho phòng còn trống");
        }

        if (hopDong.getGiaThueLucKy() == null) {
            hopDong.setGiaThueLucKy(phong.getGiaThue());
        }

        if (hopDong.getTienCoc() == null) {
            hopDong.setTienCoc(phong.getTienCoc());
        }

        hopDong.setPhong(phong);
        hopDong.setKhachThue(khachThue);

        HopDong hopDongDaLuu = hopDongRepository.save(hopDong);

        phong.setTrangThai(Phong.TrangThaiPhong.DA_THUE);
        phongRepository.save(phong);

        return hopDongDaLuu;
    }

    public HopDong capNhat(Long id, HopDong hopDongMoi) {
        HopDong hopDongCu = layTheoId(id);

        Phong phongMoi = phongRepository.findById(hopDongMoi.getPhong().getId())
                .orElseThrow(() -> new RuntimeException("Không tìm thấy phòng"));

        KhachThue khachThue = khachThueRepository.findById(hopDongMoi.getKhachThue().getId())
                .orElseThrow(() -> new RuntimeException("Không tìm thấy khách thuê"));

        if (hopDongMoi.getNgayBatDau().isAfter(hopDongMoi.getNgayKetThuc())) {
            throw new RuntimeException("Ngày bắt đầu không được lớn hơn ngày kết thúc");
        }

        Phong phongCu = hopDongCu.getPhong();

        if (!phongCu.getId().equals(phongMoi.getId())
                && phongMoi.getTrangThai() == Phong.TrangThaiPhong.DA_THUE) {
            throw new RuntimeException("Phòng mới này đã được thuê");
        }

        hopDongCu.setPhong(phongMoi);
        hopDongCu.setKhachThue(khachThue);
        hopDongCu.setNgayBatDau(hopDongMoi.getNgayBatDau());
        hopDongCu.setNgayKetThuc(hopDongMoi.getNgayKetThuc());
        hopDongCu.setGiaThueLucKy(hopDongMoi.getGiaThueLucKy());
        hopDongCu.setTienCoc(hopDongMoi.getTienCoc());
        hopDongCu.setDieuKhoan(hopDongMoi.getDieuKhoan());
        hopDongCu.setTrangThai(hopDongMoi.getTrangThai());

        HopDong hopDongDaCapNhat = hopDongRepository.save(hopDongCu);

        if (!phongCu.getId().equals(phongMoi.getId())) {
            phongCu.setTrangThai(Phong.TrangThaiPhong.CON_TRONG);
            phongRepository.save(phongCu);

            phongMoi.setTrangThai(Phong.TrangThaiPhong.DA_THUE);
            phongRepository.save(phongMoi);
        } else {
            if (hopDongCu.getTrangThai() == HopDong.TrangThaiHopDong.CON_HIEU_LUC) {
                phongMoi.setTrangThai(Phong.TrangThaiPhong.DA_THUE);
            } else {
                phongMoi.setTrangThai(Phong.TrangThaiPhong.CON_TRONG);
            }
            phongRepository.save(phongMoi);
        }

        return hopDongDaCapNhat;
    }

    public void xoa(Long id) {
        HopDong hopDong = layTheoId(id);

        Phong phong = hopDong.getPhong();

        hopDongRepository.delete(hopDong);

        phong.setTrangThai(Phong.TrangThaiPhong.CON_TRONG);
        phongRepository.save(phong);
    }
}