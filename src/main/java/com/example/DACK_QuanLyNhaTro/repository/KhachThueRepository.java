package com.example.DACK_QuanLyNhaTro.repository;

import com.example.DACK_QuanLyNhaTro.entity.KhachThue;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface KhachThueRepository extends JpaRepository<KhachThue, Long> {

    Optional<KhachThue> findByCccd(String cccd);

    boolean existsByCccd(String cccd);

    List<KhachThue> findByHoTenContainingIgnoreCase(String hoTen);

    List<KhachThue> findBySoDienThoaiContaining(String soDienThoai);
}