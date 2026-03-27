package com.example.DACK_QuanLyNhaTro.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface KhachThueRepository<KhachThue> extends JpaRepository<KhachThue, Long> {
    // Có thể thêm tìm kiếm theo mã hoặc tên nếu cần
    KhachThue findByMaKhach(String maKhach);
}
