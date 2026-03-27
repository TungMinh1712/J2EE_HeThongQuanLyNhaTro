package com.example.DACK_QuanLyNhaTro.repository;

import com.example.DACK_QuanLyNhaTro.entity.ChiSoDienNuoc;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ChiSoDienNuocRepository extends JpaRepository<ChiSoDienNuoc, Long> {

    List<ChiSoDienNuoc> findByPhongId(Long phongId);

    List<ChiSoDienNuoc> findByThangNam(String thangNam);

    List<ChiSoDienNuoc> findByPhongIdAndThangNam(Long phongId, String thangNam);

    Optional<ChiSoDienNuoc> findByPhongIdAndThangNamAndLoaiDichVu(
            Long phongId,
            String thangNam,
            ChiSoDienNuoc.LoaiDichVu loaiDichVu
    );
}