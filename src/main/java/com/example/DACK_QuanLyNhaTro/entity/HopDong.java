package com.example.DACK_QuanLyNhaTro.entity;

import jakarta.persistence.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table(name = "hop_dong")
public class HopDong {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "ma_hop_dong", nullable = false, unique = true)
    private String maHopDong;

    @ManyToOne
    @JoinColumn(name = "khach_thue_id", nullable = false)
    private KhachThue khachThue;

    @ManyToOne
    @JoinColumn(name = "phong_id", nullable = false)
    private Phong phong;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Column(name = "ngay_bat_dau", nullable = false)
    private LocalDate ngayBatDau;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Column(name = "ngay_ket_thuc", nullable = false)
    private LocalDate ngayKetThuc;

    @Column(name = "gia_thue", nullable = false)
    private BigDecimal giaThue;

    @Enumerated(EnumType.STRING)
    @Column(name = "trang_thai", nullable = false)
    private TrangThaiHopDong trangThai;

    // Constructors, Getters, and Setters
    public String getMaHopDong() {
        return maHopDong;
    }

    public void setMaHopDong(String maHopDong) {
        this.maHopDong = maHopDong;
    }

    public TrangThaiHopDong getTrangThai() {
        return trangThai;
    }

    public void setTrangThai(TrangThaiHopDong trangThai) {
        this.trangThai = trangThai;
    }
}