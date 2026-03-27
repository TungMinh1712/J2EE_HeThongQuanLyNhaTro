package com.example.DACK_QuanLyNhaTro.repository;

import com.example.DACK_QuanLyNhaTro.entity.HopDong;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;

public interface HopDongRepository extends JpaRepository<HopDong, Long> {

    List<HopDong> findByPhongId(Long phongId);

    List<HopDong> findByKhachThueId(Long khachThueId);

    List<HopDong> findByTrangThai(HopDong.TrangThaiHopDong trangThai);

    List<HopDong> findByNgayBatDauLessThanEqualAndNgayKetThucGreaterThanEqual(
            LocalDate ngay1, LocalDate ngay2
    );
}