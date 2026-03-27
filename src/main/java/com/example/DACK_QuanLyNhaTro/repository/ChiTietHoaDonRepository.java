package com.example.DACK_QuanLyNhaTro.repository;

import com.example.DACK_QuanLyNhaTro.entity.ChiTietHoaDon;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ChiTietHoaDonRepository extends JpaRepository<ChiTietHoaDon, Long> {

    List<ChiTietHoaDon> findByHoaDonId(Long hoaDonId);
}