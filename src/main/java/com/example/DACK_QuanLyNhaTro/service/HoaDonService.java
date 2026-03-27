package com.example.DACK_QuanLyNhaTro.service;

import com.example.DACK_QuanLyNhaTro.entity.HoaDon;
import com.example.DACK_QuanLyNhaTro.entity.HopDong;
import com.example.DACK_QuanLyNhaTro.entity.Phong;
import com.example.DACK_QuanLyNhaTro.repository.HoaDonRepository;
import com.example.DACK_QuanLyNhaTro.repository.HopDongRepository;
import com.example.DACK_QuanLyNhaTro.repository.PhongRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
@RequiredArgsConstructor
public class HoaDonService {

    private final HoaDonRepository hoaDonRepository;
    private final PhongRepository phongRepository;
    private final HopDongRepository hopDongRepository;

    public List<HoaDon> layTatCa() {
        return hoaDonRepository.findAll();
    }

    public HoaDon layTheoId(Long id) {
        return hoaDonRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Không tìm thấy hóa đơn với id: " + id));
    }

    public List<HoaDon> layTheoPhong(Long phongId) {
        return hoaDonRepository.findByPhongId(phongId);
    }

    public List<HoaDon> layTheoHopDong(Long hopDongId) {
        return hoaDonRepository.findByHopDongId(hopDongId);
    }

    public List<HoaDon> layTheoThangLap(String thangLap) {
        return hoaDonRepository.findByThangLap(thangLap);
    }

    public List<HoaDon> layTheoTrangThai(HoaDon.TrangThaiHoaDon trangThai) {
        return hoaDonRepository.findByTrangThai(trangThai);
    }

    public HoaDon taoMoi(HoaDon hoaDon) {
        Phong phong = phongRepository.findById(hoaDon.getPhong().getId())
                .orElseThrow(() -> new RuntimeException("Không tìm thấy phòng"));

        HopDong hopDong = null;
        if (hoaDon.getHopDong() != null && hoaDon.getHopDong().getId() != null) {
            hopDong = hopDongRepository.findById(hoaDon.getHopDong().getId())
                    .orElseThrow(() -> new RuntimeException("Không tìm thấy hợp đồng"));
        }

        hoaDonRepository.findByPhongIdAndThangLap(phong.getId(), hoaDon.getThangLap())
                .ifPresent(hd -> {
                    throw new RuntimeException("Phòng này đã có hóa đơn trong tháng " + hoaDon.getThangLap());
                });

        hoaDon.setPhong(phong);
        hoaDon.setHopDong(hopDong);
        hoaDon.setTongTien(tinhTongTien(hoaDon));

        if (hoaDon.getSoTienDaThanhToan() == null) {
            hoaDon.setSoTienDaThanhToan(BigDecimal.ZERO);
        }

        hoaDon.setTrangThai(xacDinhTrangThai(hoaDon.getSoTienDaThanhToan(), hoaDon.getTongTien()));

        return hoaDonRepository.save(hoaDon);
    }

    public HoaDon capNhat(Long id, HoaDon hoaDonMoi) {
        HoaDon hoaDonCu = layTheoId(id);

        Phong phong = phongRepository.findById(hoaDonMoi.getPhong().getId())
                .orElseThrow(() -> new RuntimeException("Không tìm thấy phòng"));

        HopDong hopDong = null;
        if (hoaDonMoi.getHopDong() != null && hoaDonMoi.getHopDong().getId() != null) {
            hopDong = hopDongRepository.findById(hoaDonMoi.getHopDong().getId())
                    .orElseThrow(() -> new RuntimeException("Không tìm thấy hợp đồng"));
        }

        hoaDonCu.setPhong(phong);
        hoaDonCu.setHopDong(hopDong);
        hoaDonCu.setThangLap(hoaDonMoi.getThangLap());
        hoaDonCu.setNgayLap(hoaDonMoi.getNgayLap());
        hoaDonCu.setHanThanhToan(hoaDonMoi.getHanThanhToan());
        hoaDonCu.setTienPhong(hoaDonMoi.getTienPhong());
        hoaDonCu.setTienDien(hoaDonMoi.getTienDien());
        hoaDonCu.setTienNuoc(hoaDonMoi.getTienNuoc());
        hoaDonCu.setChiPhiKhac(hoaDonMoi.getChiPhiKhac());
        hoaDonCu.setGiamGia(hoaDonMoi.getGiamGia());
        hoaDonCu.setSoTienDaThanhToan(hoaDonMoi.getSoTienDaThanhToan() == null ? BigDecimal.ZERO : hoaDonMoi.getSoTienDaThanhToan());
        hoaDonCu.setGhiChu(hoaDonMoi.getGhiChu());

        hoaDonCu.setTongTien(tinhTongTien(hoaDonCu));
        hoaDonCu.setTrangThai(xacDinhTrangThai(hoaDonCu.getSoTienDaThanhToan(), hoaDonCu.getTongTien()));

        return hoaDonRepository.save(hoaDonCu);
    }

    public HoaDon capNhatThanhToan(Long id, BigDecimal soTienThanhToanThem) {
        HoaDon hoaDon = layTheoId(id);

        if (soTienThanhToanThem.compareTo(BigDecimal.ZERO) <= 0) {
            throw new RuntimeException("Số tiền thanh toán phải lớn hơn 0");
        }

        BigDecimal soTienDaThanhToanMoi = hoaDon.getSoTienDaThanhToan().add(soTienThanhToanThem);
        hoaDon.setSoTienDaThanhToan(soTienDaThanhToanMoi);
        hoaDon.setTrangThai(xacDinhTrangThai(soTienDaThanhToanMoi, hoaDon.getTongTien()));

        return hoaDonRepository.save(hoaDon);
    }

    public void xoa(Long id) {
        HoaDon hoaDon = layTheoId(id);
        hoaDonRepository.delete(hoaDon);
    }

    private BigDecimal tinhTongTien(HoaDon hoaDon) {
        BigDecimal tienPhong = hoaDon.getTienPhong() == null ? BigDecimal.ZERO : hoaDon.getTienPhong();
        BigDecimal tienDien = hoaDon.getTienDien() == null ? BigDecimal.ZERO : hoaDon.getTienDien();
        BigDecimal tienNuoc = hoaDon.getTienNuoc() == null ? BigDecimal.ZERO : hoaDon.getTienNuoc();
        BigDecimal chiPhiKhac = hoaDon.getChiPhiKhac() == null ? BigDecimal.ZERO : hoaDon.getChiPhiKhac();
        BigDecimal giamGia = hoaDon.getGiamGia() == null ? BigDecimal.ZERO : hoaDon.getGiamGia();

        return tienPhong.add(tienDien).add(tienNuoc).add(chiPhiKhac).subtract(giamGia);
    }

    private HoaDon.TrangThaiHoaDon xacDinhTrangThai(BigDecimal soTienDaThanhToan, BigDecimal tongTien) {
        if (soTienDaThanhToan.compareTo(BigDecimal.ZERO) <= 0) {
            return HoaDon.TrangThaiHoaDon.CHUA_THANH_TOAN;
        }
        if (soTienDaThanhToan.compareTo(tongTien) < 0) {
            return HoaDon.TrangThaiHoaDon.THANH_TOAN_MOT_PHAN;
        }
        return HoaDon.TrangThaiHoaDon.DA_THANH_TOAN;
    }
}