package com.example.DACK_QuanLyNhaTro.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "khach_thue")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class KhachThue extends BaseEntity {

    @Column(name = "ho_ten", nullable = false, length = 100)
    private String hoTen;

    @Column(name = "cccd", nullable = false, unique = true, length = 20)
    private String cccd;

    @Column(name = "so_dien_thoai", length = 20)
    private String soDienThoai;

    @Column(name = "email", length = 100)
    private String email;

    @Column(name = "ngay_sinh")
    private LocalDate ngaySinh;

    @Column(name = "gioi_tinh", length = 10)
    private String gioiTinh;

    @Column(name = "dia_chi", length = 255)
    private String diaChi;

    @Column(name = "ghi_chu", length = 255)
    private String ghiChu;

    @OneToMany(mappedBy = "khachThue", cascade = CascadeType.ALL, orphanRemoval = true)
    @Builder.Default
    private List<HopDong> danhSachHopDong = new ArrayList<>();
}