package com.example.DACK_QuanLyNhaTro.repository;

import com.example.DACK_QuanLyNhaTro.entity.HopDong;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface HopDongRepository extends JpaRepository<HopDong, Long> {
    List<HopDong> findAllByOrderByIdDesc();
    Optional<HopDong> findByMaHopDong(String maHopDong);
}