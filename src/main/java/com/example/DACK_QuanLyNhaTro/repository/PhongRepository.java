package com.example.DACK_QuanLyNhaTro.repository;

import com.example.DACK_QuanLyNhaTro.entity.Phong;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface PhongRepository extends JpaRepository<Phong, Long> {

    Optional<Phong> findByMaPhong(String maPhong);

    boolean existsByMaPhong(String maPhong);

    List<Phong> findByTrangThai(Phong.TrangThaiPhong trangThai);

    List<Phong> findByTenPhongContainingIgnoreCase(String tenPhong);
}