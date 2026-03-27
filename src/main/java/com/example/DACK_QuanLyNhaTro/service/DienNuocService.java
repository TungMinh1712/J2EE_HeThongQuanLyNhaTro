package com.example.DACK_QuanLyNhaTro.service;

import com.example.DACK_QuanLyNhaTro.entity.DienNuoc;
import com.example.DACK_QuanLyNhaTro.entity.Phong;
import com.example.DACK_QuanLyNhaTro.repository.DienNuocRepository;
import com.example.DACK_QuanLyNhaTro.repository.PhongRepository;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
public class DienNuocService {

    private final DienNuocRepository dienNuocRepository;
    private final PhongRepository phongRepository;

    public DienNuocService(DienNuocRepository dienNuocRepository, PhongRepository phongRepository) {
        this.dienNuocRepository = dienNuocRepository;
        this.phongRepository = phongRepository;
    }

    public List<DienNuoc> getAll() {
        return dienNuocRepository.findAll();
    }

    public List<Phong> getAllPhong() {
        return phongRepository.findAll();
    }

    public DienNuoc getById(Long id) {
        return dienNuocRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Không tìm thấy dữ liệu điện nước"));
    }

    public void save(DienNuoc dienNuoc, Long phongId) {
        Phong phong = phongRepository.findById(phongId)
                .orElseThrow(() -> new RuntimeException("Không tìm thấy phòng"));

        validate(dienNuoc);
        tinhTongTien(dienNuoc);

        dienNuoc.setPhong(phong);
        dienNuocRepository.save(dienNuoc);
    }

    public void update(Long id, DienNuoc dienNuoc, Long phongId) {
        DienNuoc old = getById(id);
        Phong phong = phongRepository.findById(phongId)
                .orElseThrow(() -> new RuntimeException("Không tìm thấy phòng"));

        validate(dienNuoc);

        old.setPhong(phong);
        old.setThang(dienNuoc.getThang());
        old.setNam(dienNuoc.getNam());
        old.setChiSoDienCu(dienNuoc.getChiSoDienCu());
        old.setChiSoDienMoi(dienNuoc.getChiSoDienMoi());
        old.setChiSoNuocCu(dienNuoc.getChiSoNuocCu());
        old.setChiSoNuocMoi(dienNuoc.getChiSoNuocMoi());
        old.setGiaDien(dienNuoc.getGiaDien());
        old.setGiaNuoc(dienNuoc.getGiaNuoc());
        old.setPhiGuiXe(dienNuoc.getPhiGuiXe());
        old.setPhiRac(dienNuoc.getPhiRac());
        old.setPhiInternet(dienNuoc.getPhiInternet());

        tinhTongTien(old);
        dienNuocRepository.save(old);
    }

    public void delete(Long id) {
        dienNuocRepository.deleteById(id);
    }

    private void validate(DienNuoc d) {
        if (d.getThang() == null || d.getThang() < 1 || d.getThang() > 12) {
            throw new RuntimeException("Tháng không hợp lệ");
        }

        if (d.getNam() == null || d.getNam() < 2000) {
            throw new RuntimeException("Năm không hợp lệ");
        }

        if (d.getChiSoDienMoi() < d.getChiSoDienCu()) {
            throw new RuntimeException("Chỉ số điện mới phải lớn hơn hoặc bằng chỉ số điện cũ");
        }

        if (d.getChiSoNuocMoi() < d.getChiSoNuocCu()) {
            throw new RuntimeException("Chỉ số nước mới phải lớn hơn hoặc bằng chỉ số nước cũ");
        }
    }

    private void tinhTongTien(DienNuoc d) {
        int soDien = d.getChiSoDienMoi() - d.getChiSoDienCu();
        int soNuoc = d.getChiSoNuocMoi() - d.getChiSoNuocCu();

        BigDecimal tongDien = d.getGiaDien().multiply(BigDecimal.valueOf(soDien));
        BigDecimal tongNuoc = d.getGiaNuoc().multiply(BigDecimal.valueOf(soNuoc));
        BigDecimal tongDichVu = d.getPhiGuiXe().add(d.getPhiRac()).add(d.getPhiInternet());
        BigDecimal tong = tongDien.add(tongNuoc).add(tongDichVu);

        d.setTongTienDien(tongDien);
        d.setTongTienNuoc(tongNuoc);
        d.setTongTienDichVu(tongDichVu);
        d.setTongTien(tong);
    }
}