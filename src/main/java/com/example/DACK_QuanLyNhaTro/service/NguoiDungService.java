package com.example.DACK_QuanLyNhaTro.service;

import com.example.DACK_QuanLyNhaTro.entity.NguoiDung;
import com.example.DACK_QuanLyNhaTro.repository.NguoiDungRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class NguoiDungService {

    private final NguoiDungRepository nguoiDungRepository;

    public List<NguoiDung> layTatCa() {
        return nguoiDungRepository.findAll();
    }

    public NguoiDung layTheoId(Long id) {
        return nguoiDungRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Không tìm thấy người dùng với id: " + id));
    }

    public NguoiDung layTheoTenDangNhap(String tenDangNhap) {
        return nguoiDungRepository.findByTenDangNhap(tenDangNhap)
                .orElseThrow(() -> new RuntimeException("Không tìm thấy người dùng với tên đăng nhập: " + tenDangNhap));
    }

    public NguoiDung taoMoi(NguoiDung nguoiDung) {
        if (nguoiDungRepository.existsByTenDangNhap(nguoiDung.getTenDangNhap())) {
            throw new RuntimeException("Tên đăng nhập đã tồn tại");
        }

        if (nguoiDung.getEmail() != null && !nguoiDung.getEmail().isBlank()
                && nguoiDungRepository.existsByEmail(nguoiDung.getEmail())) {
            throw new RuntimeException("Email đã tồn tại");
        }

        return nguoiDungRepository.save(nguoiDung);
    }

    public NguoiDung capNhat(Long id, NguoiDung nguoiDungMoi) {
        NguoiDung nguoiDungCu = layTheoId(id);

        if (!nguoiDungCu.getTenDangNhap().equals(nguoiDungMoi.getTenDangNhap())
                && nguoiDungRepository.existsByTenDangNhap(nguoiDungMoi.getTenDangNhap())) {
            throw new RuntimeException("Tên đăng nhập đã tồn tại");
        }

        if (nguoiDungMoi.getEmail() != null && !nguoiDungMoi.getEmail().isBlank()) {
            boolean emailDaTonTai = nguoiDungRepository.findByEmail(nguoiDungMoi.getEmail())
                    .map(nd -> !nd.getId().equals(id))
                    .orElse(false);

            if (emailDaTonTai) {
                throw new RuntimeException("Email đã tồn tại");
            }
        }

        nguoiDungCu.setHoTen(nguoiDungMoi.getHoTen());
        nguoiDungCu.setTenDangNhap(nguoiDungMoi.getTenDangNhap());
        nguoiDungCu.setMatKhau(nguoiDungMoi.getMatKhau());
        nguoiDungCu.setEmail(nguoiDungMoi.getEmail());
        nguoiDungCu.setSoDienThoai(nguoiDungMoi.getSoDienThoai());
        nguoiDungCu.setVaiTro(nguoiDungMoi.getVaiTro());
        nguoiDungCu.setTrangThaiHoatDong(nguoiDungMoi.getTrangThaiHoatDong());

        return nguoiDungRepository.save(nguoiDungCu);
    }

    public void xoa(Long id) {
        NguoiDung nguoiDung = layTheoId(id);
        nguoiDungRepository.delete(nguoiDung);
    }
}