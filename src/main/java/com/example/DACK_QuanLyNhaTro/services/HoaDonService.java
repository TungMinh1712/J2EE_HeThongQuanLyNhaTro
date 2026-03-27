package com.example.DACK_QuanLyNhaTro.services;

import com.example.DACK_QuanLyNhaTro.entity.HoaDon;
import com.example.DACK_QuanLyNhaTro.repository.HoaDonRepository;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class HoaDonService {
    private final HoaDonRepository repo;
    public HoaDonService(HoaDonRepository repo) { this.repo = repo; }

    public List<HoaDon> getAll() { return repo.findAll(); }
    public HoaDon findById(Long id) { return repo.findById(id).orElse(null); }

    public void save(HoaDon hd) {
        double total = (hd.getTienPhong() != null ? hd.getTienPhong() : 0) +
                (hd.getTienDien() != null ? hd.getTienDien() : 0) +
                (hd.getTienNuoc() != null ? hd.getTienNuoc() : 0) +
                (hd.getTienDichVu() != null ? hd.getTienDichVu() : 0);
        hd.setTongTien(total);
        hd.setNoiDungCK("Phong " + hd.getPhong() + " thanh toan thang " + hd.getThang());
        if (hd.getId() == null) hd.setTrangThai("CHUA_THANH_TOAN");
        repo.save(hd);
    }

    public void capNhatTrangThai(Long id) {
        HoaDon hd = findById(id);
        if (hd != null) {
            hd.setTrangThai("DA_THANH_TOAN");
            repo.save(hd);
        }
    }

    public void deleteById(Long id) { repo.deleteById(id); }
}