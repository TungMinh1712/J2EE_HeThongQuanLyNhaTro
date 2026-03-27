package com.example.DACK_QuanLyNhaTro.service;

import com.example.DACK_QuanLyNhaTro.entity.HopDong;
import com.example.DACK_QuanLyNhaTro.repository.HopDongRepository;
import com.example.DACK_QuanLyNhaTro.entity.KhachThue;
import com.example.DACK_QuanLyNhaTro.entity.Phong;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class HopDongService {

    private final HopDongRepository hopDongRepository;

    public HopDongService(HopDongRepository hopDongRepository) {
        this.hopDongRepository = hopDongRepository;
    }

    // Phương thức lấy tất cả hợp đồng
    public List<HopDong> getAll() {
        return hopDongRepository.findAll();
    }

    // Phương thức lấy hợp đồng theo ID
    public HopDong getById(Long id) {
        return hopDongRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Hợp đồng không tồn tại"));
    }

    // Phương thức lưu hợp đồng mới
    public void save(HopDong hopDong) {
        hopDongRepository.save(hopDong);
    }

    // Phương thức cập nhật hợp đồng
    public void update(Long id, HopDong hopDong) {
        HopDong existingHopDong = hopDongRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Hợp đồng không tồn tại"));
        existingHopDong.setMaHopDong(hopDong.getMaHopDong());
        existingHopDong.setTrangThai(hopDong.getTrangThai());
        hopDongRepository.save(existingHopDong);
    }

    // Phương thức xóa hợp đồng
    public void delete(Long id) {
        hopDongRepository.deleteById(id);
    }

    // Phương thức lấy tất cả Khách thuê (tạm thời giả sử có phương thức lấy từ repository)
    public List<KhachThue> getAllKhachThue() {
       
        return List.of(new KhachThue()); 
    }

    // Phương thức lấy tất cả Phòng
    public List<Phong> getAllPhong() {
       
        return List.of(new Phong());
    }
}