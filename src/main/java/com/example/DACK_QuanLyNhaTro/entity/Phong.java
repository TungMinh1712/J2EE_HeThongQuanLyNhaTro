package com.example.DACK_QuanLyNhaTro.entity;

import com.example.DACK_QuanLyNhaTro.entity.BaseEntity;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "phong")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Phong extends BaseEntity {

    @Column(name = "ma_phong", nullable = false, unique = true, length = 20)
    private String maPhong;

    @Column(name = "ten_phong", nullable = false, length = 100)
    private String tenPhong;

    @Column(name = "dien_tich", nullable = false, precision = 10, scale = 2)
    private BigDecimal dienTich;

    @Column(name = "gia_thue", nullable = false, precision = 15, scale = 2)
    private BigDecimal giaThue;

    @Column(name = "tien_coc", precision = 15, scale = 2)
    private BigDecimal tienCoc;

    @Column(name = "so_nguoi_toi_da", nullable = false)
    private Integer soNguoiToiDa;

    @Column(name = "mo_ta", length = 255)
    private String moTa;

    @Enumerated(EnumType.STRING)
    @Column(name = "trang_thai", nullable = false, length = 20)
    private TrangThaiPhong trangThai;

    @OneToMany(mappedBy = "phong", cascade = CascadeType.ALL, orphanRemoval = true)
    @Builder.Default
    private List<HopDong> danhSachHopDong = new ArrayList<>();

    @OneToMany(mappedBy = "phong", cascade = CascadeType.ALL, orphanRemoval = true)
    @Builder.Default
    private List<ChiSoDienNuoc> danhSachChiSoDienNuoc = new ArrayList<>();

    @OneToMany(mappedBy = "phong", cascade = CascadeType.ALL, orphanRemoval = true)
    @Builder.Default
    private List<HoaDon> danhSachHoaDon = new ArrayList<>();

    public enum TrangThaiPhong {
        CON_TRONG,
        DA_THUE,
        DANG_BAO_TRI
    }
}