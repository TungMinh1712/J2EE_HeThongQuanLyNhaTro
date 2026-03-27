package com.example.DACK_QuanLyNhaTro.repository;

import com.example.DACK_QuanLyNhaTro.entity.HoaDon;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface HoaDonRepository extends JpaRepository<HoaDon, Long> {

    List<HoaDon> findByPhongId(Long phongId);

    List<HoaDon> findByHopDongId(Long hopDongId);

    List<HoaDon> findByThangLap(String thangLap);

    List<HoaDon> findByTrangThai(HoaDon.TrangThaiHoaDon trangThai);

    Optional<HoaDon> findByPhongIdAndThangLap(Long phongId, String thangLap);
}